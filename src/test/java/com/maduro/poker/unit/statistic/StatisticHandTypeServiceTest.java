package com.maduro.poker.unit.statistic;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.maduro.poker.unit.evaluator.HandEvaluatorServiceDTO;
import com.maduro.poker.unit.statistic.util.StatisticHandTypeServiceUtils;

class StatisticHandTypeServiceTest {

	@Mock
	protected HandEvaluatorServiceDTO handEvaluatorServiceDTO;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void mustGenerateStatisticSuccessfuly() throws Exception {

		when(handEvaluatorServiceDTO.getGameCrititalHandDataModelList())
				.thenReturn(StatisticHandTypeServiceUtils.getOneHandPerOutcomeEnumType());

		StatisticHandTypeServiceDTO statisticHandTypeServiceDTO = new StatisticHandTypeService()
				.process(handEvaluatorServiceDTO);

		assertTrue(validateOutcome(statisticHandTypeServiceDTO));
	}

	private boolean validateOutcome(StatisticHandTypeServiceDTO statisticHandTypeServiceDTO) {

		return statisticHandTypeServiceDTO.getBestHandsWinMap().size() == 1
				&& statisticHandTypeServiceDTO.getTiedHandsMap().size() == 1
				&& statisticHandTypeServiceDTO.getWorstHandsWinMap().size() == 1;
	}

}
