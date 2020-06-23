package com.maduro.poker.unit.handtypeviewer;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.maduro.poker.unit.base.BaseRunnableEventBusProcessEventService;
import com.maduro.poker.unit.handtype.HandTypeServiceDTO;

public class HandTypeViewerSevice
		extends BaseRunnableEventBusProcessEventService<HandTypeServiceDTO> {

	public HandTypeViewerSevice(EventBus eventBus) {
		super(eventBus);
	}

	@Subscribe
	@Override
	public void processEvent(HandTypeServiceDTO event) {
		showResult(event);
	}

	public void showResult(HandTypeServiceDTO handTypeServiceDTO) {
		if (handTypeServiceDTO == null) {
			return;
		}
		System.out.println("total tied: " + handTypeServiceDTO.getTiedHandsMap().size());
		System.out.println("total best: " + handTypeServiceDTO.getBestHandsWinMap().size());
		System.out.println("total worst: " + handTypeServiceDTO.getWorstHandsWinMap().size());
	}

}
