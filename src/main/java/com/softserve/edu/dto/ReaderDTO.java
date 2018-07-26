package com.softserve.edu.dto;

import java.util.HashSet;
import java.util.Set;

import com.softserve.edu.entity.Using;

public class ReaderDTO {
	private String name;
	private Set<Using> usings;

	public ReaderDTO(String name) {
		this.name = name;
		this.usings = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
