package com.maduro.poker.unit.handmapper;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.maduro.poker.unit.base.BaseRunnableEventBusProcessEventService;
import com.maduro.poker.unit.fileparser.FileParserServiceDTO;

public class HandMapperService extends BaseRunnableEventBusProcessEventService<FileParserServiceDTO> {

	public HandMapperService(EventBus eventBus) {
		super(eventBus);
	}

	@Subscribe
	@Override
	public void processEvent(FileParserServiceDTO event) {
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
