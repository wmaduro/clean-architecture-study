package com.maduro.poker;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.Executors;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.enums.AggressivityBehaviorEnum;
import com.maduro.poker.unit.MainService;

public class ClenArchitectureStudyApplication {

	public static void main(String[] args) {

		
		if (args == null || args.length == 0 || args[0].isBlank()) {
			System.err.println("The application has stopped! Please, inform the folder to be monitored.");
			return;
		}
	
		File file = new File(args[0]);
		
		if (!file.isDirectory()) {
			System.err.println("The application has stopped! the " + args[0] + " is not a valid folder.");
			return;
		}

		Path monitoredFolder = file.toPath();
		String mainPlayer = "wmaduro";
		EventBus eventBus = new EventBus();

		new MainService().run(monitoredFolder, mainPlayer, AggressivityBehaviorEnum.RAISER, eventBus,
				Executors.newCachedThreadPool());

	}

}
