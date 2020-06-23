package com.maduro.poker.unit;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.fileparser.FileParserService;
import com.maduro.poker.unit.foldermonitor.FolderMonitorService;
import com.maduro.poker.unit.handevaluator.HandEvaluatorService;
import com.maduro.poker.unit.handmapper.HandMapperService;
import com.maduro.poker.unit.handtype.HandTypeService;
import com.maduro.poker.unit.handtypeviewer.HandTypeViewerSevice;

public class MainService {

	public void run(Path monitoredFolder, String mainPlayer, AggressivityBehaviorEnum aggressivityBehaviorEnum,
			EventBus eventBus, ExecutorService executorService) {

		Runnable[] runnables = getRunnables(monitoredFolder, mainPlayer, aggressivityBehaviorEnum, eventBus);
		executeRunnables(runnables, executorService );
	}

	Runnable[] getRunnables(Path monitoredFolder, String mainPlayer,
			AggressivityBehaviorEnum aggressivityBehaviorEnum, EventBus eventBus) {
		if (monitoredFolder == null || eventBus == null) {
			return null;
		}

		final Runnable[] runnables = {

				new FolderMonitorService(eventBus, monitoredFolder), 
				new FileParserService(eventBus),
				new HandMapperService(eventBus),
				new HandEvaluatorService(eventBus, mainPlayer, aggressivityBehaviorEnum),
				new HandTypeService(eventBus), 
				new HandTypeViewerSevice(eventBus),

		};

		return runnables;
	}

	void executeRunnables(Runnable[] runnables, ExecutorService executorService ) {

		if (runnables == null) {
			return;
		}

		try {

			Arrays.asList(runnables).stream().forEach(runnable -> {
				executorService.submit(runnable);
			});

		} finally {
			System.out.println("processing... ");
			executorService.shutdown();
		}
	}

}
