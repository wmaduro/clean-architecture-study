package com.maduro.poker.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.maduro.poker.unit.fileparser.FileParserService;
import com.maduro.poker.unit.handevaluator.HandEvaluatorService;
import com.maduro.poker.unit.handmapper.HandMapperService;
import com.maduro.poker.unit.handtype.HandTypeService;
import com.maduro.poker.unit.handtypeviewer.HandTypeViewerSevice;

public class MainSyncServiceTest {

	@Mock
	private FileParserService fileParserService;
	@Mock
	private HandMapperService handMapperService;
	@Mock
	private HandEvaluatorService handEvaluatorService;
	@Mock
	private HandTypeService handTypeService;
	@Mock
	private HandTypeViewerSevice statisticViewService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAllServicesAreCalled() throws Exception {
		
		new MainSyncService().process(fileParserService, handMapperService, handEvaluatorService, handTypeService, statisticViewService);
		 
		Mockito.verify(fileParserService).process();
		Mockito.verify(handMapperService).process(null);
		Mockito.verify(handEvaluatorService).process(null);
		Mockito.verify(handTypeService).process(null);
		Mockito.verify(handTypeService).process(null);
		Mockito.verify(statisticViewService).showResult(null);

	}

}
