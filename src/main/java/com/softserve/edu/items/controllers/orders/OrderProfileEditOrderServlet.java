package com.softserve.edu.items.controllers.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.controllers.commons.Security;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.dto.OrderDto;
import com.softserve.edu.items.services.IocContainer;
import com.softserve.edu.items.services.OrderServise;
import com.softserve.edu.items.services.UserService;

/**
 * Servlet implementation class EditOrderServlet
 */
@WebServlet("/orderedit")
public class OrderProfileEditOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private OrderServise orderServise;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderProfileEditOrderServlet() {
        super();
        userService = IocContainer.get().getUserService();
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
		OrderDto orderDto = orderServise.geOrderDto(Long.
				parseLong(request.getParameter("idOrder")));
		request.setAttribute("orderDto", orderDto);
		getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.ORDER_PROFILE_JSP.toString())
			.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // Do not Create new Session
		OrderDto orderDto;
		
		if(request.getSession(false).getAttribute("idUserAddItem")!=null&&
				!request.getSession(false).getAttribute("idUserAddItem").equals("")) {
			if(Integer.parseInt(request.getParameter("scope")) >= 1) {
				Long idUserAddItem= (Long) request.getSession(false).getAttribute("idUserAddItem");
				session.setAttribute("idUserAddItem", "");
				orderDto = new OrderDto(
						Long.parseLong(request.getParameter("idOrder")),
						request.getParameter("shop"),
						request.getParameter("address"),
						request.getParameter("production"),
						Integer.parseInt(request.getParameter("scope")),
						request.getParameter("status"));
				orderServise.setOrderDto(orderDto, idUserAddItem );
				
				request.setAttribute("changesSavedSuccessfully", "Changes saved successfully");
				
				response.sendRedirect(request.getContextPath()
						+ ControllerUrls.ORDERS_SERVLET.toString());			
			}
			else {
				orderDto = new OrderDto(
						Long.parseLong(request.getParameter("idOrder")),
						request.getParameter("shop"),
						request.getParameter("address"),
						request.getParameter("production"),
						Integer.parseInt(request.getParameter("scope")),
						request.getParameter("status"));
				request.setAttribute("orderDto", orderDto);
				request.setAttribute("error", "Invalid data entered");
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.ORDER_PROFILE_JSP.toString())
				.forward(request, response);
			}
		}
		else {
			if(Integer.parseInt(request.getParameter("scope")) >= 1) {
				orderDto = new OrderDto(
						Long.parseLong(request.getParameter("idOrder")),
						request.getParameter("shop"),
						request.getParameter("address"),
						request.getParameter("production"),
						Integer.parseInt(request.getParameter("scope")),
						request.getParameter("status"));
				orderServise.setOrderDto(orderDto, userService
						.getIdUserByLogin((LoginDto) (session.getAttribute("loginDto"))));
				request.setAttribute("changesSavedSuccessfully", "Changes saved successfully");
				
				response.sendRedirect(request.getContextPath()
						+ ControllerUrls.ORDERS_SERVLET.toString());			
			}
			else {
				orderDto = new OrderDto(
						Long.parseLong(request.getParameter("idOrder")),
						request.getParameter("shop"),
						request.getParameter("address"),
						request.getParameter("production"),
						Integer.parseInt(request.getParameter("scope")),
						request.getParameter("status"));
				request.setAttribute("orderDto", orderDto);
				request.setAttribute("error", "Invalid data entered");
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.ORDER_PROFILE_JSP.toString())
				.forward(request, response);
			}			
		}		
		
	}

}
