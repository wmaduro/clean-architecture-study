package com.maduro.poker.base.queue.facade;

import java.beans.PropertyChangeListener;

public interface IQueueSubscriber<T> {
	public T consume();

	void addPropertyChangeListener(PropertyChangeListener pcl);
}