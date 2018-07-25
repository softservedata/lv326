package com.softserve.edu.items.controllers;

public enum RedirectURLInOrderCount {

	ORDERS_SERVLET("orders"),
	ORDERS_USERS_ADMIN_SERVLET("usersordersadmin");
	//
	private String url;

	private RedirectURLInOrderCount(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
