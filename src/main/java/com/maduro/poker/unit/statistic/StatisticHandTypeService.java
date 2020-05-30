package com.maduro.poker.unit.statistic;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.maduro.poker.unit.base.BaseRunnableEventBusProcessEventService;
import com.maduro.poker.unit.base.IBaseEventBusDTO;
import com.maduro.poker.unit.evaluator.HandEvaluatorServiceDTO;

public class StatisticHandTypeService extends BaseRunnableEventBusProcessEventService {

	public StatisticHandTypeService(EventBus eventBus) {
		super(eventBus);
	}

	@Subscribe
	public void processEvent(IBaseEventBusDTO event) {
		System.out.println("StatisticHandTypeViewerSevice - "+event.getClass());
		if (event instanceof HandEvaluatorServiceDTO) {
			publish(process((HandEvaluatorServiceDTO) event));
		}
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