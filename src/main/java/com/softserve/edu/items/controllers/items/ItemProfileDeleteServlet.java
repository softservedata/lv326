package com.softserve.edu.items.controllers.items;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.services.IocContainer;
import com.softserve.edu.items.services.ItemServise;

/**
 * Servlet implementation class ItemProfileDeleteServlet
 */
@WebServlet("/itemdelete")
public class ItemProfileDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemServise itemServise;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemProfileDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
        itemServise = IocContainer.get().getItemServise(); 
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
		if (!isActiveSession) {
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
				.forward(request, response);
		} else {
			//idItem
			itemServise.deleteItem(Long
					.parseLong(request.getParameter("idItem")));
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ControllerUrls.USER_ITEMS_SERVLET.toString())
				.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
