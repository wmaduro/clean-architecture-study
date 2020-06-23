/**
 * 
 */
package com.maduro.poker.unit.fileparser.helper;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.maduro.poker.unit.fileparser.exception.FileCannotBeDirectoryException;
import com.maduro.poker.unit.fileparser.exception.FileNotExistsException;
import com.maduro.poker.unit.fileparser.exception.FileNotProvidedException;
import com.maduro.poker.unit.fileparser.helper.FileParserServiceHelper;

class FileParserServiceHelperTest {

	@TempDir
	File tempDir;

	@BeforeEach
	void setUp() throws Exception {
		Arrays.asList(tempDir.listFiles()).stream().forEach(file -> file.delete());
	}

	@Test
	void fileMustExists() {
		try {
			assertTrue(new File(createFileInTemporaryPath(false)).exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void exceptionMustBeThrownForFileNotProvided() {
		assertThrows(FileNotProvidedException.class, () -> {
			FileParserServiceHelper.getFileFromPath("");
		});
		assertThrows(FileNotProvidedException.class, () -> {
			FileParserServiceHelper.getFileFromPath(null);
		});
		assertThrows(FileNotProvidedException.class, () -> {
			FileParserServiceHelper.getFileFromPath("  ");
		});
	}

	@Test
	void exceptionMustBeThrownForFileNotExists() {
		assertThrows(FileNotExistsException.class, () -> {
			FileParserServiceHelper.getFileFromPath(createFileInTemporaryPath(true));
		});
	}

	@Test
	void exceptionMustBeThrownForFileCannotBeDirectory() {
		assertThrows(FileCannotBeDirectoryException.class, () -> {
			FileParserServiceHelper.getFileFromPath(tempDir.getAbsolutePath());
		});
	}

	private String createFileInTemporaryPath(boolean fileMustBeDeleted) throws IOException {

		String prefix = "test";
		String suffix = "";
		File tempFile = File.createTempFile(prefix, suffix, tempDir);
		if (fileMustBeDeleted) {
			tempFile.delete();
		}

		return tempFile.getAbsoluteFile().toString();

	}
}
