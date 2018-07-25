package com.softserve.edu.items.controllers.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.controllers.commons.Security;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.dto.UserDto;
import com.softserve.edu.items.services.IocContainer;
import com.softserve.edu.items.services.UserService;

/**
 * Servlet implementation class UserProfileEditServlet
 */
@WebServlet("/profileedit")
public class ProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileEditServlet() {
        super();
    	userService = IocContainer.get().getUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!Security.isActiveSession(request, response)) {
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
				.forward(request, response);
		} 
		else {
		LoginDto loginDto= (LoginDto)request.getSession(false).getAttribute("loginDto");
		request.setAttribute("login", loginDto.getLogin());		
		
		getServletConfig()
		.getServletContext()
		.getRequestDispatcher(ViewUrls.EDIT_PROFILE_JSP.toString())
		.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDto loginDto= (LoginDto)request.getSession(true).getAttribute("loginDto");
		
		
		if(request.getParameter("login") != null && request.getParameter("new_login") != null){
			if(!request.getParameter("login").equals(request.getParameter("new_login"))&&
					userService.isUserExist(request.getParameter("new_login"))==false &&
					request.getParameter("login").equals(loginDto.getLogin() )) { //&&request.getParameter("login").equals(loginDto.getLogin() yskcho minyaempsw to maem minyaty sesiy
				UserDto userDto = userService.getUserDto(loginDto);
	
				userService.setUserDtoUpdateLogin(userDto, request.getParameter("new_login"));
				loginDto.setLogin(request.getParameter("new_login"));
				HttpSession session = request.getSession(false);
				session.setAttribute("loginDto", loginDto);
								
				//request.setAttribute("changesSavedSuccessfully", "Changes Saved Successfully");
				//request.setAttribute("login", request.getParameter("new_login"));
				response.sendRedirect(request.getContextPath()
						+ ControllerUrls.PROFILE_EDIT_SERVLET.toString());	
				/*getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.EDIT_PROFILE_JSP.toString())
				.forward(request, response);*/
			}
			else if(userService.isUserExist(request.getParameter("new_login"))==true) {
				request.setAttribute("login", request.getParameter("login"));
				request.setAttribute("error", "Such account already exists");
				//response.sendRedirect(ControllerUrls.FULL_URL_PROFILE_EDIT.toString());
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.EDIT_PROFILE_JSP.toString())
				.forward(request, response);
			}	
			
			
			else {
				request.setAttribute("login", request.getParameter("login"));
				request.setAttribute("error", "Invalid data entered");
				//response.sendRedirect(ControllerUrls.FULL_URL_PROFILE_EDIT.toString());
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.EDIT_PROFILE_JSP.toString())
				.forward(request, response);
			}
			
		}
		
		if(request.getParameter("previous_password") != null && 
				request.getParameter("new_password") != null && 
				request.getParameter("repeat_new_password") != null ) {
			if(loginDto.getPassword().equals(request.getParameter("previous_password"))&&
					request.getParameter("new_password").equals(request.getParameter("repeat_new_password"))) {
				UserDto userDto = userService.getUserDto(loginDto);
				userDto.setPassword(request.getParameter("new_password"));
				userService.setUserDto(userDto);
				
				loginDto.setPassword(request.getParameter("new_password"));
				HttpSession session = request.getSession(false);
				session.setAttribute("loginDto", loginDto);
				
				request.setAttribute("login", loginDto.getLogin());
				request.setAttribute("changesSavedSuccessfully", "Changes Saved Successfully");
				//response.sendRedirect(ControllerUrls.FULL_URL_PROFILE_EDIT.toString());
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.EDIT_PROFILE_JSP.toString())
				.forward(request, response);
			}
			else {
				request.setAttribute("login", loginDto.getLogin());
				request.setAttribute("previous_password", request.getParameter("previous_password"));
				request.setAttribute("new_password", request.getParameter("new_password"));
				request.setAttribute("repeat_new_password", request.getParameter("repeat_new_password"));
				request.setAttribute("error", "Invalid data entered");
				//response.sendRedirect(ControllerUrls.FULL_URL_PROFILE_EDIT.toString());
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.EDIT_PROFILE_JSP.toString())
				.forward(request, response);
			}
		}		
		//System.out.println("done= ");
	}

}
