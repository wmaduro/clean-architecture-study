package com.maduro.poker.unit.view;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.maduro.poker.unit.base.BaseRunnableEventBusProcessEventService;
import com.maduro.poker.unit.base.IBaseEventBusDTO;
import com.maduro.poker.unit.statistic.StatisticHandTypeServiceDTO;

public class StatisticHandTypeViewerSevice extends BaseRunnableEventBusProcessEventService {

	public StatisticHandTypeViewerSevice(EventBus eventBus) {
		super(eventBus);
	}

	@Subscribe
	public void processEvent(IBaseEventBusDTO event) {
		System.out.println("StatisticHandTypeViewerSevice - "+event.getClass());

		if (event instanceof StatisticHandTypeServiceDTO) {
			showResult((StatisticHandTypeServiceDTO) event);
		}
	}

	public void showResult(StatisticHandTypeServiceDTO statisticHandTypeServiceDTO) {
		if (statisticHandTypeServiceDTO == null) {
			System.out.print("No statistics were processed. Data is null!");
			return;
		}
		System.out.println("total tied: " + statisticHandTypeServiceDTO.getTiedHandsMap().size());
		System.out.println("total best: " + statisticHandTypeServiceDTO.getBestHandsWinMap().size());
		System.out.println("total worst: " + statisticHandTypeServiceDTO.getWorstHandsWinMap().size());
	}

}
