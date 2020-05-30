package com.maduro.poker.unit.evaluator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.maduro.poker.domain.CriticalHandOutcomeEnum;
import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.base.BaseRunnableEventBusProcessEventService;
import com.maduro.poker.unit.evaluator.handdata.HandDataService;
import com.maduro.poker.unit.mapper.HandMapperServiceDTO;

public class HandEvaluatorService extends BaseRunnableEventBusProcessEventService<HandMapperServiceDTO> {

	private String mainPlayerName = null;
	private AggressivityBehaviorEnum aggressivityBehaviorEnum = null;
	private static final String ACTION_PRE_FLOP = "PRE_FLOP";

	public HandEvaluatorService(EventBus eventBus, String mainPlayerName,
			AggressivityBehaviorEnum aggressivityBehaviorEnum) {
		super(eventBus);
		this.mainPlayerName = mainPlayerName;
		this.aggressivityBehaviorEnum = aggressivityBehaviorEnum;
	}

	@Subscribe
	@Override
	public void processEvent(HandMapperServiceDTO event) {
		publish(process(event));

	}

	public HandEvaluatorServiceDTO process(HandMapperServiceDTO handMapperServiceDTO) {
		if (handMapperServiceDTO == null || handMapperServiceDTO.getHandDataModelMap() == null)
			return null;

		HandEvaluatorServiceDTO handEvaluatorServiceDTO = new HandEvaluatorServiceDTO();

		for (Map.Entry<String, List<HandDataModel>> entry : handMapperServiceDTO.getHandDataModelMap().entrySet()) {

			if (!isHandProcessable(entry)) {
				continue;
			}

			try {
				CriticalHandOutcomeEnum criticalHandOutcomeEnum = new HandDataService().analyzeHands(entry.getValue());
				handEvaluatorServiceDTO.addGameCrititalHandDataModel(entry.getKey(), entry.getValue(),
						criticalHandOutcomeEnum);
			} catch (Exception e) {
				e.printStackTrace();
			}

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
		if (this.mainPlayerName == null) {
			return true;
		}
		return handDataModelList.stream()
				.anyMatch(handDataModel -> handDataModel.getUser_name().equals(this.mainPlayerName));
	}

	private boolean checkMainPlayerAggressityBehavior(List<HandDataModel> handDataModelList) {

		if (this.mainPlayerName == null || this.mainPlayerName == null) {
			return true;
		}

		return handDataModelList.stream()
				.anyMatch(handDataModel -> handDataModel.getUser_name().equals(this.mainPlayerName)
						&& handDataModel.getAction_pre_flop().equals(this.aggressivityBehaviorEnum.getValue()));
	}

}
