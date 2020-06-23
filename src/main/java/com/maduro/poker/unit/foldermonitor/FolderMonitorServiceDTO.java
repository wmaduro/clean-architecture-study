package com.maduro.poker.unit.foldermonitor;

import java.io.File;

import com.maduro.poker.unit.base.IBaseEventBusDTO;

import lombok.Getter;

public class FolderMonitorServiceDTO implements IBaseEventBusDTO {

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
