package com.passwordsaver.controllers.admins;

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
import com.passwordsaver.controllers.securities.AdminSecurity;
import com.passwordsaver.dto.AdminUsersDto;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.services.UserService;

/**
 * Servlet implementation class UserUpdateByAdminServlet
 */
@WebServlet("/UserUpdateByAdminServlet")
public class UserUpdateByAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateByAdminServlet() {
        super();
        userService = ServicesContainer.getInstance().getUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(AdminSecurity.isLoginedAdmin(request)) {
			String login = request.getParameter(ParameterNames.USER_LOGIN.toString());
			UserDto updatedUser = userService.getUserDtoByLogin(login); 
			request.setAttribute(AttributeNames.UPDATED_USER_DTO.toString(), updatedUser);
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.USER_UPDATE_BY_ADMIN.toString())
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
		if(AdminSecurity.isLoginedAdmin(request)) {
			
		String oldLogin = request.getParameter(ParameterNames.OLD_LOGIN.toString());
		String newLogin = request.getParameter(ParameterNames.NEW_LOGIN.toString());
		String newPassword = request.getParameter(ParameterNames.NEW_PASSWORD.toString());
		String newRole = request.getParameter(ParameterNames.NEW_ROLE.toString());
		
		
		Long id = userService.getIdUserByLogin(oldLogin);
		UserDto updatedUserDto = new UserDto(newLogin, newPassword, newRole);
		userService.setUserDto(updatedUserDto, id);
		
		response.sendRedirect(request.getContextPath()+ControllerUrls.PERSONAL_CABINET_SERVLET);
		}else {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
			.forward(request, response);
		}
	}

}
