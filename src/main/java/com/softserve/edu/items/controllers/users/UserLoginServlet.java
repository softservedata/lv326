package com.softserve.edu.items.controllers.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.services.UserService;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet({"","/login" })
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        userService=new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDto loginDto = new LoginDto(
				request.getParameter("login"),
				request.getParameter("password"));
		
		if (userService.isValid(loginDto)) {
			// Create session
			HttpSession session = request.getSession(true);
			session.setAttribute("loginDto", loginDto);
			session.setMaxInactiveInterval(300000);
			// Add Cookie
			Cookie cookie = new Cookie("id_session", session.getId());
			response.addCookie(cookie);
			
			if(userService.isAdmin(loginDto)) {//if is admin->OrderAdmin jsp	//if is user->Order jsp	
				session.setAttribute("idRoleSession", 1);
			}
			else {
				session.setAttribute("idRoleSession", 2);				
			}				
			response.sendRedirect(request.getContextPath()
					+ ControllerUrls.ORDERS_SERVLET.toString());
			/*getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ControllerUrls.ORDERS_SERVLET.toString())
			.forward(request, response);  */
		    } 
		    else {
			// Show Error Validator
			request.setAttribute("error", "Bad Login or Password");
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
				.forward(request, response);
		     }

	}

}
