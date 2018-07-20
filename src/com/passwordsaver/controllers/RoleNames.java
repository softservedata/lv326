package com.passwordsaver.controllers;

public enum RoleNames {
	ADMINISTRATOR("Administrator"),
	USER("User");
	private String title;
	
	RoleNames(String title){
		this.title = title;
	}
	
	public String toString() {
		return title;
	}
}
