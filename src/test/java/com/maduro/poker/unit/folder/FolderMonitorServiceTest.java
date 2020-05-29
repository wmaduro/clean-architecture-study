package com.maduro.poker.unit.folder;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.awaitility.Awaitility;
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
	void must_Rename_CSVFiles() throws IOException {
		
		final int TIMEOUT_TWO_SEC = 2;
		
		File csvFile = FolderMonitorServiceUtils.createImportFile(tempDir, this.getClass().getCanonicalName());
		String importFileName = Utils.removeFileExtension(csvFile.getAbsoluteFile().toString())
				+ FolderMonitorService.IMPORTING_EXTENSION;

		FolderMonitorService folderMonitorService = new FolderMonitorService(null, tempDir.toPath());
		Executors.newCachedThreadPool().execute(folderMonitorService);

		final File importFile = new File(importFileName);
		Awaitility.await().timeout(Duration.ofSeconds(TIMEOUT_TWO_SEC)).until(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return importFile.exists();
			}
		});

		assertTrue(new File(importFileName).exists());

	}

}
