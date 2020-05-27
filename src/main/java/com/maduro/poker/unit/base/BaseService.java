package com.maduro.poker.unit.base;

import com.google.common.eventbus.EventBus;

import lombok.Getter;

public abstract class BaseService implements Runnable {

	@Getter
	private EventBus eventBus;

	public BaseService(EventBus eventBus) {
		this.eventBus = eventBus;
		this.eventBus.register(this);
	}

	@Override
	public void run() {
	}
	
	public void publish(Object object) {
		this.eventBus.post(object);
	}

}
