package com.softserve.edu.items.controllers;

public enum ControllerUrls {
	ROOT_SERVLET("/"),
	LOGIN_SERVLET("/login"),
	LOGOUT_SERVLET("/logout"),
	//
	USER_CREATE_SERVLET("/usercreate"),
	USER_EDIT_SERVLET("/useredit"),
	USER_UPDATE_SERVLET("/userupdate"),
	USER_CANCEL_SERVLET("/usercancel"),
	//
	ITEM_CREATE_SERVLET("/itemcreate"),
	ITEM_EDIT_SERVLET("/itemedit"),
	ITEM_UPDATE_SERVLET("/itemupdate"),
	ITEM_CANCEL_SERVLET("/itemcancel"),
	ITEM_DELETE_SERVLET("/itemdelete"),
	//
	ITEM_COUNT_SERVLET("/itemcount"),
	USER_ITEMS_SERVLET("/useritems");
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
