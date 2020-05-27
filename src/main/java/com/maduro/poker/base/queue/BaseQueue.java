package com.maduro.poker.base.queue;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.LinkedTransferQueue;

import com.maduro.poker.base.queue.facade.IQueue;

public abstract class BaseQueue<T> implements IQueue<T> {

	private final LinkedTransferQueue<T> queue = new LinkedTransferQueue<>();
	private boolean onNewElementAdded = false;

	private PropertyChangeSupport propertyChangeSupport;

	public BaseQueue() {
		this.propertyChangeSupport = new PropertyChangeSupport(this);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	}

	@Override
	public void publish(T object) {
		if (queue.contains(object)) {
			return;
		}
//		this.queue.poll();
		this.queue.add(object);
//		show();
		propertyChangeSupport.firePropertyChange("onNewElementAdded", onNewElementAdded, !onNewElementAdded);

	}

	@Override
	public T consume() {

//		show();
		T object = this.queue.poll();
//		show();
		return object;

	}

	private void show() {
		System.out.println("");
		System.out.println("queue elements------- begin");
		this.queue.stream().forEach(object -> {
			System.out.println("queue -> " + object.toString());
		});
		System.out.println("queue elements------- end");
	}
}
