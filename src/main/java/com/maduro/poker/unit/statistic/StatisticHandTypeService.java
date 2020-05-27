package com.maduro.poker.unit.statistic;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.maduro.poker.unit.base.BaseService;
import com.maduro.poker.unit.evaluator.HandEvaluatorServiceDTO;

public class StatisticHandTypeService extends BaseService {
	
	public StatisticHandTypeService(EventBus eventBus) {
		super(eventBus);
	}

	@Subscribe
	public void stringEvent(HandEvaluatorServiceDTO event) {
		publish(process(event));
	}

	public StatisticHandTypeServiceDTO process(HandEvaluatorServiceDTO handEvaluatorServiceDTO) {

		StatisticHandTypeServiceDTO statisticHandTypeServiceDTO = new StatisticHandTypeServiceDTO();

		handEvaluatorServiceDTO.getGameCrititalHandDataModelList().stream().forEach(gameCrititalHandDataModel -> {

			statisticHandTypeServiceDTO.addHand(gameCrititalHandDataModel.getGameCode(),
					gameCrititalHandDataModel.getHandDataModelList(),
					gameCrititalHandDataModel.getCriticalHandOutcomeEnum());
		});

		return statisticHandTypeServiceDTO;
	}

}