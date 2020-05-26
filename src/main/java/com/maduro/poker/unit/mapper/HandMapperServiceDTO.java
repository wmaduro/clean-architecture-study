package com.maduro.poker.unit.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.maduro.poker.domain.HandDataModel;

import lombok.Getter;

public class HandMapperServiceDTO {
	@Getter
	final private Map<String, List<HandDataModel>> handDataModelMap = 
			 new TreeMap<>();
	
	public void mapHandDataModel(HandDataModel handDataModel) {
		if (!handDataModelMap.containsKey(handDataModel.getHand())) {
			handDataModelMap.put(handDataModel.getHand(), new ArrayList<HandDataModel>());
		}
		handDataModelMap.get(handDataModel.getHand()).add(handDataModel);
	}
	
}
