package com.maduro.poker.unit.statistic;

import com.maduro.poker.unit.evaluator.HandEvaluatorServiceDTO;

public class StatisticHandTypeService {
	
	public StatisticHandTypeServiceDTO process(HandEvaluatorServiceDTO handEvaluatorServiceDTO) {
		
		StatisticHandTypeServiceDTO statisticHandTypeServiceDTO = new StatisticHandTypeServiceDTO();
		
		if (handEvaluatorServiceDTO != null) {
			handEvaluatorServiceDTO.getGameCrititalHandDataModelList().stream()
					.forEach(handDataModelMapParserDTO -> {
						statisticHandTypeServiceDTO.addHand(handDataModelMapParserDTO.getGameCode(),
								handDataModelMapParserDTO.getHandDataModelList(),
								handDataModelMapParserDTO.getCriticalHandOutcomeEnum());
					});
		}
		return statisticHandTypeServiceDTO;
	}

	

}