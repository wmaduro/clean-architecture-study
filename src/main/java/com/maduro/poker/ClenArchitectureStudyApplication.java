package com.maduro.poker;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.evaluator.HandEvaluatorService;
import com.maduro.poker.unit.file.FileParserService;
import com.maduro.poker.unit.folder.FolderMonitorService;
import com.maduro.poker.unit.mapper.HandMapperService;
import com.maduro.poker.unit.statistic.StatisticHandTypeService;
import com.maduro.poker.unit.statistic.view.StatisticHandTypeViewerSevice;

public class ClenArchitectureStudyApplication {

	public static void main(String[] args) {
		new ClenArchitectureStudyApplication();
	}

	public ClenArchitectureStudyApplication() {

		Path monitoredFolder = Paths.get("/home/maduro/pokerstas-files/all-in-statistic/");

		FolderMonitorService folderMonitorService = new FolderMonitorService(monitoredFolder);
		FileParserService fileParserService = new FileParserService(folderMonitorService.getQueuePublisher());
		HandMapperService handMapperService = new HandMapperService(fileParserService.getQueuePublisher());

		HandEvaluatorService handEvaluatorService = new HandEvaluatorService(handMapperService.getQueuePublisher(),
				"wmaduro", AggressivityBehaviorEnum.RAISER);

		StatisticHandTypeService statisticHandTypeService = new StatisticHandTypeService(
				handEvaluatorService.getQueuePublisher());
		StatisticHandTypeViewerSevice statisticHandTypeViewerSevice = new StatisticHandTypeViewerSevice(
				statisticHandTypeService.getQueuePublisher());

		final Runnable[] services = { folderMonitorService, fileParserService, handMapperService, handEvaluatorService,
				statisticHandTypeViewerSevice, };

		final ExecutorService executorService = Executors.newCachedThreadPool();
		try {
			Arrays.asList(services).stream().forEach(executorService::submit);
		} finally {
			System.out.println("processing... ");
			executorService.shutdown();
		}

	}

}
