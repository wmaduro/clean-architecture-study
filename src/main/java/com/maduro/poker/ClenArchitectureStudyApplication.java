package com.maduro.poker;

import java.time.Duration;
import java.time.Instant;

import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.MainSyncService;
import com.maduro.poker.unit.evaluator.HandEvaluatorService;
import com.maduro.poker.unit.file.FileParserService;
import com.maduro.poker.unit.mapper.HandMapperService;
import com.maduro.poker.unit.statistic.StatisticHandTypeService;
import com.maduro.poker.unit.statistic.view.StatisticHandTypeViewerSevice;

public class ClenArchitectureStudyApplication {

	public static void main(String[] args) {

		try {
			System.out.println("running... please, wait...");
			
			Instant start = Instant.now();
			
			String mainPlayer = "wmaduro";
			AggressivityBehaviorEnum aggressivityBehaviorEnum = AggressivityBehaviorEnum.RAISER;
			String filePath = "./sample-files/all-in.csv";
			
			new MainSyncService().process(
					new FileParserService(filePath), 
					new HandMapperService(),
					new HandEvaluatorService(mainPlayer, aggressivityBehaviorEnum), 
					new StatisticHandTypeService(), 
					new StatisticHandTypeViewerSevice()
			);

			System.out.println("done: " + Duration.between(start, Instant.now()).getSeconds() + " sec");

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
