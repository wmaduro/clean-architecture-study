package com.maduro.poker.unit.evaluator;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.mapper.HandMapperServiceDTO;

public abstract class HandEvaluatorBaseTest {

	@Mock
	protected HandMapperServiceDTO handMapperServiceDTO;

	protected final String mainPlayerNameFilter = "wmaduro";

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void must_Event_BeCaptured_Successfully() {

		EventBus eventBus = new EventBus();

		HandEvaluatorService handEvaluatorService = new HandEvaluatorService(eventBus, null, null);
		Executors.newCachedThreadPool().execute(handEvaluatorService);

		HandMapperServiceDTO event = new HandMapperServiceDTO();
		eventBus.post(event);

		assertTrue(handEvaluatorService.getInstantEventProcessed() != null);

	}
}
