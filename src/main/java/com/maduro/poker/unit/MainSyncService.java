package com.maduro.poker.unit;

import com.maduro.poker.unit.fileparser.FileParserService;
import com.maduro.poker.unit.fileparser.FileParserServiceDTO;
import com.maduro.poker.unit.handevaluator.HandEvaluatorService;
import com.maduro.poker.unit.handevaluator.HandEvaluatorServiceDTO;
import com.maduro.poker.unit.handmapper.HandMapperService;
import com.maduro.poker.unit.handmapper.HandMapperServiceDTO;
import com.maduro.poker.unit.handtype.HandTypeService;
import com.maduro.poker.unit.handtype.HandTypeServiceDTO;
import com.maduro.poker.unit.handtypeviewer.HandTypeViewerSevice;

public class MainSyncService {

	public void process(
			FileParserService fileParserService, 
			HandMapperService handMapperService,
			HandEvaluatorService handEvaluatorService, 
			HandTypeService handTypeService, 
			HandTypeViewerSevice handTypeViewerSevice) throws Exception {

		FileParserServiceDTO fileParserServiceDTO = fileParserService.process();

		HandMapperServiceDTO handMapperServiceDTO = handMapperService.process(fileParserServiceDTO);

		HandEvaluatorServiceDTO handEvaluatorServiceDTO = handEvaluatorService.process(handMapperServiceDTO);

		HandTypeServiceDTO handTypeServiceDTO = handTypeService.process(handEvaluatorServiceDTO);

		handTypeViewerSevice.showResult(handTypeServiceDTO);

	}

}
