package com.mazurak.cinema.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntityCommonFieldsAbstract implements BaseEntity {

	private Long id;
	public BaseEntityCommonFieldsAbstract(Long id) {
		this.id = id;
	}
}
