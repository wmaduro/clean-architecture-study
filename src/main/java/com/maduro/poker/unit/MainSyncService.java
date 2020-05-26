package com.maduro.poker.unit;

import com.maduro.poker.unit.evaluator.HandEvaluatorService;
import com.maduro.poker.unit.evaluator.HandEvaluatorServiceDTO;
import com.maduro.poker.unit.file.FileParserService;
import com.maduro.poker.unit.file.FileParserServiceDTO;
import com.maduro.poker.unit.mapper.HandMapperService;
import com.maduro.poker.unit.mapper.HandMapperServiceDTO;
import com.maduro.poker.unit.statistic.StatisticHandTypeService;
import com.maduro.poker.unit.statistic.StatisticHandTypeServiceDTO;
import com.maduro.poker.unit.statistic.view.StatisticHandTypeViewerSevice;

public class MainSyncService {

	public void process(
			FileParserService fileParserService, 
			HandMapperService handMapperService,
			HandEvaluatorService handEvaluatorService, 
			StatisticHandTypeService statisticHandTypeService, 
			StatisticHandTypeViewerSevice statisticHandTypeViewerSevice) throws Exception {

		FileParserServiceDTO fileParserServiceDTO = fileParserService.process();

		HandMapperServiceDTO handMapperServiceDTO = handMapperService.process(fileParserServiceDTO);

		HandEvaluatorServiceDTO handEvaluatorServiceDTO = handEvaluatorService.process(handMapperServiceDTO);

		StatisticHandTypeServiceDTO statisticHandTypeServiceDTO = statisticHandTypeService.process(handEvaluatorServiceDTO);

		statisticHandTypeViewerSevice.showResult(statisticHandTypeServiceDTO);

	}

}
