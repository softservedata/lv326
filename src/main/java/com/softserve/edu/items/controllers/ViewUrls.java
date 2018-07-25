package com.softserve.edu.items.controllers;

public enum ViewUrls {
	LOGIN_JSP("/WEB-INF/views/users/Login.jsp"),
	REGISTER_JSP("/WEB-INF/views/users/Register.jsp"),
	RECOVER_PASSWORD_JSP("/WEB-INF/views/users/RecoverPassword.jsp"),
		
	ORDERS_JSP("/WEB-INF/views/orders/Orders.jsp"),
	ORDERS_USERS_ADMIN_JSP("/WEB-INF/views/orders/OrdersUsersAdmin.jsp"),
	ADD_ORDER_JSP("/WEB-INF/views/orders/AddOrder.jsp"),
	
	ORDER_PROFILE_JSP("/WEB-INF/views/orders/OrderProfile.jsp"),
	
	EDIT_PROFILE_JSP("/WEB-INF/views/users/EditProfile.jsp"),
	EDIT_PROFILES_USERS_JSP("/WEB-INF/views/users/EditProfilesUsers.jsp"),
	EDIT_PROFILES_USERS_COMMIT_JSP("/WEB-INF/views/users/EditProfilesUsersCommit.jsp"),
		
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
