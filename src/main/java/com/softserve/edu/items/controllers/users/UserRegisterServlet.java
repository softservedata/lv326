package com.softserve.edu.items.controllers.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.dto.UserDto;
import com.softserve.edu.items.services.UserService;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();       
        userService=new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		getServletConfig()
		.getServletContext()
		.getRequestDispatcher(ViewUrls.REGISTER_JSP.toString())
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDto userDto = new UserDto(
				request.getParameter("login"),
				request.getParameter("password"),
				request.getParameter("name"),
				"User");
		

		//chek if user with same login is exist.If exist- eror; else- create	
		if(!userService.isUserExist(userDto.getLogin())) {			
			if(request.getParameter("password").equals(request.getParameter("password_repeat"))) {
				//Create user
				
				userService.insertUserDto(userDto);
				request.setAttribute("accountSuccessfullyCreated", "Account Successfully Created");
				getServletConfig()
					.getServletContext()
					.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
					.forward(request, response);
		}
			else {
				// Show Error Validator
							request.setAttribute("error", "Passwords don`t match");
							
							getServletConfig()
								.getServletContext()
								.getRequestDispatcher(ViewUrls.REGISTER_JSP.toString())
								.forward(request, response);
			     }					
		}
		else {			
						request.setAttribute("error", "You don`t may create one more account at this email");
						getServletConfig()
							.getServletContext()
							.getRequestDispatcher(ViewUrls.REGISTER_JSP.toString())
							.forward(request, response);
		     }		
	}

}
