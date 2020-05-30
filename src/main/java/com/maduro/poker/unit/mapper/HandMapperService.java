package com.maduro.poker.unit.mapper;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.base.BaseRunnableEventBusProcessEventService;
import com.maduro.poker.unit.base.IBaseEventBusDTO;
import com.maduro.poker.unit.file.FileParserServiceDTO;

public class HandMapperService extends BaseRunnableEventBusProcessEventService {

	public HandMapperService(EventBus eventBus) {
		super(eventBus);
	}

	@Override
	public void processEvent(IBaseEventBusDTO event) {
		if (event instanceof FileParserServiceDTO) {
			publish(process((FileParserServiceDTO) event));
		}

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
