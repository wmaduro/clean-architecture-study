package com.maduro.poker.unit.handtype;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.maduro.poker.unit.handevaluator.HandEvaluatorServiceDTO;
import com.maduro.poker.unit.handtype.util.HandTypeServiceUtils;

class HandTypeServiceTest {

	@Mock
	protected HandEvaluatorServiceDTO handEvaluatorServiceDTO;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void mustGenerateStatisticSuccessfuly() throws Exception {

		when(handEvaluatorServiceDTO.getGameCrititalHandDataModelList())
				.thenReturn(HandTypeServiceUtils.getOneHandPerOutcomeEnumType());

		HandTypeServiceDTO handTypeServiceDTO = new HandTypeService()
				.process(handEvaluatorServiceDTO);

		assertTrue(validateOutcome(handTypeServiceDTO));
	}

	private boolean validateOutcome(HandTypeServiceDTO handTypeServiceDTO) {

		return handTypeServiceDTO.getBestHandsWinMap().size() == 1
				&& handTypeServiceDTO.getTiedHandsMap().size() == 1
				&& handTypeServiceDTO.getWorstHandsWinMap().size() == 1;
	}

}
