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

@WebServlet("/listusersmovies")
public class UserMoviesServlet extends HttpServlet {
	public static final int CURRENT_PAGE = 0;
	public static final int RECORDS_PER_PAGE = 10;
	public static final int USER_ID = 1;

	private static final long serialVersionUID = 1L;

	private UserMovieService userMovieService;

	public UserMoviesServlet() {
		this.userMovieService = IoCContainer.getInit().getUserMovieService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// check active session
		
		Security.isActiveSession(req, resp);

		resp.setContentType("text/html;charset=UTF-8");
		Long idUser =
				Long.parseLong(req.getSession().getAttribute(AttributeName.USER_ID_ATTRIBUTE).toString());
		Long roleId =
				Long.parseLong(req.getSession().getAttribute(AttributeName.USER_ROLE_ATTRIBUTE).toString());

		try {
			int currentPage = CURRENT_PAGE;
			int recordsPerPage = RECORDS_PER_PAGE;
			currentPage = Integer.parseInt(req.getParameter(ParameterName.PAGE));
			int numOfRows = 0;
			if (roleId == USER_ID) {
				numOfRows = userMovieService.getCountMovieInUser(idUser);
			} else {
				numOfRows = userMovieService.getAllMovie().size();
			}

			int numOfPage = (numOfRows / recordsPerPage);
			if (numOfPage % recordsPerPage >= 0) {
				++numOfPage;
			}
			req.getSession().setAttribute(AttributeName.CURRENT_PAGE, currentPage);
			int begin = Math.max(1, currentPage - 4);
			int end = Math.min(begin + 4, currentPage + 2);

			req.setAttribute(AttributeName.BEGIN_INDEX, begin);
			req.setAttribute(AttributeName.END_INDEX, end);
			req.setAttribute(AttributeName.CURRENT_INDEX, currentPage);
			req.setAttribute(AttributeName.NUMBER_OF_ALL_PAGES, numOfPage);

			if (roleId == USER_ID) {
				req.setAttribute(AttributeName.MOVIE_ATTR, userMovieService.getMoviePagination(
						userMovieService.getUserMoviesById(idUser), currentPage + 1, recordsPerPage));
				req.getRequestDispatcher(ViewUrls.USER_LIST_MOVIES_JSP.toString()).forward(req, resp);
			} else {
				req.setAttribute(AttributeName.MOVIE_ATTR, userMovieService
						.getMoviePagination(userMovieService.getAllMovie(), currentPage + 1, recordsPerPage));
				req.getRequestDispatcher(ViewUrls.USER_LIST_MOVIES_JSP.toString()).forward(req, resp);
			}
		} catch (RuntimeException e) {
			req.getRequestDispatcher(ViewUrls.USER_LIST_MOVIES_JSP.toString()).forward(req, resp);
		}
	}
}

