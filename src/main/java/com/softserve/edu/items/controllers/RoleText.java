package com.softserve.edu.items.controllers;

public enum RoleText {
	NAME_ROLE_ADMINISTRATOR("Administrator"),
	NAME_ROLE_USER("User"),
	ID_ROLE_ADMINISTRATOR("1"),
	ID_ROLE_USER("2"),;
	//
	private String url;

	private RoleText(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}