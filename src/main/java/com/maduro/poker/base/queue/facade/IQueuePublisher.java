package com.maduro.poker.base.queue.facade;

public interface IQueuePublisher<T> {

	void publish(T object);

}