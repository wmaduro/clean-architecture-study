package com.maduro.poker.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.maduro.poker.unit.evaluator.HandEvaluatorService;
import com.maduro.poker.unit.file.FileParserService;
import com.maduro.poker.unit.mapper.HandMapperService;
import com.maduro.poker.unit.statistic.StatisticHandTypeService;
import com.maduro.poker.unit.statistic.view.StatisticHandTypeViewerSevice;

public class MainSyncServiceTest {

	@Mock
	private FileParserService fileParserService;
	@Mock
	private HandMapperService handMapperService;
	@Mock
	private HandEvaluatorService handEvaluatorService;
	@Mock
	private StatisticHandTypeService statisticHandTypeService;
	@Mock
	private StatisticHandTypeViewerSevice statisticViewService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAllServicesAreCalled() throws Exception {
		
		new MainSyncService().process(fileParserService, handMapperService, handEvaluatorService, statisticHandTypeService, statisticViewService);
		 
		Mockito.verify(fileParserService).process();
		Mockito.verify(handMapperService).process(null);
		Mockito.verify(handEvaluatorService).process(null);
		Mockito.verify(statisticHandTypeService).process(null);
		Mockito.verify(statisticHandTypeService).process(null);
		Mockito.verify(statisticViewService).showResult(null);

	}

}
