package com.maduro.poker.unit.handmapper;

import com.maduro.poker.unit.fileparser.FileParserServiceDTO;

public class HandMapperService {

	public HandMapperServiceDTO process(FileParserServiceDTO fileParserServiceDTO) {

		if (fileParserServiceDTO == null || fileParserServiceDTO.getHandDataModelList() == null)
			return null;

		HandMapperServiceDTO handMapperServiceDTO = new HandMapperServiceDTO();

		fileParserServiceDTO.getHandDataModelList().forEach(handDataModel -> {
			handMapperServiceDTO.mapHandDataModel(handDataModel);
		});

		return handMapperServiceDTO;
	}
}
