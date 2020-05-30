package com.maduro.poker.unit.base;

import java.time.Instant;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.Getter;

public abstract class BaseRunnableEventBusService implements Runnable {

	@Getter
	private Instant instantEventProcessed= null;
	@Getter
	private Instant instantEventPosted= null;
	
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

	public void publish(Object object) {
		this.instantEventProcessed = Instant.now();
		if (eventBus != null && object != null) {
			this.instantEventPosted= Instant.now();
			this.eventBus.post(object);
		}
	}

}