package com.maduro.poker.unit.base;

import java.io.File;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

public abstract class BaseTempDirServiceTest {
	
	@TempDir
	protected File tempDir;

	@BeforeEach
	void setUp() throws Exception {
		Arrays.asList(tempDir.listFiles()).stream().forEach(file -> file.delete());
	}

}
