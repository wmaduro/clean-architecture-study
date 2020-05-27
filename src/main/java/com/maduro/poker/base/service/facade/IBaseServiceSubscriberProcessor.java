package com.maduro.poker.base.service.facade;

public interface IBaseServiceSubscriberProcessor<S, P> {

	P processSubscriber(S subscriber);

}