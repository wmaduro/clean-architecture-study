package com.maduro.poker.unit.handevaluator;

import java.util.ArrayList;
import java.util.List;

import com.maduro.poker.domain.CriticalHandOutcomeEnum;
import com.maduro.poker.domain.GameCrititalHandDataModel;
import com.maduro.poker.domain.HandDataModel;

import lombok.Getter;

public class HandEvaluatorServiceDTO {	

	@Getter
	private List<GameCrititalHandDataModel> gameCrititalHandDataModelList = new ArrayList<>();

	public void addGameCrititalHandDataModel(String gameCode, List<HandDataModel> handDataModelList,
			CriticalHandOutcomeEnum criticalHandOutcomeEnum) {
		
		gameCrititalHandDataModelList
				.add(new GameCrititalHandDataModel(gameCode, handDataModelList, criticalHandOutcomeEnum));

	}

}
