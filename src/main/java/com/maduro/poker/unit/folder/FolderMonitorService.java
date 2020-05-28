package com.maduro.poker.unit.folder;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.base.BaseService;
import com.maduro.poker.util.Utils;

public class FolderMonitorService extends BaseService {

	public static String IMPORTING_EXTENSION = ".importing";
	private Path monitoredFolder;

	public FolderMonitorService(EventBus eventBus, Path monitoredFolder) {
		super(eventBus);
		this.monitoredFolder = monitoredFolder;
	}

	@Override
	public void run() {
		startMonitoringFolder();
		super.run();
	}

	private void startMonitoringFolder() {
		try {

			WatchService watchService = FileSystems.getDefault().newWatchService();
			monitoredFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

			while (true) {

				processFolder(monitoredFolder);

				WatchKey watchKey = watchService.take();
				watchKey.pollEvents();

				if (!watchKey.reset()) {
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processFolder(Path monitoredFolder) throws Exception {

		String MONITORED_EXTENSTIONS = "*.{csv}";

		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(monitoredFolder, MONITORED_EXTENSTIONS);
		
		try {

			for (Path path : directoryStream) {
				File renamedFile = renameFile(path);

				publish(new FolderMonitorServiceDTO().addFile(renamedFile));
			}

		} finally {
			directoryStream.close();
		}
	}

	File renameFile(Path path) throws Exception {
		
		String newFileName = Utils.removeFileExtension(path.toFile().getAbsolutePath()) + IMPORTING_EXTENSION;
		File newImportFile = new File(newFileName);
		if (!path.toFile().renameTo(newImportFile)) {
			throw new Exception("Error while renaming file " + path.getFileName());
		}
		return newImportFile;

	}

}
