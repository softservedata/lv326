package com.mazurak.cinema.controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mazurak.cinema.constant.AttributeName;
import com.mazurak.cinema.constant.ParameterName;
import com.mazurak.cinema.controllers.enums.ViewUrls;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.UserMovieService;

@WebServlet("/search")
public class UserMoviesSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserMovieService userMovieService;

	public UserMoviesSearchServlet() {
		this.userMovieService = IoCContainer.getInit().getUserMovieService();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// check active session 
		Security.isActiveSession(req, resp);
		try {
			req.setAttribute(AttributeName.MOVIE_ATTR,
					userMovieService.searchMovie(req.getParameter(ParameterName.SEARCH)));
			req.getRequestDispatcher(ViewUrls.USER_SEARCH_PAGE_JSP.toString()).forward(req, resp);
		} catch (RuntimeException e) {
			req.getRequestDispatcher(ViewUrls.USER_SEARCH_PAGE_JSP.toString()).forward(req, resp);
		}
	}
}
