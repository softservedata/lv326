package com.mazurak.cinema.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mazurak.cinema.constant.AttributeName;
import com.mazurak.cinema.constant.Message;
import com.mazurak.cinema.constant.ParameterName;
import com.mazurak.cinema.controllers.enums.ControllerUrls;
import com.mazurak.cinema.controllers.enums.ViewUrls;
import com.mazurak.cinema.dto.UserDto;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.UserService;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	public RegistrationServlet() {
		this.userService = IoCContainer.getInit().getUserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(ViewUrls.REGISTER_PAGE_JSP.toString()).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		UserDto userDto = new UserDto();
		String name = req.getParameter(ParameterName.NAME);
		String login = req.getParameter(ParameterName.LOGIN);
		String password = req.getParameter(ParameterName.PASSWORD);
		userDto.setName(name);
		userDto.setLogin(login);
		userDto.setPassword(password);
		
		if(!userService.isAlreadyExists(userDto)) {
			req.setAttribute(AttributeName.ERROR, Message.ALREDY_EXIST);
			req.getRequestDispatcher(ViewUrls.REGISTER_PAGE_JSP.toString()).forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + ControllerUrls.LOGIN_SERVLET.toString());
		}
	}
}
