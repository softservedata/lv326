package com.softserve.edu.project.controller;

public enum ControllerUrls {
	ROOT_SERVLET("/"),
	LOGIN_SERVLET("/login"),
	LOGOUT_SERVLET("/logout"),
	//
	USER_CREATE_SERVLET("/usercreate"),
	USER_EDIT_SERVLET("/useredit"),
	USER_CANCEL_SERVLET("/usercancel"),
	USER_PROFILE_SERVLET("/userprofile"),
	USER_DELETE_SERVLET("/userdelete"),
	USER_LIST_SERVLET("/usersList"),
	USER_HAS_TEAMS_SERVLET("/myteams"),
	USER_HAS_PLAYERS("/myplayers"),
	//
	TEAM_CREATE_SERVLET("/teamcreate"),
	TEAM_EDIT_SERVLET("/teamEdit"),
	TEAM_LIST_SERVLET("/teamsList"),
	TEAM_DELETE_SERVLET("/teamdelete"),
	TEAM_HAS_PLAYERS_SERVLET("/teamHasPlayers"),
	/////////////////
	PLAYER_LIST_SERVLET("/playersList"),
	PLAYER_DELETE_SERVLET("/playerdelete"),
	PLAYER_EDIT_SERVLET("/playerEdit"),
	PLAYER_CREATE_SERVLET("/playerCreate");
	//

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
