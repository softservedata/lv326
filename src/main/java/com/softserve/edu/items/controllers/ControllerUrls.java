package com.softserve.edu.items.controllers;

public enum ControllerUrls {
	ROOT_SERVLET("/"),
	LOGIN_SERVLET("/login"),
	LOGOUT_SERVLET("/logout"),
	REGISTER_SERVLET("/register"),	
	//
	ORDERS_SERVLET("/orders"),
	ORDERS_USERS_ADMIN_SERVLET("/usersordersadmin"),
	PROFILE_EDIT_SERVLET("/profileedit"),
	USERS_PROFILES_EDIT_ADMIN_SERVLET("/usersprofileseditadmin");	
	//
	private String url;

	private ControllerUrls(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
