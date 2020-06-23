package com.maduro.poker.unit.fileparser.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.util.Utils;

public class FileParsrServiceUtils {
	public static File createImportFile(File tempDir, String filePrefix) throws IOException {

		File file = File.createTempFile(filePrefix, "", tempDir);

		for (String line : getHandDataModelRawDataWithOneHand()) {
			Files.write(file.toPath(), (line + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
		}

		return file;
	}

	private static String[] getHandDataModelRawDataWithOneHand() {

		String[] lines = {
				"\"game\",\"hand\",\"hand_position\",\"user_name\",\"card_sequence\",\"value_won\",\"board\",\"all_in_action_street\",\"action_pre_flop\",\"value_action_pre_flop\",\"action_flop\",\"value_action_flop\",\"action_turn\",\"value_action_turn\",\"action_river\",\"value_action_river\",\"bb\",\"street_ended\",\"show_down\",\"level\"",
				"\"2655675429   \",\"202713458879 \",2,zloipitbull2212,\"9c 9s\",,,PRE_FLOP,CALL,4660,,,,,,,61,RIVER,true,5",
				"\"2655675429   \",\"202713458879 \",2,wmaduro,Ts Tc,10170,,PRE_FLOP,RAISE,4900,,,,,,,61,RIVER,true,5" };

		return lines;
	}
	
	public static boolean checkFieldValues(String line, HandDataModel handDataModel)
			throws IllegalArgumentException, IllegalAccessException {

		String[] lineValues = line.split(Utils.FIELD_LINE_SEPARATOR);
		Field[] fields = HandDataModel.class.getDeclaredFields();

		for (int i = 0; i < lineValues.length; i++) {

			Field field = fields[i];
			boolean accessible = field.isAccessible(); //canAccess(handDataModel);
			field.setAccessible(true);

			String handDataModelFieldValue = ((String) field.get(handDataModel)).trim();

			field.setAccessible(accessible);

			String lineValueCleanned = lineValues[i].replace("\"", "").replace(" ", "");
			if (!lineValueCleanned.equals(handDataModelFieldValue)) {
				return false;
			}
		}

		return true;
	}

}
