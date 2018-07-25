package com.softserve.edu.items.controllers.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.RedirectURLInOrderCount;
/**
 * Servlet implementation class PageOffsetServlet
 */
@WebServlet("/ordercount")
public class OrderCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = new Cookie("visible_orders",
				request.getParameter("visibleOrders"));		
		response.addCookie(cookie);
		
		 
		if(request.getParameter("redirectURLInOrderCount")
				.equals(RedirectURLInOrderCount.ORDERS_SERVLET.toString())) {
			response.sendRedirect(request.getContextPath()
					+ ControllerUrls.ORDERS_SERVLET.toString());
		}
		else if(request.getParameter("redirectURLInOrderCount")
				.equals(RedirectURLInOrderCount.ORDERS_USERS_ADMIN_SERVLET.toString())) {
			request.setAttribute("nameOfUserSelect", request.getParameter("nameOfUserSelect"));
			response.sendRedirect(request.getContextPath()
					+ ControllerUrls.ORDERS_USERS_ADMIN_SERVLET.toString());			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}

}
