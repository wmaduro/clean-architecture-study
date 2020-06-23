package com.maduro.poker.unit;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.eventbus.EventBus;
import com.maduro.poker.unit.fileparser.FileParserService;
import com.maduro.poker.unit.foldermonitor.FolderMonitorService;
import com.maduro.poker.unit.handevaluator.HandEvaluatorService;
import com.maduro.poker.unit.handmapper.HandMapperService;
import com.maduro.poker.unit.handtype.HandTypeService;
import com.maduro.poker.unit.handtypeviewer.HandTypeViewerSevice;

@RunWith(MockitoJUnitRunner.class)
public class MainServiceTest {

	@Spy
	MainService mainService;

	@Test
	public void mustGetRunnablesReturnsRightObjects() throws Exception {

		Runnable[] runnables = mainService.getRunnables(mock(Path.class), null, null, mock(EventBus.class));

		List<?> excpectedServiceList = Arrays.asList(FolderMonitorService.class, FileParserService.class,
				HandMapperService.class, HandEvaluatorService.class, HandTypeService.class,
				HandTypeViewerSevice.class);

		java.util.List<?> runnablesClassList = Arrays.asList(runnables).stream().map(a -> a.getClass())
				.collect(Collectors.toList());

		assertTrue(runnablesClassList.containsAll(excpectedServiceList));

	}

	@Test
	public void mustExecuteRunnables() {

		final Runnable[] runnables = { new Runnable() {
			@Override
			public void run() {
			}
		}};
		
		ExecutorService executorService = mock(ExecutorService.class);

		mainService.executeRunnables(runnables, executorService);
	
		verify(executorService).submit(runnables[0]);

	}

	@Test
	public void mustRunCallAllMethods() throws Exception {

		mainService.run(null, null, null, null, null);

		verify(mainService).getRunnables(null, null, null, null);
		verify(mainService).executeRunnables(null, null);

	}

}