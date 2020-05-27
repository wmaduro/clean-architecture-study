package com.maduro.poker.unit;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.evaluator.HandEvaluatorService;
import com.maduro.poker.unit.file.FileParserService;
import com.maduro.poker.unit.folder.FolderMonitorService;
import com.maduro.poker.unit.mapper.HandMapperService;
import com.maduro.poker.unit.statistic.StatisticHandTypeService;
import com.maduro.poker.unit.statistic.view.StatisticHandTypeViewerSevice;

public class MainService {

	public void run(Path monitoredFolder, String mainPlayer, AggressivityBehaviorEnum aggressivityBehaviorEnum) {

		EventBus eventBus = new EventBus();

		Runnable[] runnables = getRunnables(monitoredFolder, mainPlayer, aggressivityBehaviorEnum, eventBus);
		executeRunnables(runnables);

	}

	private void executeRunnables(Runnable[] runnables) {
		final ExecutorService executorService = Executors.newCachedThreadPool();

		try {

			Arrays.asList(runnables).stream().forEach(runnable -> {
				executorService.submit(runnable);
			});

		} finally {
			System.out.println("processing... ");
			executorService.shutdown();
		}
	}

	private Runnable[] getRunnables(Path monitoredFolder, String mainPlayer,
			AggressivityBehaviorEnum aggressivityBehaviorEnum, EventBus eventBus) {

		FolderMonitorService folderMonitorService = new FolderMonitorService(eventBus, monitoredFolder);
		FileParserService fileParserService = new FileParserService(eventBus);
		HandMapperService handMapperService = new HandMapperService(eventBus);
		HandEvaluatorService handEvaluatorService = new HandEvaluatorService(eventBus, mainPlayer,
				aggressivityBehaviorEnum);
		StatisticHandTypeService statisticHandTypeService = new StatisticHandTypeService(eventBus);
		StatisticHandTypeViewerSevice statisticHandTypeViewerSevice = new StatisticHandTypeViewerSevice(eventBus);

		final Runnable[] runnables = { 
				folderMonitorService, 
				fileParserService,
				handMapperService, 
				handEvaluatorService,
				statisticHandTypeService, 
				statisticHandTypeViewerSevice, 
		};

		return runnables;
	}

}
