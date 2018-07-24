package com.mazurak.cinema.controllers.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ViewUrls {

	LOGIN_PAGE_JSP("/WEB-INF/views/login-page.jsp"),
	REGISTER_PAGE_JSP("/WEB-INF/views/registration-page.jsp"),
	USER_PAGE_JSP("/WEB-INF/views/user-page.jsp"),
	USER_LIST_MOVIES_JSP("/WEB-INF/views/list-movies.jsp"),
	CREATE_MOVIES_PAGE_JSP("/WEB-INF/views/create-movie-page.jsp"),
	UPDATE_MOVIES_PAGE_JSP("/WEB-INF/views/update-page.jsp"),
	USER_LIST_PAGE_JSP("/WEB-INF/views/user-list-page.jsp"),
	USER_SEARCH_PAGE_JSP("/WEB-INF/views/search-page.jsp"),
	ADMIN_UPDATE_USER_JSP("/WEB-INF/views/admin-update-user-page.jsp");
	
	
	
	
	private String url;

	@Override
	public String toString() {
		return url;
	}
}
