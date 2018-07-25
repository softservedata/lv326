package com.softserve.edu.items.controllers.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.RedirectURLInOrderCount;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.controllers.commons.Security;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.dto.UserOrdersDto;
import com.softserve.edu.items.services.IocContainer;

/**
 * Servlet implementation class Order
 */
@WebServlet("/orders")
public class Orders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orders() {
        super();       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());		
		if (!Security.isActiveSession(request, response)) {
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
				.forward(request, response);
		} 		
		else {
			UserOrdersDto userOrdersDto;
			try {
				 userOrdersDto = IocContainer.get()
						.getUserOrdersServise()
						.getUserOrders(IocContainer.get()
						.getUserService()
						.getUserDto(((LoginDto)(request.getSession(false).getAttribute("loginDto")))));	
				request.setAttribute("userOrdersDto", userOrdersDto);
			}
			catch(Exception e) {
				userOrdersDto= new UserOrdersDto(((LoginDto)request.getSession(true).getAttribute("loginDto")).getLogin());
				System.out.println("Runtime exception= "+ e.getMessage());
				request.setAttribute("userOrdersDto", userOrdersDto);
			}
			
		
		
		Cookie visibleOrdersCookie = null;
		for (Cookie currentCookie : request.getCookies()) {
			if (currentCookie.getName().equals("visible_orders")) {
				visibleOrdersCookie = currentCookie;
				break;
			}
		}
		String visibleOrders = "10000";
		if (visibleOrdersCookie != null) {
			visibleOrders = visibleOrdersCookie.getValue();
		}
		request.setAttribute("visibleOrders", visibleOrders);		
					
		if(request.getParameter("pageNumber") == null || 
			Integer.parseInt(request.getParameter("pageNumber"))<=1 ) {
			request.setAttribute("pageNumber", 1);
		}
		else {
			request.setAttribute("pageNumber", Integer.parseInt(request.getParameter("pageNumber")));
			}	
		
		request.setAttribute("pageCount", 
				(((int)userOrdersDto.getOrders().size()/ Integer.parseInt(visibleOrders))+1));
		request.setAttribute("redirectURLInOrderCount", RedirectURLInOrderCount.ORDERS_SERVLET.toString());
										
		getServletConfig()
		.getServletContext()
		.getRequestDispatcher(ViewUrls.ORDERS_JSP.toString())
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
	//System.out.println("getPageOffset= "+userOrdersDto.getPageOffset());

}
