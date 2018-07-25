package com.softserve.edu.items.controllers.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.commons.Security;
import com.softserve.edu.items.services.IocContainer;
import com.softserve.edu.items.services.OrderServise;

/**
 * Servlet implementation class DeleteOrderServlet
 */
@WebServlet("/orderdelete")
public class OrderProfileDeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderServise orderServise;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderProfileDeleteOrderServlet() {
        super();
        orderServise = IocContainer.get().getOrderServise(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!Security.isActiveSession(request, response)) {
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
				.forward(request, response);
		} 
		else {
		//idItem
		orderServise.deleteOrder(Long
				.parseLong(request.getParameter("idOrder")));
		getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ControllerUrls.ORDERS_SERVLET.toString())
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
