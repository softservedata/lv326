package com.softserve.edu.project.controller;

public enum ViewUrls {
	LOGIN_JSP("/WEB-INF/views/users/Login.jsp"),
	USER_CREATE_JSP("/WEB-INF/views/users/registration.jsp"),
	USER_PROFILE_JSP("/WEB-INF/views/users/UserProfile.jsp"),
	USER_LIST_JSP("/WEB-INF/views/users/usersList.jsp"),
	USER_EDIT_JSP("/WEB-INF/views/users/UserEdit.jsp"),
	USER_HAS_TEAMS_JSP("/WEB-INF/views/teams/MyTeams.jsp"),
	USER_HAS_PLAYERS_JSP("/WEB-INF/views/players/MyPlayers.jsp"),
	ERROR_JSP("/WEB-INF/views/commons/Error.jsp"),
	//////////////////
	TEAM_CREATE_JSP("/WEB-INF/views/teams/TeamCreate.jsp"),
	TEAM_LIST_JSP("/WEB-INF/views/teams/TeamList.jsp"),
	TEAM_EDIT_JSP("/WEB-INF/views/teams/TeamEdit.jsp"),
	TEAM_HAS_PLAYERS_JSP("/WEB-INF/views/teams/TeamHasPlayers.jsp"),
	TEST_JSP("/WEB-INF/views/test.jsp"),
	///////
	PLAYER_CREATE_JSP("/WEB-INF/views/players/PlayerCreate.jsp"),
	PLAYER_LIST_JSP("/WEB-INF/views/players/PlayerList.jsp"),
	PLAYER_EDIT_JSP("/WEB-INF/views/players/PlayerEdit.jsp"),
	;
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
