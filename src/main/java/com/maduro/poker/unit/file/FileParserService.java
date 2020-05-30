package com.maduro.poker.unit.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.unit.base.BaseRunnableEventBusProcessEventService;
import com.maduro.poker.unit.base.IBaseEventBusDTO;
import com.maduro.poker.unit.folder.FolderMonitorServiceDTO;

public class FileParserService extends BaseRunnableEventBusProcessEventService {

	public FileParserService(EventBus eventBus) {
		super(eventBus);
	}

	@Override
	public void processEvent(IBaseEventBusDTO event) {
		if (event instanceof FolderMonitorServiceDTO) {
			publish(process((FolderMonitorServiceDTO) event));
		}
	}

	public FileParserServiceDTO process(FolderMonitorServiceDTO folderMonitorServiceDTO) {

		if (folderMonitorServiceDTO == null || folderMonitorServiceDTO.getFile() == null) {
			return null;
		}

		FileParserServiceDTO fileParserDTO = new FileParserServiceDTO();

		final String HEAD = "\"game\"";
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(folderMonitorServiceDTO.getFile()));

			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith(HEAD)) {
					continue;
				}
				HandDataModel handDataModel = parseLine(line);
				if (handDataModel.hasAllValidFields()) {
					fileParserDTO.addHandDataModel(handDataModel);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileParserDTO;
	}

	private HandDataModel parseLine(String line) throws Exception {
		String[] fieldsFromFile = line.split(",");
		return (HandDataModel) setObjectFieldFromArray(HandDataModel.class, fieldsFromFile);
	};

	private <T> Object setObjectFieldFromArray(Class<T> clazz, String[] array) throws Exception {

		Object object = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {

			if (array.length > i) {

				Field field = fields[i];
				boolean accessible = field.isAccessible();
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

}
