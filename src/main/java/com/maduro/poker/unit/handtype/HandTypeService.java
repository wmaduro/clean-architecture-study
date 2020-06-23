package com.maduro.poker.unit.handtype;

import com.maduro.poker.unit.handevaluator.HandEvaluatorServiceDTO;

public class HandTypeService {
	
	public HandTypeServiceDTO process(HandEvaluatorServiceDTO handEvaluatorServiceDTO) {
		
		HandTypeServiceDTO handTypeServiceDTO = new HandTypeServiceDTO();
		
		if (handEvaluatorServiceDTO != null) {
			handEvaluatorServiceDTO.getGameCrititalHandDataModelList().stream()
					.forEach(handDataModelMapParserDTO -> {
						handTypeServiceDTO.addHand(handDataModelMapParserDTO.getGameCode(),
								handDataModelMapParserDTO.getHandDataModelList(),
								handDataModelMapParserDTO.getCriticalHandOutcomeEnum());
					});
		}
		return handTypeServiceDTO;
	}

	

}