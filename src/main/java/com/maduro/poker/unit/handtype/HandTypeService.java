package com.maduro.poker.unit.handtype;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.maduro.poker.unit.base.BaseRunnableEventBusProcessEventService;
import com.maduro.poker.unit.handevaluator.HandEvaluatorServiceDTO;

public class HandTypeService extends BaseRunnableEventBusProcessEventService<HandEvaluatorServiceDTO> {

	public HandTypeService(EventBus eventBus) {
		super(eventBus);
	}

	@Subscribe
	@Override
	public void processEvent(HandEvaluatorServiceDTO event) {
		publish(process(event));
	}

	public HandTypeServiceDTO process(HandEvaluatorServiceDTO handEvaluatorServiceDTO) {

		HandTypeServiceDTO handTypeServiceDTO = new HandTypeServiceDTO();

		handEvaluatorServiceDTO.getGameCrititalHandDataModelList().stream().forEach(gameCrititalHandDataModel -> {

			handTypeServiceDTO.addHand(gameCrititalHandDataModel.getGameCode(),
					gameCrititalHandDataModel.getHandDataModelList(),
					gameCrititalHandDataModel.getCriticalHandOutcomeEnum());
		});

		return handTypeServiceDTO;
	}

}