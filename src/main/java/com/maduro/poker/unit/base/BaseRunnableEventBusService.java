package com.maduro.poker.unit.base;

import java.time.Instant;

import com.google.common.eventbus.EventBus;

import lombok.Getter;

public abstract class BaseRunnableEventBusService implements Runnable {

	@Getter
	private Instant instantPublishCalled= null;
	
	
	private EventBus eventBus;

	public BaseRunnableEventBusService(EventBus eventBus) {
		if (eventBus != null) {
			this.eventBus = eventBus;
			this.eventBus.register(this);
		}
	}
	
	@Override
	public void run() {
	}

	public void publish(IBaseEventBusDTO object) {
		this.instantPublishCalled = Instant.now();
		if (eventBus != null && object != null) {
			this.eventBus.post(object);
		}
	}

}
