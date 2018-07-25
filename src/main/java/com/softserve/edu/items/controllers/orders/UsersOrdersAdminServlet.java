package com.softserve.edu.items.controllers.orders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.softserve.edu.items.dto.OrderDto;
import com.softserve.edu.items.dto.UserDto;
import com.softserve.edu.items.dto.UserOrdersDto;
import com.softserve.edu.items.services.IocContainer;
import com.softserve.edu.items.services.UserService;

/**
 * Servlet implementation class UsersOrdersAdminServlet
 */
@WebServlet("/usersordersadmin")
public class UsersOrdersAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
    
    public UsersOrdersAdminServlet() {
        super(); 
        userService = IocContainer.get().getUserService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numberOfUser = userService.getAllUsersName().size();
		
		if (!Security.isActiveSession(request, response)) {
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
				.forward(request, response);
		} 
		else {
		UserDto userDto = userService.getUserDto((LoginDto)request.getSession(false).getAttribute("loginDto"));
		//String [] arrayNumberOfUser= new String[numberOfUser];		
		List<String> listAllUsersName= new ArrayList<>();
		for(int i=0; i<numberOfUser; i++) {
			if(!userService.getAllUsersName().get(i).equals(userDto.getName())) {
			listAllUsersName.add(userService.getAllUsersName().get(i));
			}
		}
		request.setAttribute("arrayNumberOfUser", listAllUsersName);	
		
				
		if(request.getParameter("nameOfUserSelect")!=null &&
				!request.getParameter("nameOfUserSelect").equals("Choose User") ) {//the data send in get method(in URL line) check if User doesn`t edit nameOfUserSelect; check if this name exist in DB
			String nameOfUserSelect= request.getParameter("nameOfUserSelect");
			request.setAttribute("nameOfUserSelect", nameOfUserSelect);	
			
			HttpSession session = request.getSession(false);
			session.setAttribute("idUserAddItem", userService.getIdUserByName(nameOfUserSelect));
			
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
			
			try {
			UserOrdersDto userOrdersDto = IocContainer.get()
					.getUserOrdersServise()
					.getUserOrders(IocContainer.get()
					.getUserService()
					.getUserDtoByName((String)request.getAttribute("nameOfUserSelect")));			
			request.setAttribute("userOrdersDto", userOrdersDto);
			request.setAttribute("pageCount", 
					(((int)userOrdersDto.getOrders().size()/ Integer.parseInt(visibleOrders))+1));
			}
			catch(Exception e) {
				List<OrderDto> nullList =new ArrayList<>();
				request.setAttribute("userOrdersDto",  new UserOrdersDto("", nullList));
				request.setAttribute("pageCount", 0);
				request.setAttribute("visibleOrders= ",0);
				System.out.println("RuntimeException, message: " + e.getMessage());
			}			
			
						
			if(request.getParameter("pageNumber") == null || 
				Integer.parseInt(request.getParameter("pageNumber"))<=1 ) {
				request.setAttribute("pageNumber", 1);
			}
			else {
				request.setAttribute("pageNumber", Integer.parseInt(request.getParameter("pageNumber")));
			}						
			
			request.setAttribute("redirectURLInOrderCount", RedirectURLInOrderCount.ORDERS_USERS_ADMIN_SERVLET.toString());
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.ORDERS_USERS_ADMIN_JSP.toString())
			.forward(request, response);
		}
		else {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.ORDERS_USERS_ADMIN_JSP.toString())
			.forward(request, response);
		}
		}
				
			
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
