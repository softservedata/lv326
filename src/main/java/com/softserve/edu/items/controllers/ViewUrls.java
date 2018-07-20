package com.softserve.edu.items.controllers;

public enum ViewUrls {
	LOGIN_JSP("/WEB-INF/views/users/Login.jsp"),
	USER_PROFILE_JSP("/WEB-INF/views/users/UserProfile.jsp"),
	ITEM_PROFILE_JSP("/WEB-INF/views/items/ItemProfile.jsp"),
	USER_ITEMS_JSP("/WEB-INF/views/commons/UserItems.jsp"),
	ERROR_JSP("/WEB-INF/views/commons/Error.jsp");
	//
	private String url;

	private ViewUrls(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
