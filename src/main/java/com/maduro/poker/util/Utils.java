package com.maduro.poker.util;

import java.lang.reflect.Field;

import com.maduro.poker.domain.HandDataModel;

public class Utils {
	public static String FIELD_LINE_SEPARATOR = ",";
	public static String FILE_HEAD_START_WITH = "\"game\"";

	private static <T> Object setObjectFieldFromArray(Class<T> clazz, String[] array) throws Exception {

		Object object = clazz.getDeclaredConstructor().newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {

			if (array.length > i) {

				Field field = fields[i];
				boolean accessible = field.isAccessible();// canAccess(object);
				field.setAccessible(true);

				String value = array[i];
				if (!field.getName().equals("user_name")) {
					value = value.replace("\"", "").replace(" ", "");
				}
				field.set(object, value);

				field.setAccessible(accessible);
			}
		}
		return object;

	}
	
	public static HandDataModel parseLine(String line) throws Exception {
		String[] fieldsFromFile = line.split(Utils.FIELD_LINE_SEPARATOR);
		return (HandDataModel) Utils.setObjectFieldFromArray(HandDataModel.class, fieldsFromFile);
	};
}
