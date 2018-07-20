package com.passwordsaver.controllers.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.passwordsaver.controllers.AttributeNames;
import com.passwordsaver.controllers.ControllerUrls;
import com.passwordsaver.controllers.ParameterNames;
import com.passwordsaver.controllers.ServicesContainer;
import com.passwordsaver.controllers.ViewUrls;
import com.passwordsaver.controllers.securities.UserSecurity;
import com.passwordsaver.dto.AdminUsersDto;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.dto.UserKeysDto;
import com.passwordsaver.services.UserService;

/**
 * Servlet implementation class updateProfileServlet
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
        super();
        userService = ServicesContainer.getInstance().getUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(UserSecurity.isLoginedUser(request)) {
			UserDto userDto = (UserDto)request.getSession().getAttribute(AttributeNames.USER_DTO.toString());
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.UPDATE_PROFILE_JSP.toString())
			.forward(request, response);
		}else {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
			.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(UserSecurity.isLoginedUser(request)) {
			UserDto userDto = (UserDto)request.getSession().getAttribute(AttributeNames.USER_DTO.toString());
			String newLogin = request.getParameter(ParameterNames.NEW_LOGIN.toString());
			String newPassword = request.getParameter(ParameterNames.NEW_PASSWORD.toString());
			Long idUser = userService.getIdUserByUserDto(userDto);
			if( !userDto.getLogin().equals(newLogin) && userService.isExistUser(newLogin) ){
				
				request.setAttribute(AttributeNames.ERROR.toString(), "notAvailableLogin");
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.UPDATE_PROFILE_JSP.toString())
				.forward(request, response);
				return;
				
			}else if(newLogin.length()<5 || newLogin.length()>30){
				request.setAttribute(AttributeNames.ERROR.toString(), "wrongLoginFormat");
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.UPDATE_PROFILE_JSP.toString())
				.forward(request, response);
				return;
			}else if(newPassword.length()<5 || newPassword.length()>30){
				request.setAttribute(AttributeNames.ERROR.toString(), "wrongPasswordFormat");
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.UPDATE_PROFILE_JSP.toString())
				.forward(request, response);
				return;
			}else{
				UserDto newUserDto = new UserDto(newLogin, newPassword, userDto.getRole());
				userService.setUserDto(newUserDto, idUser);
				request.getSession().setAttribute(AttributeNames.USER_DTO.toString(), newUserDto);
				response.sendRedirect(request.getContextPath()+ControllerUrls.PERSONAL_CABINET_SERVLET);
			}
			
		}else {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
			.forward(request, response);
		}
				
	}

}
