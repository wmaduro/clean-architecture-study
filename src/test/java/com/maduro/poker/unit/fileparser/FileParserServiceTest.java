package com.maduro.poker.unit.fileparser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.unit.fileparser.FileParserService;
import com.maduro.poker.unit.fileparser.FileParserServiceDTO;
import com.maduro.poker.unit.fileparser.util.FileParsrServiceUtils;

public class FileParserServiceTest {

	@TempDir
	File tempDir;

	@BeforeEach
	void setUp() throws Exception {
		Arrays.asList(tempDir.listFiles()).stream().forEach(file -> file.delete());
	}

	@Test
	public void mustProcessFileSuccessfully() throws Exception {

		File file = FileParsrServiceUtils.createImportFile(tempDir, this.getClass().getCanonicalName());
		FileParserServiceDTO fileParserServiceDTO = new FileParserService(file.getAbsolutePath()).process();

		assertTrue(validateOutcome(file, fileParserServiceDTO));

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
