package com.maduro.poker.base.service;

import com.maduro.poker.base.queue.facade.IQueue;
import com.maduro.poker.base.queue.facade.IQueuePublisher;
import com.maduro.poker.base.service.facade.IBaseServicePublisherQueue;

public abstract class BaseServicePublisher<P> implements Runnable, IQueuePublisher<P>, IBaseServicePublisherQueue<P> {

	protected IQueue<P> queuePubliser;

	@Override
	public void publish(P publishElement) {
		queuePubliser.publish(publishElement);
	}

	@Override
	public void run() {
	}

	@Override
	public IQueue<P> getQueuePublisher() {
		return queuePubliser;
	}

}