package com.maduro.poker.unit.base;

import com.google.common.eventbus.Subscribe;

public interface IBaseProcessEventService {
	@Subscribe 
	void ProcessEvent(IBaseEventBusDTO event);
}
