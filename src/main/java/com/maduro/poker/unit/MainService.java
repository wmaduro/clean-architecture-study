package com.maduro.poker.unit;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.evaluator.HandEvaluatorService;
import com.maduro.poker.unit.file.FileParserService;
import com.maduro.poker.unit.folder.FolderMonitorService;
import com.maduro.poker.unit.mapper.HandMapperService;
import com.maduro.poker.unit.statistic.StatisticHandTypeService;
import com.maduro.poker.unit.view.StatisticHandTypeViewerSevice;

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
				new StatisticHandTypeService(eventBus), 
				new StatisticHandTypeViewerSevice(eventBus),

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
