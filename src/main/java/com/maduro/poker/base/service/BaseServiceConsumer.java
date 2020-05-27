package com.maduro.poker.base.service;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.maduro.poker.base.queue.facade.IQueueSubscriber;

public class BaseServiceConsumer<S> implements Runnable, PropertyChangeListener {

	protected IQueueSubscriber<S> queueSubscriber;
	protected S result;

	@Override
	public void run() {

	}

	public BaseServiceConsumer(IQueueSubscriber<S> subscriber) {
		this.queueSubscriber = subscriber;
		subscriber.addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		result = queueSubscriber.consume();
	}

	

}