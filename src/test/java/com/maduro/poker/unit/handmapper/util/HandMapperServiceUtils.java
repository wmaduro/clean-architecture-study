package com.maduro.poker.unit.handmapper.util;

import com.maduro.poker.unit.fileparser.FileParserServiceDTO;
import com.maduro.poker.util.DataUtils;

public class HandMapperServiceUtils {
	private static String[] getHandDataModelRawDataWithOneHand() {

		String[] lines = {
				"\"game\",\"hand\",\"hand_position\",\"user_name\",\"card_sequence\",\"value_won\",\"board\",\"all_in_action_street\",\"action_pre_flop\",\"value_action_pre_flop\",\"action_flop\",\"value_action_flop\",\"action_turn\",\"value_action_turn\",\"action_river\",\"value_action_river\",\"bb\",\"street_ended\",\"show_down\",\"level\"",
				"\"2655675429   \",\"202713458879 \",2,zloipitbull2212,\"9c 9s\",,,PRE_FLOP,CALL,4660,,,,,,,61,RIVER,true,5",
				"\"2655675429   \",\"202713458879 \",2,wmaduro,Ts Tc,10170,,PRE_FLOP,RAISE,4900,,,,,,,61,RIVER,true,5" };

		return lines;
	}

	public static FileParserServiceDTO getFileParserServiceDTOWithOneHand() throws Exception {

		FileParserServiceDTO fileParserServiceDTO = new FileParserServiceDTO();

		DataUtils.getHandDataModelList(getHandDataModelRawDataWithOneHand()).forEach(handDataModel -> {
			fileParserServiceDTO.addHandDataModel(handDataModel);
		});

		return fileParserServiceDTO;

	}
}
