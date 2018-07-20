package com.passwordsaver.controllers;

public enum ParameterNames {
	ID_KEY("idKey"),
	INDEX_OF_CURRENT_PAGE("currentPage"),
	VISIBLE_ITEMS_COUNT("visibleItemsCount"),
	USER_LOGIN("userLogin"),
	USER_PASSWORD("userPassword"),
	PASSWORD_CONFIRMATION("passwordConfirmation"),
	SUBSTRING("substring"),
	NEW_LOGIN("newLogin"),
	NEW_PASSWORD("newPassword"),
	NEW_ROLE("newRole"),
	NEW_KEY_SERVICE("newKeyService"),
	NEW_KEY_PASSWORD("newKeyPassword"),
	OLD_LOGIN("oldLogin");
	
	
	private String name;
	ParameterNames(String name){
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
}
