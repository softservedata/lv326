package com.passwordsaver.controllers;

public enum ControllerUrls {
	LOGIN_SERVLET("/LoginServlet"),
	PERSONAL_CABINET_SERVLET("/PersonalCabinetServlet"),
	REGISTRATION_SERVLET("/RegistrationServlet"),
	ADD_KEY_SERVLET("/AddKeyServlet"),
	EDIT_KEY_SERVLET("/EditKeyServlet"),
	DELETE_KEY_SERVLET("/DeleteKeyServlet"),
	UPDATE_PROFILE_SERVLET("/UpdateProfileServlet"),
	UPDATE_USER_BY_ADMIN_SERVLET("/UserUpdateByAdminServlet"),
	DELETE_USER_SERVLET("/DeleteUserServlet"),
	LOGOUT_SERVLET("/LogOutServlet");
	
	private String url;
	
	ControllerUrls(String url){
		this.url = url;
	}
	
	public String toString() {
		return url;
	}
}
