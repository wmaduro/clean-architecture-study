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

}
