package com.maduro.poker.unit.base;

import com.google.common.eventbus.EventBus;

public abstract class BaseRunnableEventBusProcessEventService<T> extends BaseRunnableEventBusService
		implements IBaseProcessEventService<T> {

	public BaseRunnableEventBusProcessEventService(EventBus eventBus) {
		super(eventBus);
	}
	
	@Override
	public abstract void processEvent(T event);
}
