package com.softserve.edu.items.controllers.commons;

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
import com.softserve.edu.items.dto.UserItemsDto;
import com.softserve.edu.items.services.IocContainer;

/**
 * Servlet implementation class UserProfileEditServlet
 */
@WebServlet("/useritems")
public class UserItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserItemsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		boolean isActiveSession = true;
		HttpSession session = request.getSession(false); // Do not Create new Session
		Cookie idSessionCookie = null;
		for (Cookie currentCookie : request.getCookies()) {
			if (currentCookie.getName().equals("id_session")) {
				idSessionCookie = currentCookie;
				break;
			}
		}
		isActiveSession = isActiveSession && (session != null)
				&& (session.getAttribute("loginDto") != null)
				&& (((LoginDto) (session.getAttribute("loginDto"))).getLogin() != null)
				&& (idSessionCookie != null);
		isActiveSession = isActiveSession
				&& (idSessionCookie.getValue().equals(session.getId()));
		//
		//System.out.println("\t/useritems doGet");
		if (!isActiveSession) {
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
				.forward(request, response);
		} else {
			// Get All Items All_userItemsDto
			UserItemsDto userItemsDto = IocContainer.get()
					.getUserItemsServise()
					.getUserItems(IocContainer.get()
							.getUserService()
							.getUserDto(((LoginDto)(request.getSession(false).getAttribute("loginDto")))));
			request.setAttribute("userItemsDto", userItemsDto);
			//
			request.setAttribute("countItems",
					String.valueOf(userItemsDto.getItems().size()));
			//
			Cookie visibleItemsCookie = null;
			for (Cookie currentCookie : request.getCookies()) {
				if (currentCookie.getName().equals("visible_items")) {
					visibleItemsCookie = currentCookie;
					break;
				}
			}
			String visibleItems = "100000";
			if (visibleItemsCookie != null) {
				visibleItems = visibleItemsCookie.getValue();
			}
			request.setAttribute("visibleItems", visibleItems);
			//
			int pageNumber = 5;
			if (request.getParameter("pageNumber") != null) {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			}
			request.setAttribute("pageNumber",
					String.valueOf(pageNumber));
			// TODO Get custom Items for userItemsDto, use visibleItems
			//
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.USER_ITEMS_JSP.toString())
				.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("\t/useritems doPost");
		doGet(request, response);
	}

}
