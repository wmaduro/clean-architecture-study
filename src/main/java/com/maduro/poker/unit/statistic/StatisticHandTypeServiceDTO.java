package com.maduro.poker.unit.statistic;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.maduro.poker.domain.CriticalHandOutcomeEnum;
import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.unit.base.IBaseEventBusDTO;

import lombok.Getter;

public class StatisticHandTypeServiceDTO implements IBaseEventBusDTO {
	
	@Getter
	private final Map<String, List<HandDataModel>> tiedHandsMap = new TreeMap<String, List<HandDataModel>>();
	@Getter
	private final Map<String, List<HandDataModel>> bestHandsWinMap = new TreeMap<String, List<HandDataModel>>();
	@Getter
	private final Map<String, List<HandDataModel>> worstHandsWinMap = new TreeMap<String, List<HandDataModel>>();

	
	public void addHand(String key, List<HandDataModel> value, CriticalHandOutcomeEnum criticalHandOutcomeEnum) {

		switch (criticalHandOutcomeEnum) {
		case BEST_WIN: {
			this.bestHandsWinMap.put(key, value);
			break;
		}
		case WORST_WIN: {
			this.worstHandsWinMap.put(key, value);
			break;
		}
		case TIED: {
			this.tiedHandsMap.put(key, value);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + criticalHandOutcomeEnum);
		}

	}
}
