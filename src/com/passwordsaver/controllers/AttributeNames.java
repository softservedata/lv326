package com.passwordsaver.controllers;

public enum AttributeNames {
	INDEX_OF_CURRENT_PAGE("currentPage"),
	ERROR("error"),
	INDEX_OF_FIRST_ITEM_ON_PAGE("from"),
	RIGHT_BOUNDARY_PAGE("rightBoundaryPage"),
	LEFT_BOUNDARY_PAGE("leftBoundaryPage"),
	ADMIN_USERS_DTO("adminUsersDto"),
	USER_DTO("userDto"),
	UPDATED_USER_DTO("updatedUser"),
	USER_KEYS_DTO("userKeysDto");
	
	private String name;
	private AttributeNames(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
}
