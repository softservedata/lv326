package com.passwordsaver.controllers;

public enum ViewUrls {
	LOGIN_JSP("/WEB-INF/views/users/Login.jsp"),
	ADD_KEY_JSP("/WEB-INF/views/users/clients/keys/addKey.jsp"),
	EDIT_KEY_JSP("/WEB-INF/views/users/clients/keys/editKey.jsp"),
	UPDATE_PROFILE_JSP("/WEB-INF/views/users/updateProfile.jsp"),
	REGISTRATION_JSP("/WEB-INF/views/users/clients/registration.jsp"),
	ADMIN_PROFILE_JSP("/WEB-INF/views/users/admins/adminProfile.jsp"),
	USER_UPDATE_BY_ADMIN("/WEB-INF/views/users/admins/userUpdateByAdmin.jsp"),
	USER_KEYS_JSP("/WEB-INF/views/users/clients/userProfile.jsp");
	
	private String url;
	
	private ViewUrls(String url) {
		this.url = url;
	}
	public String toString() {
		return url;
	}
}
