package com.maduro.poker.unit.fileparser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.unit.base.BaseTempDirServiceTest;
import com.maduro.poker.unit.fileparser.util.FileParsrServiceUtils;
import com.maduro.poker.unit.foldermonitor.FolderMonitorServiceDTO;

public class FileParserServiceTest extends BaseTempDirServiceTest {

	@Test
	public void must_Event_BeCaptured_Successfully() {

		EventBus eventBus = new EventBus();

		FileParserService fileParserService = new FileParserService(eventBus);
		Executors.newCachedThreadPool().execute(fileParserService);

		FolderMonitorServiceDTO event = new FolderMonitorServiceDTO();
		eventBus.post(event);

		assertTrue(fileParserService.getInstantPublishCalled() != null);

	}

	@Test
	public void must_ProcessFile_Successfully() throws Exception {

		File csvFile = FileParsrServiceUtils.createImportFile(tempDir, this.getClass().getCanonicalName());

		FileParserService fileParserService = new FileParserService(null);
		FolderMonitorServiceDTO folderMonitorServiceDTO = new FolderMonitorServiceDTO();
		folderMonitorServiceDTO.addFile(csvFile);

		FileParserServiceDTO fileParserServiceDTO = fileParserService.process(folderMonitorServiceDTO);

		assertTrue(validateOutcome(csvFile, fileParserServiceDTO));
	}

	private boolean validateOutcome(File file, FileParserServiceDTO fileParserServiceDTO)
			throws IOException, IllegalArgumentException, IllegalAccessException {

		List<String> lines = Files.readAllLines(file.toPath());

		for (int i = 0; i < lines.size(); i++) {

			if (i > 0) {
				String line = lines.get(i);
				HandDataModel handDataModel = fileParserServiceDTO.getHandDataModelList().get(i - 1);
				if (!FileParsrServiceUtils.checkFieldValues(line, handDataModel)) {
					return false;
				}
			}

		}
		return true;
	}

}