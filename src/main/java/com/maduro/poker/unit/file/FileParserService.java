package com.maduro.poker.unit.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.unit.file.helper.FileParserServiceHelper;
import com.maduro.poker.util.Utils;

public class FileParserService {

	private String filePath;
	public FileParserService(String filePath) {
		this.filePath = filePath ;
	}
	public FileParserServiceDTO process() throws Exception {

		FileParserServiceDTO fileParserServiceDTO = new FileParserServiceDTO();

		try {

			BufferedReader br = new BufferedReader(new FileReader(FileParserServiceHelper.getFileFromPath(filePath)));

			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith(Utils.FILE_HEAD_START_WITH)) {
					continue;
				}
				HandDataModel handDataModel = Utils.parseLine(line);
				fileParserServiceDTO.addHandDataModel(handDataModel);
			}

			br.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return fileParserServiceDTO;
	}

}
