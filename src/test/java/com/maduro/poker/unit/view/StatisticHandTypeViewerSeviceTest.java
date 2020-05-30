package com.maduro.poker.unit.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.statistic.StatisticHandTypeServiceDTO;

class StatisticHandTypeViewerSeviceTest {

	@Mock
	protected StatisticHandTypeServiceDTO statisticHandTypeServiceDTO;
	@Mock
	protected EventBus eventBus;

//	@Test
//	public void must_Event_BeCaptured_Successfully() {
//
//		EventBus eventBus = Mockito.spy(new EventBus());
//
//		StatisticHandTypeViewerSevice statisticHandTypeViewerSeviceMock = Mockito.spy(new StatisticHandTypeViewerSevice(eventBus));
////		Executors.newCachedThreadPool().execute(statisticHandTypeViewerSeviceMock);
//
//		StatisticHandTypeServiceDTO event = Mockito.spy(new StatisticHandTypeServiceDTO());
//		eventBus.post(event);
//
////		assertTrue(statisticHandTypeViewerSevice.getInstantEventProcessed() != null);
//		Mockito.verify(statisticHandTypeViewerSeviceMock).showResult(null);
//	}

	@Test
	void must_ShowResult_BeCalled_Successfuly() throws Exception {

		StatisticHandTypeViewerSevice statisticHandTypeViewerSeviceMock = Mockito
				.spy(new StatisticHandTypeViewerSevice(null));

		statisticHandTypeViewerSeviceMock.processEvent(null);

		Mockito.verify(statisticHandTypeViewerSeviceMock).showResult(null);

	}
}
