package com.maduro.poker.unit.statistic;

import com.maduro.poker.base.queue.facade.IQueue;
import com.maduro.poker.base.service.BaseServiceFull;
import com.maduro.poker.unit.evaluator.HandEvaluatorServiceDTO;
import com.maduro.poker.unit.statistic.queue.StatisticHandTypeQueue;

public class StatisticHandTypeService
		extends BaseServiceFull<HandEvaluatorServiceDTO, StatisticHandTypeServiceDTO> {

	public StatisticHandTypeService(IQueue<HandEvaluatorServiceDTO> subscriber) {
		super(subscriber);
		this.queuePublisher = new StatisticHandTypeQueue();
	}

	@Override
	public StatisticHandTypeServiceDTO processSubscriber(HandEvaluatorServiceDTO subscriber) {

		StatisticHandTypeServiceDTO statisticHandTypeServiceDTO = new StatisticHandTypeServiceDTO();

		subscriber.getGameCrititalHandDataModelList().stream().forEach(gameCrititalHandDataModel -> {

			statisticHandTypeServiceDTO.addHand(gameCrititalHandDataModel.getGameCode(),
					gameCrititalHandDataModel.getHandDataModelList(),
					gameCrititalHandDataModel.getCriticalHandOutcomeEnum());
		});

		return statisticHandTypeServiceDTO;
	}

}