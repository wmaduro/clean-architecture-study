package com.maduro.poker.unit.evaluator;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.maduro.poker.unit.mapper.HandMapperServiceDTO;

public abstract class HandEvaluatorBaseTest {

	@Mock
	protected HandMapperServiceDTO handMapperServiceDTO;

	protected final String mainPlayerNameFilter = "wmaduro";

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

}
