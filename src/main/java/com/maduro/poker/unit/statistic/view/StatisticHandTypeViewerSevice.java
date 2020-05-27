package com.maduro.poker.unit.statistic.view;

import java.beans.PropertyChangeEvent;

import com.maduro.poker.base.queue.facade.IQueueSubscriber;
import com.maduro.poker.base.service.BaseServiceConsumer;
import com.maduro.poker.unit.statistic.StatisticHandTypeServiceDTO;

public class StatisticHandTypeViewerSevice extends BaseServiceConsumer<StatisticHandTypeServiceDTO> {

	public StatisticHandTypeViewerSevice(IQueueSubscriber<StatisticHandTypeServiceDTO> subscriber) {
		super(subscriber);
	}

	public void showResult(StatisticHandTypeServiceDTO statisticHandTypeServiceDTO) {
		System.out.println("total tied: " + statisticHandTypeServiceDTO.getTiedHandsMap().size());
		System.out.println("total best: " + statisticHandTypeServiceDTO.getBestHandsWinMap().size());
		System.out.println("total worst: " + statisticHandTypeServiceDTO.getWorstHandsWinMap().size());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		super.propertyChange(evt);
		showResult(result);
	}

}
