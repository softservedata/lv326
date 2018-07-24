package com.mazurak.cinema.controller.movie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mazurak.cinema.constant.AttributeName;
import com.mazurak.cinema.constant.ParameterName;
import com.mazurak.cinema.controller.common.Security;
import com.mazurak.cinema.controllers.enums.ControllerUrls;
import com.mazurak.cinema.controllers.enums.ViewUrls;
import com.mazurak.cinema.dto.MovieDto;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.MovieService;

@WebServlet("/updatemovie/*")
public class UpdateMovieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private MovieService movieService;

	public UpdateMovieServlet() {
		this.movieService = IoCContainer.getInit().getMovieService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// check active session 
		Security.isActiveSession(req, resp);
		
		String uri = req.getRequestURI().toString();
		String[] split = uri.split("/");
		Long idMovie = Long.parseLong(split[split.length - 1].toString());
		
		req.setAttribute("id", idMovie);
		req.setAttribute(AttributeName.MOVIE_ATTR, movieService.getMovieDto(idMovie));
		req.getRequestDispatcher(ViewUrls.UPDATE_MOVIES_PAGE_JSP.toString()).forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Long idUser = Long.parseLong(req.getSession()
				.getAttribute(AttributeName.USER_ID_ATTRIBUTE).toString());
	
		Long idMovie = Long.parseLong(req.getParameter("id"));
		String newName = req.getParameter(ParameterName.NEW_NAME_PARAM);
		String newDesc = req.getParameter(ParameterName.NEW_DESCRIPTION_PARAM);
		String newAgeLimit = req.getParameter(ParameterName.NEW_AGELIMIT_PARAM);
		Integer newYear = Integer.parseInt(req.getParameter(ParameterName.NEW_YEAR_PARAM));

		MovieDto movieDto = new MovieDto(idMovie, newName, newDesc, newAgeLimit, newYear, idUser);
		movieService.setMovieDto(movieDto, idUser);
		String pageNum = req.getSession().getAttribute(AttributeName.CURRENT_PAGE).toString();
		resp.sendRedirect(req.getContextPath() + ControllerUrls.LIST_MOVIES_PAGE + pageNum);
	}
}
