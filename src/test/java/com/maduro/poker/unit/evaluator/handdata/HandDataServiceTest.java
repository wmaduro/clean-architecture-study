package com.maduro.poker.unit.evaluator.handdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.maduro.poker.domain.CriticalHandOutcomeEnum;
import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.unit.evaluator.handdata.exception.WinnerHandNotFoundException;

public class HandDataServiceTest {

	@Test
	void exceptionMustBeThrownForFileNotProvided() {
		assertThrows(WinnerHandNotFoundException.class, () -> {
			new HandDataService().analyzeHands(Arrays.asList(HandDataModel.builder().build()));
		});
	}

	@Test
	void mustReturnTiedEnum() throws Exception {
		
		HandDataModel hand1 = HandDataModel.builder().build();
		hand1.setValue_won("100");
		hand1.setCard_sequence("AcAd");
		HandDataModel hand2 = HandDataModel.builder().build();
		hand2.setValue_won("100");
		hand2.setCard_sequence("AhAs");
		assertEquals(CriticalHandOutcomeEnum.TIED,
				new HandDataService().analyzeHands(Arrays.asList(hand1, hand2)));
	}
	
	
	@Test
	void mustReturnBestHandWonEnum() throws Exception {
		
		HandDataModel hand1 = HandDataModel.builder().build();
		hand1.setValue_won("100");
		hand1.setCard_sequence("AcAd");
		HandDataModel hand2 = HandDataModel.builder().build();
		hand2.setValue_won("");
		hand2.setCard_sequence("KhAs");
		assertEquals(CriticalHandOutcomeEnum.BEST_WIN,
				new HandDataService().analyzeHands(Arrays.asList(hand1, hand2)));
	}
	
	@Test
	void mustReturnWorstHandWonEnum() throws Exception {
		
		HandDataModel hand1 = HandDataModel.builder().build();
		hand1.setValue_won("0");
		hand1.setCard_sequence("AcAd");
		HandDataModel hand2 = HandDataModel.builder().build();
		hand2.setValue_won("100");
		hand2.setCard_sequence("KhAs");
		assertEquals(CriticalHandOutcomeEnum.WORST_WIN,
				new HandDataService().analyzeHands(Arrays.asList(hand1, hand2)));
	}

}
