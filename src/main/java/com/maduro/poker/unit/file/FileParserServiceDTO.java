package com.maduro.poker.unit.file;

import java.util.ArrayList;
import java.util.List;

import com.maduro.poker.domain.HandDataModel;

import lombok.Getter;

public class FileParserServiceDTO {
	@Getter
	final private List<HandDataModel> handDataModelList = new ArrayList<>();
	
	public void addHandDataModel(HandDataModel handDataModel) {
		this.handDataModelList.add(handDataModel);
	}
	
}
