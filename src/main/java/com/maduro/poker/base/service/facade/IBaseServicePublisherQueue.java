package com.maduro.poker.base.service.facade;

import com.maduro.poker.base.queue.facade.IQueue;

public interface IBaseServicePublisherQueue<P> {

	IQueue<P> getQueuePublisher();

}