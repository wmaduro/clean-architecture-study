package com.maduro.poker.unit.handevaluator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.maduro.poker.domain.CriticalHandOutcomeEnum;
import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.handevaluator.handdata.HandDataService;
import com.maduro.poker.unit.handmapper.HandMapperServiceDTO;

public class HandEvaluatorService {

	private String mainPlayerNameFilter = null;
	private AggressivityBehaviorEnum aggressivityBehaviorFilter = null;

	private static final String ACTION_PRE_FLOP = "PRE_FLOP";

	public HandEvaluatorService(String mainPlayerNameFilter, AggressivityBehaviorEnum aggressivityBehaviorFilter) {
		this.mainPlayerNameFilter = mainPlayerNameFilter;
		this.aggressivityBehaviorFilter = aggressivityBehaviorFilter;
	}

	public HandEvaluatorServiceDTO process(HandMapperServiceDTO handMapperServiceDTO) throws Exception {

		if (handMapperServiceDTO == null || handMapperServiceDTO.getHandDataModelMap() == null)
			return null;

		HandEvaluatorServiceDTO handEvaluatorServiceDTO = new HandEvaluatorServiceDTO();

		for (Map.Entry<String, List<HandDataModel>> entry : handMapperServiceDTO.getHandDataModelMap().entrySet()) {

			if (!isHandProcessable(entry)) {
				continue;
			}

			CriticalHandOutcomeEnum criticalHandOutcomeEnum = new HandDataService().analyzeHands(entry.getValue());
			handEvaluatorServiceDTO.addGameCrititalHandDataModel(entry.getKey(), entry.getValue(), criticalHandOutcomeEnum);

		}
		return handEvaluatorServiceDTO;
	}

	private boolean isHandProcessable(Entry<String, List<HandDataModel>> entry) {
		if (entry.getValue().size() != 2) {
			return false;
		}

		if (!entry.getValue().get(0).getAll_in_action_street().equals(ACTION_PRE_FLOP)) {
			return false;
		}

		if (!checkIfPlayerExists(entry.getValue())) {
			return false;
		}

		if (!checkMainPlayerAggressityBehavior(entry.getValue())) {
			return false;
		}

		return true;
	}

	private boolean checkIfPlayerExists(List<HandDataModel> handDataModelList) {
		if (this.mainPlayerNameFilter == null) {
			return true;
		}
		return handDataModelList.stream()
				.anyMatch(handDataModel -> handDataModel.getUser_name().equals(this.mainPlayerNameFilter));
	}

	private boolean checkMainPlayerAggressityBehavior(List<HandDataModel> handDataModelList) {

		if (this.mainPlayerNameFilter == null || this.aggressivityBehaviorFilter == null) {
			return true;
		}

		return handDataModelList.stream()
				.anyMatch(handDataModel -> handDataModel.getUser_name().equals(this.mainPlayerNameFilter)
						&& handDataModel.getAction_pre_flop().equals(this.aggressivityBehaviorFilter.getValue()));
	}

}
