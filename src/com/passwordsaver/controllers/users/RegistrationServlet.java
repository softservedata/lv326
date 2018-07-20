package com.passwordsaver.controllers.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passwordsaver.controllers.AttributeNames;
import com.passwordsaver.controllers.ControllerUrls;
import com.passwordsaver.controllers.ParameterNames;
import com.passwordsaver.controllers.RoleNames;
import com.passwordsaver.controllers.ServicesContainer;
import com.passwordsaver.controllers.ViewUrls;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.services.UserService;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService;
    
    public RegistrationServlet() {
        super();
        userService = ServicesContainer.getInstance().getUserService();
    }
    
	private boolean isCorrectPassword(String password){
		boolean result = false;
		if(password.length()>4 
			&& password.length()<31 
			&& password.indexOf(" ")==-1) 
		{	
			result=true;
		}
		return  result;
	}
	private boolean isCorrectLogin(String login){
		boolean result = false;
		if(login.length()>4 
			&& login.length()<31 
			&& login.indexOf(" ")==-1) 
		{	
			result=true;
		}
		return  result;
	}
	private boolean isMatch(String password, String confirmation) {
		return password.equals(confirmation);
	}
	private boolean isUniqueLogin(String login) {
		boolean result = false;
		if(!ServicesContainer.getInstance().getUserService().isExistUser(login)) {
			result = true;
		}
		return result;
	}
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletConfig()
		.getServletContext()
		.getRequestDispatcher(ViewUrls.REGISTRATION_JSP.toString())
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(ParameterNames.NEW_LOGIN.toString());
		String password = request.getParameter(ParameterNames.NEW_PASSWORD.toString());
		String confirmation = request.getParameter(ParameterNames.PASSWORD_CONFIRMATION.toString());
		
		if(login==null || !this.isCorrectLogin(login)) {
			request.setAttribute(AttributeNames.ERROR.toString(), "wrongLoginFormat");
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.REGISTRATION_JSP.toString())
			.forward(request, response);
		}else if(password==null || !this.isCorrectPassword(password)) {
			request.setAttribute(AttributeNames.ERROR.toString(), "wrongPasswordFormat");
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.REGISTRATION_JSP.toString())
			.forward(request, response);
		}else if(confirmation==null || !this.isMatch(password, confirmation))
		{
			request.setAttribute(AttributeNames.ERROR.toString(), "wrongConfirmation");
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.REGISTRATION_JSP.toString())
			.forward(request, response);
		}else if(!this.isUniqueLogin(login)){
			request.setAttribute(AttributeNames.ERROR.toString(), "notAvailableLogin");
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.REGISTRATION_JSP.toString())
			.forward(request, response);	
		}else {
			userService.setUserDto(new UserDto(login,password,RoleNames.USER.toString()),0l);
			response.sendRedirect(request.getContextPath()+ControllerUrls.LOGIN_SERVLET);
		}
	}

}
