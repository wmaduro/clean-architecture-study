package com.maduro.poker.unit.view;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.evaluator.HandEvaluatorServiceDTO;
import com.maduro.poker.unit.statistic.StatisticHandTypeServiceDTO;

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
	void must_ShowResult_BeCalled_Successfuly() throws Exception {

		StatisticHandTypeViewerSevice statisticHandTypeViewerSeviceMock = Mockito
				.spy(new StatisticHandTypeViewerSevice(null));

		statisticHandTypeViewerSeviceMock.processEvent(null);

		Mockito.verify(statisticHandTypeViewerSeviceMock).showResult(null);

	}
}
