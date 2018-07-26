package com.softserve.edu.entity;

import java.util.Set;

public class Book implements IEntity {
	private Long id;
	private String title;
	private Set<Using> using;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Using> getUsing() {
		return using;
	}

	public void setUsing(Set<Using> using) {
		this.using = using;
	}

}
