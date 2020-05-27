package com.maduro.poker.unit.evaluator.util;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.util.DataUtils;

public class HandEvaluatorUtils {

	private static String[] getMainPlayerNotAggressor() {
		String[] lines = {
				"\"game\",\"hand\",\"hand_position\",\"user_name\",\"card_sequence\",\"value_won\",\"board\",\"all_in_action_street\",\"action_pre_flop\",\"value_action_pre_flop\",\"action_flop\",\"value_action_flop\",\"action_turn\",\"value_action_turn\",\"action_river\",\"value_action_river\",\"bb\",\"street_ended\",\"show_down\",\"level\"",
				"\"2655675429   \",\"202713458879 \",2,zloipitbull2212,\"9c 9s\",,,PRE_FLOP,RAISE,4660,,,,,,,61,RIVER,true,5",
				"\"2655675429   \",\"202713458879 \",2,wmaduro,Ts Tc,10170,,PRE_FLOP,CALL,4900,,,,,,,61,RIVER,true,5" };

		return lines;
	}

	private static String[] getMainPlayerNotExist() {
		String[] lines = {
				"\"game\",\"hand\",\"hand_position\",\"user_name\",\"card_sequence\",\"value_won\",\"board\",\"all_in_action_street\",\"action_pre_flop\",\"value_action_pre_flop\",\"action_flop\",\"value_action_flop\",\"action_turn\",\"value_action_turn\",\"action_river\",\"value_action_river\",\"bb\",\"street_ended\",\"show_down\",\"level\"",
				"\"2655675429   \",\"202713458879 \",2,zloipitbull2212,\"Th Td\",10,,FLOP,CALL,4660,,,,,,,61,RIVER,true,5",
				"\"2655675429   \",\"202713458879 \",2,joao,Ts Tc,10,,FLOP,RAISE,4900,,,,,,,61,RIVER,true,5" };

		return lines;
	}

	private static String[] getActionIsNotPreFlop() {
		String[] lines = {
				"\"game\",\"hand\",\"hand_position\",\"user_name\",\"card_sequence\",\"value_won\",\"board\",\"all_in_action_street\",\"action_pre_flop\",\"value_action_pre_flop\",\"action_flop\",\"value_action_flop\",\"action_turn\",\"value_action_turn\",\"action_river\",\"value_action_river\",\"bb\",\"street_ended\",\"show_down\",\"level\"",
				"\"2655675429   \",\"202713458879 \",2,zloipitbull2212,\"Th Td\",10,,FLOP,CALL,4660,,,,,,,61,RIVER,true,5",
				"\"2655675429   \",\"202713458879 \",2,wmaduro,Ts Tc,10,,FLOP,RAISE,4900,,,,,,,61,RIVER,true,5" };

		return lines;
	}

	private static String[] getOnlyOneHand() {
		String[] lines = {
				"\"game\",\"hand\",\"hand_position\",\"user_name\",\"card_sequence\",\"value_won\",\"board\",\"all_in_action_street\",\"action_pre_flop\",\"value_action_pre_flop\",\"action_flop\",\"value_action_flop\",\"action_turn\",\"value_action_turn\",\"action_river\",\"value_action_river\",\"bb\",\"street_ended\",\"show_down\",\"level\"",
				"\"2655675429   \",\"202713458879 \",2,wmaduro,Ts Tc,10,,PRE_FLOP,RAISE,4900,,,,,,,61,RIVER,true,5" };

		return lines;
	}

	private static String[] getTiedHands() {
		String[] lines = {
				"\"game\",\"hand\",\"hand_position\",\"user_name\",\"card_sequence\",\"value_won\",\"board\",\"all_in_action_street\",\"action_pre_flop\",\"value_action_pre_flop\",\"action_flop\",\"value_action_flop\",\"action_turn\",\"value_action_turn\",\"action_river\",\"value_action_river\",\"bb\",\"street_ended\",\"show_down\",\"level\"",
				"\"2655675429   \",\"202713458879 \",2,zloipitbull2212,\"Th Td\",10,,PRE_FLOP,CALL,4660,,,,,,,61,RIVER,true,5",
				"\"2655675429   \",\"202713458879 \",2,wmaduro,Ts Tc,10,,PRE_FLOP,RAISE,4900,,,,,,,61,RIVER,true,5" };

		return lines;
	}

	private static String[] getBestHandWins() {

		String[] lines = {
				"\"game\",\"hand\",\"hand_position\",\"user_name\",\"card_sequence\",\"value_won\",\"board\",\"all_in_action_street\",\"action_pre_flop\",\"value_action_pre_flop\",\"action_flop\",\"value_action_flop\",\"action_turn\",\"value_action_turn\",\"action_river\",\"value_action_river\",\"bb\",\"street_ended\",\"show_down\",\"level\"",
				"\"2655675429   \",\"202713458879 \",2,zloipitbull2212,\"9c 9s\",,,PRE_FLOP,CALL,4660,,,,,,,61,RIVER,true,5",
				"\"2655675429   \",\"202713458879 \",2,wmaduro,Ts Tc,10170,,PRE_FLOP,RAISE,4900,,,,,,,61,RIVER,true,5" };

		return lines;
	}

	private static String[] getWorstHandWins() {

		String[] lines = {
				"\"game\",\"hand\",\"hand_position\",\"user_name\",\"card_sequence\",\"value_won\",\"board\",\"all_in_action_street\",\"action_pre_flop\",\"value_action_pre_flop\",\"action_flop\",\"value_action_flop\",\"action_turn\",\"value_action_turn\",\"action_river\",\"value_action_river\",\"bb\",\"street_ended\",\"show_down\",\"level\"",
				"\"2655675429   \",\"202713458879 \",2,zloipitbull2212,\"9c 9s\",100,,PRE_FLOP,CALL,4660,,,,,,,61,RIVER,true,5",
				"\"2655675429   \",\"202713458879 \",2,wmaduro,Ts Tc,,,PRE_FLOP,RAISE,4900,,,,,,,61,RIVER,true,5" };

		return lines;
	}

	public static Map<String, List<HandDataModel>> getWorstHandWinMap() throws Exception {
		return getHandsMap(getWorstHandWins());
	}

	public static Map<String, List<HandDataModel>> getBestHandWinMap() throws Exception {
		return getHandsMap(getBestHandWins());
	}

	private static Map<String, List<HandDataModel>> getHandsMap(String[] hands) throws Exception {
		Map<String, List<HandDataModel>> handDataModelMap = new TreeMap<>();

		List<HandDataModel> handDataModelList = DataUtils.getHandDataModelList(hands);

		String handCode = handDataModelList.get(0).getHand();
		handDataModelMap.put(handCode, handDataModelList);

		return handDataModelMap;
	}

	public static Map<String, List<HandDataModel>> getTiedHandWinMap() throws Exception {
		return getHandsMap(getTiedHands());
	}

	public static Map<String, List<HandDataModel>> getOnlyOneHandMap() throws Exception {
		return getHandsMap(getOnlyOneHand());
	}

	public static Map<String, List<HandDataModel>> getActionIsNotPreFlopMap() throws Exception {
		return getHandsMap(getActionIsNotPreFlop());
	}

	public static Map<String, List<HandDataModel>> getMainPlayerNotExistMap() throws Exception {
		return getHandsMap(getMainPlayerNotExist());
	}

	public static Map<String, List<HandDataModel>> getMainPlayerNotAggressorMap() throws Exception {
		return getHandsMap(getMainPlayerNotAggressor());
	}

}
