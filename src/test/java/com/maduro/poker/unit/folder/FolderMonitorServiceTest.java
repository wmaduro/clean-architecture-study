package com.maduro.poker.unit.folder;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.maduro.poker.unit.folder.util.FolderMonitorServiceUtils;
import com.maduro.poker.util.Utils;

class FolderMonitorServiceTest {

	@TempDir
	File tempDir;

	@BeforeEach
	void setUp() throws Exception {
		Arrays.asList(tempDir.listFiles()).stream().forEach(file -> file.delete());
	}

	@Test
	void mustRenameCSVFiles() throws IOException {

		File csvFile = FolderMonitorServiceUtils.createImportFile(tempDir, this.getClass().getCanonicalName());

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		FolderMonitorService folderMonitorService = new FolderMonitorService(null, tempDir.toPath());
		Executors.newCachedThreadPool().submit(folderMonitorService);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String importFileName = csvFile.getAbsoluteFile().toString();
		importFileName = Utils.removeFileExtension(importFileName) + FolderMonitorService.IMPORTING_EXTENSION;

		assertTrue(new File(importFileName).exists());

	}

}
