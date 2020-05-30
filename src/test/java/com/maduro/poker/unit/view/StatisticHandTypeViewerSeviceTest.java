package com.maduro.poker.unit.view;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.evaluator.HandEvaluatorServiceDTO;
import com.maduro.poker.unit.statistic.StatisticHandTypeService;
import com.maduro.poker.unit.statistic.StatisticHandTypeServiceDTO;
import com.maduro.poker.unit.statistic.util.StatisticHandTypeServiceUtils;
import com.maduro.poker.unit.view.StatisticHandTypeViewerSevice;

class StatisticHandTypeViewerSeviceTest {

	@Mock
	protected StatisticHandTypeServiceDTO statisticHandTypeServiceDTO;
	@Mock
	protected EventBus eventBus;
	@Test
	public void must_Event_BeCaptured_Successfully() {

		EventBus eventBus = new EventBus();

		StatisticHandTypeViewerSevice statisticHandTypeViewerSevice = new StatisticHandTypeViewerSevice(eventBus);
		Executors.newCachedThreadPool().execute(statisticHandTypeViewerSevice);

		HandEvaluatorServiceDTO event = new HandEvaluatorServiceDTO();
		eventBus.post(event);

		assertTrue(statisticHandTypeViewerSevice.getInstantEventProcessed() != null);

	}
	
	
	@Test
	void must_ProcessStatistic_Successfuly() throws Exception {
		
//		Mockito.when(statisticHandTypeServiceDTO.getBestHandsWinMap())
//				.thenReturn(StatisticHandTypeServiceUtils.getOneHandPerOutcomeEnumType());
//
//		new StatisticHandTypeViewerSevice(eventBus).showResult(statisticHandTypeServiceDTO);
//
//		assertTrue(validateOutcome(statisticHandTypeServiceDTO));
	}
	
	private boolean validateOutcome(StatisticHandTypeServiceDTO statisticHandTypeServiceDTO) {

		return statisticHandTypeServiceDTO.getBestHandsWinMap().size() == 1
				&& statisticHandTypeServiceDTO.getTiedHandsMap().size() == 1
				&& statisticHandTypeServiceDTO.getWorstHandsWinMap().size() == 1;
	}

	
}
