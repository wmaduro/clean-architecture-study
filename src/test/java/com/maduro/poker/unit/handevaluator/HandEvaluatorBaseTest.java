package com.maduro.poker.unit.handevaluator;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.maduro.poker.unit.handmapper.HandMapperServiceDTO;

public abstract class HandEvaluatorBaseTest {

	@Mock
	protected HandMapperServiceDTO handMapperServiceDTO;

	protected final String mainPlayerNameFilter = "wmaduro";

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

}
