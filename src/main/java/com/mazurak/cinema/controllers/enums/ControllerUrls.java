package com.mazurak.cinema.controllers.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ControllerUrls {

	LOGIN_SERVLET("/login"),
	LOGOUT_SERVLET("/logout"),
	USER_PAGE_SERVLET("userpage"),
	CREATE_MOVIE_SERVLET("/createmovie"),
	UPDATE_MOVIE_SERVLET("/updatemovie"),
	DELETE_MOVIE_SERVLET("/deletemovie"),
	USER_MOVIE_LIST_SERVLET("/listusersmovies"),
	ADMIN_USER_LIST_SERVLET("/userslist"),
	LIST_USER_MOVIES_PAGINATION("http://localhost:8080/SoftServeProject/listusersmovies?page=0"),
	USER_SEARCH_SERVLET("/search"),
	LIST_MOVIES_PAGE("/listusersmovies?page=");
	
	private String url;

	@Override
	public String toString() {
		return url;
	}
}
