package com.maduro.poker.unit.statistic;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.evaluator.HandEvaluatorServiceDTO;
import com.maduro.poker.unit.statistic.util.StatisticHandTypeServiceUtils;


class StatisticHandTypeServiceTest {

	@Mock
	protected HandEvaluatorServiceDTO handEvaluatorServiceDTO;
	@Mock
	protected EventBus eventBus;
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void must_Event_BeCaptured_Successfully() {

		EventBus eventBus = new EventBus();

		StatisticHandTypeService statisticHandTypeService = 
				new StatisticHandTypeService(eventBus);
		Executors.newCachedThreadPool().execute(statisticHandTypeService);

		HandEvaluatorServiceDTO event = new HandEvaluatorServiceDTO();
		eventBus.post(event);

		assertTrue(statisticHandTypeService.getInstantEventProcessed() != null);

	}

	@Test
	void must_GenerateStatistic_Successfuly() throws Exception {

		Mockito.when(handEvaluatorServiceDTO.getGameCrititalHandDataModelList())
				.thenReturn(StatisticHandTypeServiceUtils.getOneHandPerOutcomeEnumType());

		StatisticHandTypeServiceDTO statisticHandTypeServiceDTO = 
				new StatisticHandTypeService(eventBus)
				.process(handEvaluatorServiceDTO);

		assertTrue(validateOutcome(statisticHandTypeServiceDTO));
	}

	private boolean validateOutcome(StatisticHandTypeServiceDTO statisticHandTypeServiceDTO) {

		return statisticHandTypeServiceDTO.getBestHandsWinMap().size() == 1
				&& statisticHandTypeServiceDTO.getTiedHandsMap().size() == 1
				&& statisticHandTypeServiceDTO.getWorstHandsWinMap().size() == 1;
	}

}
