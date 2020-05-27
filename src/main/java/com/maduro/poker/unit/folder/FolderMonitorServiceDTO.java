package com.maduro.poker.unit.folder;

import java.io.File;

import lombok.Getter;

public class FolderMonitorServiceDTO {

	@Getter
	private File file = null;

	public FolderMonitorServiceDTO addFile(File file) {
		this.file = file;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(file);
		return sb.toString();
	}

}
