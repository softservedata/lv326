package com.softserve.edu.project.controller.users;




import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.ViewUrls;
import com.softserve.edu.project.dto.LoginDto;
import com.softserve.edu.project.service.IocContainer;
import com.softserve.edu.project.service.RoleService;
import com.softserve.edu.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet({ "", "/login" })
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 UserService userService;
	 RoleService roleService;

    public UserLoginServlet() {
        super();
		userService =IocContainer.get().getUserService();
		roleService=IocContainer.get().getRoleService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		roleService.createRolesIfTheyDontExist();
		userService.createAdmin();
		getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
				.forward(request, response);

	}

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
			//
			response.sendRedirect(request.getContextPath()
					+ ControllerUrls.TEAM_LIST_SERVLET.toString());

		} else {
			request.setAttribute("error", "Invalid Login or Password");
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
				.forward(request, response);
		}
	}

}
