package com.maduro.poker.unit.base;

public interface IBaseProcessEventService<T> {
	void processEvent(T event);
}
