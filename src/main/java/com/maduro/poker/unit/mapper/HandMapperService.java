package com.maduro.poker.unit.mapper;

import com.maduro.poker.base.queue.facade.IQueue;
import com.maduro.poker.base.service.BaseServiceFull;
import com.maduro.poker.unit.file.FileParserServiceDTO;
import com.maduro.poker.unit.mapper.queue.HandDataModelMapQueue;

public class HandMapperService extends BaseServiceFull<FileParserServiceDTO, HandMapperServiceDTO> {

	public HandMapperService(IQueue<FileParserServiceDTO> subscriber) {
		super(subscriber);
		this.queuePublisher = new HandDataModelMapQueue();
	}

	@Override
	public HandMapperServiceDTO processSubscriber(FileParserServiceDTO subscriber) {
		
		if (subscriber == null || subscriber.getHandDataModelList() == null)
			return null;

		HandMapperServiceDTO handMapperServiceDTO = new HandMapperServiceDTO();

		subscriber.getHandDataModelList().forEach(handDataModel -> {
			handMapperServiceDTO.mapHandDataModel(handDataModel);
		});

		return handMapperServiceDTO;
	}
}
