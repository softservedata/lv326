package com.softserve.edu.dto;

import java.util.HashSet;
import java.util.Set;

import com.softserve.edu.entity.Using;

public class BookDTO {
	private String title;
	private Set<Using> usings;

	public BookDTO(String title) {
		this.title = title;
		this.usings = new HashSet<>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Using> getUsings() {
		return usings;
	}

	public void setUsing(Set<Using> usings) {
		this.usings = usings;
	}

	public void addUsing(Using using) {
		usings.add(using);
	}

}
