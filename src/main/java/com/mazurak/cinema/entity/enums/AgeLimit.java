package com.mazurak.cinema.entity.enums;

import lombok.Getter;

@Getter
public enum AgeLimit {
	
	LIMIT_1("0+"),
	LIMIT_2("9+"),
	LIMIT_3("12+"),
	LIMIT_4("16+"),
	LIMIT_5("18+");
	
	private String limit;
	
	private AgeLimit(String limit) {
		this.limit = limit;
	}
}
