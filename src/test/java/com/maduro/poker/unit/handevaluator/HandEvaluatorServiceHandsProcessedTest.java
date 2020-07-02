package com.maduro.poker.unit.handevaluator;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.maduro.poker.domain.CriticalHandOutcomeEnum;
import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.handevaluator.util.HandEvaluatorUtils;
import com.maduro.poker.unit.handmapper.HandMapperServiceDTO;

public class HandEvaluatorServiceHandsProcessedTest extends HandEvaluatorBaseTest {

	@Test
	public void mustProcessBestHandWinSuccessfuly() throws Exception {
		must_ProcessHandWin_Successfuly(HandEvaluatorUtils.getBestHandWinMap(), CriticalHandOutcomeEnum.BEST_WIN);
	}

	@Test
	public void mustProcessWorstHandWinSuccessfuly() throws Exception {
		must_ProcessHandWin_Successfuly(HandEvaluatorUtils.getWorstHandWinMap(), CriticalHandOutcomeEnum.WORST_WIN);
	}

	@Test
	public void mustProcessTiedSuccessfuly() throws Exception {
		must_ProcessHandWin_Successfuly(HandEvaluatorUtils.getTiedHandWinMap(), CriticalHandOutcomeEnum.TIED);
	}

	private void must_ProcessHandWin_Successfuly(Map<String, List<HandDataModel>> handMap,
			CriticalHandOutcomeEnum criticalHandOutcomeEnum) throws Exception {

		when(handMapperServiceDTO.getHandDataModelMap()).thenReturn(handMap);

		HandEvaluatorServiceDTO handEvaluatorServiceDTO = new HandEvaluatorService(null, mainPlayerNameFilter,
				AggressivityBehaviorEnum.RAISER).process(handMapperServiceDTO);

		assertTrue(validateProcessHandWinSuccessfulyOutcome(handMapperServiceDTO, handEvaluatorServiceDTO,
				criticalHandOutcomeEnum));

	}

	private boolean validateProcessHandWinSuccessfulyOutcome(HandMapperServiceDTO handMapperServiceDTO,
			HandEvaluatorServiceDTO handEvaluatorServiceDTO, CriticalHandOutcomeEnum criticalHandOutcomeEnum) {

		if (handEvaluatorServiceDTO.getGameCrititalHandDataModelList().size() != 1) {
			return false;
		}

		boolean bestHandWin = handEvaluatorServiceDTO.getGameCrititalHandDataModelList().get(0)
				.getCriticalHandOutcomeEnum().equals(criticalHandOutcomeEnum);

		return bestHandWin;
	}

}
