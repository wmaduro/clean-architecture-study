package com.maduro.poker.unit.file;

import java.util.ArrayList;
import java.util.List;

import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.unit.base.IBaseEventBusDTO;

import lombok.Getter;

public class FileParserServiceDTO implements IBaseEventBusDTO {
	@Getter
	final private List<HandDataModel> handDataModelList = new ArrayList<>();
	
	public void addHandDataModel(HandDataModel handDataModel) {
		this.handDataModelList.add(handDataModel);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		this.handDataModelList.stream().forEach(handDataModel -> {
			sb.append(handDataModel.getGame() + " | " + handDataModel.getHand() +"\n");
		});
		return sb.toString();
	}
}
