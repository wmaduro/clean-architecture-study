package com.maduro.poker.unit.statistic.view;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.maduro.poker.unit.base.BaseService;
import com.maduro.poker.unit.statistic.StatisticHandTypeServiceDTO;

public class StatisticHandTypeViewerSevice extends BaseService {

	public StatisticHandTypeViewerSevice(EventBus eventBus) {
		super(eventBus);
	}

	@Subscribe
	public void stringEvent(StatisticHandTypeServiceDTO event) {
		showResult(event);
	}

	public void showResult(StatisticHandTypeServiceDTO statisticHandTypeServiceDTO) {
		System.out.println("total tied: " + statisticHandTypeServiceDTO.getTiedHandsMap().size());
		System.out.println("total best: " + statisticHandTypeServiceDTO.getBestHandsWinMap().size());
		System.out.println("total worst: " + statisticHandTypeServiceDTO.getWorstHandsWinMap().size());
	}

}
