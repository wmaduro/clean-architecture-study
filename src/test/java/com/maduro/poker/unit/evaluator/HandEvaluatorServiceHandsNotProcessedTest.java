package com.maduro.poker.unit.evaluator;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.evaluator.util.HandEvaluatorUtils;

public class HandEvaluatorServiceHandsNotProcessedTest extends HandEvaluatorBaseTest{
	@Test
	public void mustNotProcessingWhenActionIsNotPreFlop() throws Exception {
		mustNotProcessingSuccessfuly(HandEvaluatorUtils.getActionIsNotPreFlopMap());
	}
	
	@Test
	public void mustNotProcessingWhenFindOnlyOneHand() throws Exception {
		mustNotProcessingSuccessfuly(HandEvaluatorUtils.getOnlyOneHandMap());		
	}
	
	@Test
	public void mustNotProcessingWhenPlayerNotExist() throws Exception {
		mustNotProcessingSuccessfuly(HandEvaluatorUtils.getMainPlayerNotExistMap());		
	}

	@Test
	public void mustNotProcessingWhenPlayerIsNotAggressor() throws Exception {
		mustNotProcessingSuccessfuly(HandEvaluatorUtils.getMainPlayerNotAggressorMap());		
	}
	
	private void mustNotProcessingSuccessfuly(Map<String, List<HandDataModel>> handMap) throws Exception {
		
		Mockito.when(handMapperServiceDTO.getHandDataModelMap()).thenReturn(handMap);
		
		HandEvaluatorServiceDTO handEvaluatorServiceDTO = new HandEvaluatorService(null, mainPlayerNameFilter,
				AggressivityBehaviorEnum.RAISER).process(handMapperServiceDTO);

		assertTrue(validateNotProcessingOutcome(handEvaluatorServiceDTO));
	
	}
	
	private boolean validateNotProcessingOutcome(HandEvaluatorServiceDTO handEvaluatorServiceDTO) {
		return handEvaluatorServiceDTO.getGameCrititalHandDataModelList().size() == 0;
	}
}
