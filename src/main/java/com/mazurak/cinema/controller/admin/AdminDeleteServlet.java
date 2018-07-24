package com.mazurak.cinema.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mazurak.cinema.controller.common.Security;
import com.mazurak.cinema.controllers.enums.ControllerUrls;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.UserService;

@WebServlet("/admin/delete/*")
public class AdminDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	public AdminDeleteServlet() {
		this.userService = IoCContainer.getInit().getUserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// check active session 
		Security.isActiveSession(req, resp);
		
		String uri = req.getRequestURI().toString();
		String[] split = uri.split("/");
		Long idUser = Long.parseLong(split[split.length - 1]);

		if (userService.deleteUserFromDB(idUser)) {
			req.setAttribute("deleteAttr", "is successfully deleted");
		} else {
			req.setAttribute("deleteAttr", "is not deleted");
		}
		resp.sendRedirect(req.getContextPath() + ControllerUrls.ADMIN_USER_LIST_SERVLET);
	}
}

