package com.maduro.poker.unit.handtypeviewer;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.handtype.HandTypeServiceDTO;

class HandTypeViewerSeviceTest {

	@Mock
	protected HandTypeServiceDTO handTypeServiceDTO;
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

		HandTypeViewerSevice statisticHandTypeViewerSeviceMock = Mockito
				.spy(new HandTypeViewerSevice(null));

		statisticHandTypeViewerSeviceMock.processEvent(null);

		Mockito.verify(statisticHandTypeViewerSeviceMock).showResult(null);

	}
}
