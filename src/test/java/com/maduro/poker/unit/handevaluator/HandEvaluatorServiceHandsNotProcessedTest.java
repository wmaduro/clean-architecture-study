package com.maduro.poker.unit.handevaluator;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.handevaluator.util.HandEvaluatorUtils;

public class HandEvaluatorServiceHandsNotProcessedTest extends HandEvaluatorBaseTest{
	@Test
	public void mustNotProcessingWhenActionIsNotPreFlop() throws Exception {
		must_NotProcessing_Successfuly(HandEvaluatorUtils.getActionIsNotPreFlopMap());
	}
	
	@Test
	public void mustNotProcessingWhenFindOnlyOneHand() throws Exception {
		must_NotProcessing_Successfuly(HandEvaluatorUtils.getOnlyOneHandMap());		
	}
	
	@Test
	public void mustNotProcessingWhenPlayerNotExist() throws Exception {
		must_NotProcessing_Successfuly(HandEvaluatorUtils.getMainPlayerNotExistMap());		
	}

	@Test
	public void mustNotProcessingWhenPlayerIsNotAggressor() throws Exception {
		must_NotProcessing_Successfuly(HandEvaluatorUtils.getMainPlayerNotAggressorMap());		
	}
	
	private void must_NotProcessing_Successfuly(Map<String, List<HandDataModel>> handMap) throws Exception {
		
		Mockito.when(handMapperServiceDTO.getHandDataModelMap()).thenReturn(handMap);
		
		HandEvaluatorServiceDTO handEvaluatorServiceDTO = new HandEvaluatorService(null, mainPlayerNameFilter,
				AggressivityBehaviorEnum.RAISER).process(handMapperServiceDTO);

		assertTrue(validate_NotProcessing_Outcome(handEvaluatorServiceDTO));
	
	}
	
	private boolean validate_NotProcessing_Outcome(HandEvaluatorServiceDTO handEvaluatorServiceDTO) {
		return handEvaluatorServiceDTO.getGameCrititalHandDataModelList().size() == 0;
	}
}
