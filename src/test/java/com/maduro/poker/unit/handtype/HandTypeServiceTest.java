package com.maduro.poker.unit.handtype;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.handevaluator.HandEvaluatorServiceDTO;
import com.maduro.poker.unit.handtype.HandTypeService;
import com.maduro.poker.unit.handtype.HandTypeServiceDTO;
import com.maduro.poker.unit.handtype.util.HandTypeServiceUtils;


class HandTypeServiceTest {

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

		HandTypeService handTypeService = 
				new HandTypeService(eventBus);
		Executors.newCachedThreadPool().execute(handTypeService);

		HandEvaluatorServiceDTO event = new HandEvaluatorServiceDTO();
		eventBus.post(event);

		assertTrue(handTypeService.getInstantPublishCalled() != null);

	}

	@Test
	void must_GenerateStatistic_Successfuly() throws Exception {

		Mockito.when(handEvaluatorServiceDTO.getGameCrititalHandDataModelList())
				.thenReturn(HandTypeServiceUtils.getOneHandPerOutcomeEnumType());

		HandTypeServiceDTO handTypeServiceDTO = 
				new HandTypeService(eventBus)
				.process(handEvaluatorServiceDTO);

		assertTrue(validateOutcome(handTypeServiceDTO));
	}

	private boolean validateOutcome(HandTypeServiceDTO handTypeServiceDTO) {

		return handTypeServiceDTO.getBestHandsWinMap().size() == 1
				&& handTypeServiceDTO.getTiedHandsMap().size() == 1
				&& handTypeServiceDTO.getWorstHandsWinMap().size() == 1;
	}

}
