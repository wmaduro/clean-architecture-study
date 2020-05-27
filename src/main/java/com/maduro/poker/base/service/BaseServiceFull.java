package com.maduro.poker.base.service;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.maduro.poker.base.queue.facade.IQueue;
import com.maduro.poker.base.service.facade.IBaseServicePublisherQueue;
import com.maduro.poker.base.service.facade.IBaseServiceSubscriberProcessor;

public abstract class BaseServiceFull<S, P>
		implements Runnable, IBaseServiceSubscriberProcessor<S, P>,  IBaseServicePublisherQueue<P>, PropertyChangeListener {

	private IQueue<S> queueSubscriber;
	protected IQueue<P> queuePublisher;

	public BaseServiceFull(IQueue<S> subscriber) {
		this.queueSubscriber = subscriber;
		subscriber.addPropertyChangeListener(this);
	}

	@Override
	public void run() {

	}
	
	@Override
	public IQueue<P> getQueuePublisher() {
		return queuePublisher;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		S subscriberElement = queueSubscriber.consume();
		if (subscriberElement != null) {
			P processedPOJO = processSubscriber(subscriberElement);
			if (processedPOJO != null) {
				new Thread(()->{
					queuePublisher.publish(processedPOJO);	
				}).start();
			}
		}
	}

}