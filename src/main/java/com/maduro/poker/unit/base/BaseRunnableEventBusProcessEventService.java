package com.maduro.poker.unit.base;

import com.google.common.eventbus.EventBus;

public abstract class BaseRunnableEventBusProcessEventService extends BaseRunnableEventBusService
		implements IBaseProcessEventService {

	public BaseRunnableEventBusProcessEventService(EventBus eventBus) {
		super(eventBus);
	}
}
