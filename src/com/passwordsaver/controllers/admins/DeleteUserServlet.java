package com.passwordsaver.controllers.admins;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passwordsaver.controllers.ControllerUrls;
import com.passwordsaver.controllers.ParameterNames;
import com.passwordsaver.controllers.ServicesContainer;
import com.passwordsaver.controllers.securities.AdminSecurity;
import com.passwordsaver.dto.AdminUsersDto;
import com.passwordsaver.services.UserService;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        userService = ServicesContainer.getInstance().getUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(AdminSecurity.isLoginedAdmin(request)) {
			String deletedUserLogin = request.getParameter(ParameterNames.USER_LOGIN.toString());
			userService.deleteUserByLogin(deletedUserLogin);
			response.sendRedirect(request.getContextPath()+ControllerUrls.PERSONAL_CABINET_SERVLET);
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
		
		
	}
}
