package com.maduro.poker.unit.mapper;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.maduro.poker.unit.base.BaseService;
import com.maduro.poker.unit.file.FileParserServiceDTO;

public class HandMapperService extends BaseService {

	public HandMapperService(EventBus eventBus) {
		super(eventBus);
	}

	@Subscribe
	public void stringEvent(FileParserServiceDTO event) {
		publish(process(event));
	}

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
