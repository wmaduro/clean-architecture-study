package com.maduro.poker.enums;

import lombok.Getter;

public enum AggressivityBehaviorEnum {
	CALLER("CALL"), RAISER("RAISE");

	@Getter
	private String value;

	AggressivityBehaviorEnum(String value) {
		this.value = value;
	}

}
