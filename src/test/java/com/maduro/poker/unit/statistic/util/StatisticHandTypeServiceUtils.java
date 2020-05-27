package com.maduro.poker.unit.statistic.util;

import java.util.ArrayList;
import java.util.List;

import com.maduro.poker.domain.CriticalHandOutcomeEnum;
import com.maduro.poker.domain.GameCrititalHandDataModel;

public class StatisticHandTypeServiceUtils {

	public static List<GameCrititalHandDataModel> getOneHandPerOutcomeEnumType() throws Exception {

		List<GameCrititalHandDataModel> handDataModelMapParserDTOList = new ArrayList<>();

		handDataModelMapParserDTOList.add(new GameCrititalHandDataModel("1", null, CriticalHandOutcomeEnum.BEST_WIN));
		handDataModelMapParserDTOList.add(new GameCrititalHandDataModel("1", null, CriticalHandOutcomeEnum.TIED));
		handDataModelMapParserDTOList.add(new GameCrititalHandDataModel("1", null, CriticalHandOutcomeEnum.WORST_WIN));

		return handDataModelMapParserDTOList;
	}

}
