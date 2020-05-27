package com.maduro.poker;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.MainService;

public class ClenArchitectureStudyApplication {

	public static void main(String[] args) {
		
		Path monitoredFolder = Paths.get("/home/maduro/pokerstas-files/all-in-statistic/");
		String mainPlayer = "wmaduro";

		new MainService().run(monitoredFolder, mainPlayer, AggressivityBehaviorEnum.RAISER);

		
	}

}
