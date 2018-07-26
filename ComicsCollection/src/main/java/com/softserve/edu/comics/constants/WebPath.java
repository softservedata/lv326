package com.softserve.edu.comics.constants;

public class   WebPath {

	// JSP FILES
	public static final String REGISTER_JSP = "/WEB-INF/views/users/register.jsp";
	public static final String LOGIN_JSP = "/WEB-INF/views/users/login.jsp";
	public static final String USER_PROFILE_JSP = "/WEB-INF/views/users/userprofile.jsp";
	public static final String USERS_LIST_JSP = "/WEB-INF/views/users/userslist.jsp";
	public static final String USER_COMICS_JSP = "/WEB-INF/views/users/usercomics.jsp";
	public static final String ADD_COMICS_JSP = "/WEB-INF/views/comics/addcomics.jsp";
	public static final String EDIT_COMICS_JSP = "/WEB-INF/views/comics/editcomics.jsp";
	public static final String CHANGE_PASSW_JSP = "/WEB-INF/views/users/changepassw.jsp";
	public static final String ERROR_JSP = "/WEB-INF/views/commons/error.jsp";

	// USER SERVLETS
	public static final String HOME_SERVLET = "/";
	public static final String LOGIN_SERVLET = "/login";
	public static final String LOGOUT_SERVLET = "/logout";
	public static final String REGISTER_SERVLET = "/register";
	public static final String USER_CANCEL_SERVLET = "/usercancel";
	public static final String USER_PROFILE_SERVLET = "/userprofile";
	public static final String CHANGE_PASSW_SERVLET = "/changepassw";
	public static final String USERS_LIST_SERVLET = "/userslist";
	public static final String USER_COMICS_SERVLET = "/usercomics";
	public static final String ERROR_PAGE_SERVLET = "/errorpage";

	//COMICS SERVLETS
	public static final String ADD_COMICS_SERVLET = "/addcomics";
	public static final String EDIT_COMICS_SERVLET = "/editcomics/*";
	public static final String COMICS_CENCEL_SERVLET = "/comicscancel";
	public static final String DELETE_COMICS_SERVLET = "/deletecomics/*";

}
