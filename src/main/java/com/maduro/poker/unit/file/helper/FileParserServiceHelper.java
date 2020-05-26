package com.maduro.poker.unit.file.helper;

import java.io.File;

import com.maduro.poker.unit.file.exception.FileCannotBeDirectoryException;
import com.maduro.poker.unit.file.exception.FileNotExistsException;
import com.maduro.poker.unit.file.exception.FileNotProvidedException;

public class FileParserServiceHelper {

	public static File getFileFromPath(String filePath) {

		if (filePath == null || filePath.trim().isEmpty()) {
			throw new FileNotProvidedException();
		}

		File file = new File(filePath);

		if (!file.exists()) {
			throw new FileNotExistsException();
		}
		if (file.isDirectory()) {
			throw new FileCannotBeDirectoryException();
		}

		return file;
	}
}
