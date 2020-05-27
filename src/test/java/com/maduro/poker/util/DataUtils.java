package com.maduro.poker.util;

import java.util.ArrayList;
import java.util.List;

import com.maduro.poker.domain.HandDataModel;

public class DataUtils {
	public static List<HandDataModel> getHandDataModelList(String[] lines) throws Exception {
		List<HandDataModel> handDataModelList = new ArrayList<>();
		for (String line : lines) {
			if (!line.startsWith(Utils.FILE_HEAD_START_WITH)) {
				HandDataModel handDataModel = Utils.parseLine(line);
				handDataModelList.add(handDataModel);
			}
		}
		return handDataModelList;
	}
}
