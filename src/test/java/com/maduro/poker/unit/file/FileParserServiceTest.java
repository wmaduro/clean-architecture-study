package com.maduro.poker.unit.file;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.file.util.FileParsrServiceUtils;
import com.maduro.poker.unit.folder.FolderMonitorServiceDTO;

@RunWith(MockitoJUnitRunner.class)
public class FileParserServiceTest {

	@TempDir
	File tempDir;

	@BeforeEach
	void setUp() throws Exception {
		Arrays.asList(tempDir.listFiles()).stream().forEach(file -> file.delete());
	}

	@Test
	public void must_Event_BeCaptured_Successfully() {

		EventBus eventBus = new EventBus();

		FileParserService fileParserService = new FileParserService(eventBus);
		Executors.newCachedThreadPool().execute(fileParserService);

		FolderMonitorServiceDTO event = new FolderMonitorServiceDTO();
		eventBus.post(event);

		assertTrue(fileParserService.getInstantEventProcessed() != null);

	}

	@Test
	public void must_ProcessFile_Successfully() throws Exception {

		File csvFile = FileParsrServiceUtils.createImportFile(tempDir, this.getClass().getCanonicalName());

		FileParserService fileParserService = new FileParserService(null);
		FolderMonitorServiceDTO folderMonitorServiceDTO = new FolderMonitorServiceDTO();
		folderMonitorServiceDTO.addFile(csvFile);

		FileParserServiceDTO fileParserServiceDTO = fileParserService.process(folderMonitorServiceDTO);

		assertTrue(fileParserServiceDTO.getHandDataModelList().size() == 2);

	}

}