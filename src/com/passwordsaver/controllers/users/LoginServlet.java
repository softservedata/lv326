package com.passwordsaver.controllers.users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.passwordsaver.controllers.AttributeNames;
import com.passwordsaver.controllers.ControllerUrls;
import com.passwordsaver.controllers.ParameterNames;
import com.passwordsaver.controllers.ServicesContainer;
import com.passwordsaver.controllers.ViewUrls;
import com.passwordsaver.dto.AdminUsersDto;
import com.passwordsaver.dto.LoginDto;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.dto.UserKeysDto;
import com.passwordsaver.services.UserService;


/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
    /**
     * Default constructor. 
     */
    public LoginServlet() {
        userService = ServicesContainer.getInstance().getUserService();
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
				request.getParameter(ParameterNames.USER_LOGIN.toString()),
				request.getParameter(ParameterNames.USER_PASSWORD.toString()));
				
		
		
		if(userService.isValide(loginDto)) {
			HttpSession session = request.getSession();
			UserDto userDto = userService.getUserDto(loginDto);
			session.setAttribute(AttributeNames.USER_DTO.toString(), userDto);
			
			Cookie cookie = new Cookie("id_session", session.getId());
			response.addCookie(cookie);
			
			response.sendRedirect(request.getContextPath()+ControllerUrls.PERSONAL_CABINET_SERVLET);
		}else {
			request.setAttribute(AttributeNames.ERROR.toString(), "not valide data");
			
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
			.forward(request, response);
		}
	}

}
