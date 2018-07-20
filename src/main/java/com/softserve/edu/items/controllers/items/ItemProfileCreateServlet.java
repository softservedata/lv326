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
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.dto.ItemDto;
import com.softserve.edu.items.dto.LoginDto;

/**
 * Servlet implementation class ItemProfileCreateServlet
 */
@WebServlet("/itemcreate")
public class ItemProfileCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemProfileCreateServlet() {
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
		if (!isActiveSession) {
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
				.forward(request, response);
		} else {
			ItemDto itemDto = new ItemDto(0L, "", ""); 
			request.setAttribute("itemDto", itemDto);
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.ITEM_PROFILE_JSP.toString())
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
