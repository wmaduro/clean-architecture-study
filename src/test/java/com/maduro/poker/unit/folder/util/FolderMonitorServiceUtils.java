package com.maduro.poker.unit.folder.util;

import java.io.File;
import java.io.IOException;

public class FolderMonitorServiceUtils {

	public static File createImportFile(File tempDir, String filePrefix) throws IOException {
		return File.createTempFile(filePrefix, ".csv", tempDir);
	}

}
