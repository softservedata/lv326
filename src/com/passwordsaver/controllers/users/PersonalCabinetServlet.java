package com.passwordsaver.controllers.users;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.passwordsaver.controllers.AttributeNames;
import com.passwordsaver.controllers.ControllerUrls;
import com.passwordsaver.controllers.ParameterNames;
import com.passwordsaver.controllers.RoleNames;
import com.passwordsaver.controllers.ServicesContainer;
import com.passwordsaver.controllers.ViewUrls;
import com.passwordsaver.controllers.securities.UserSecurity;
import com.passwordsaver.dto.AdminUsersDto;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.dto.UserKeysDto;
import com.passwordsaver.services.UserKeysService;
import com.passwordsaver.services.UserService;

/**
 * Servlet implementation class PersonalCabinetServlet
 */
@WebServlet("/PersonalCabinetServlet")
public class PersonalCabinetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserKeysService userKeysService;
       private UserService userService;
       private static final int DEFAULT_AVAILABLE_PAGES_COUNT = 5;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalCabinetServlet() {
        super();
        userKeysService = ServicesContainer.getInstance().getUserKeysService();
        userService = ServicesContainer.getInstance().getUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(UserSecurity.isLoginedUser(request)) {
			int currentPage = 1;
			int pageOffset = 10;
			String substring = "";
			if(request.getParameter(ParameterNames.INDEX_OF_CURRENT_PAGE.toString())!=null) {
				currentPage = Integer.parseInt(request.getParameter(ParameterNames.INDEX_OF_CURRENT_PAGE.toString()));
			}
			if(request.getParameter(ParameterNames.VISIBLE_ITEMS_COUNT.toString())!=null) {
				pageOffset = Integer.parseInt(request.getParameter(ParameterNames.VISIBLE_ITEMS_COUNT.toString()));
			}
			if(request.getParameter(ParameterNames.SUBSTRING.toString())!=null) {
				substring = request.getParameter(ParameterNames.SUBSTRING.toString());
			}
			UserDto userDto = (UserDto)request.getSession().getAttribute(AttributeNames.USER_DTO.toString());
			
			
			if(userDto.getRole().equals(RoleNames.USER.toString())) {
				UserKeysDto userKeysDto = userKeysService.getUserKeys(userDto, currentPage, pageOffset, substring);
				
				request.setAttribute(AttributeNames.USER_KEYS_DTO.toString(), userKeysDto);
				request.setAttribute(AttributeNames.INDEX_OF_CURRENT_PAGE.toString(), new Integer(currentPage));
				setFirstItemNumber(request, currentPage, pageOffset);
				setBoundaryPages(request, currentPage, userKeysDto.getPageCount());
				
				getServletConfig()
					.getServletContext()
					.getRequestDispatcher(ViewUrls.USER_KEYS_JSP.toString())
					.forward(request, response);
				
			}else if(userDto.getRole().equals(RoleNames.ADMINISTRATOR.toString())) {
				AdminUsersDto adminUsersDto = userService.getUsersDto(userDto, currentPage, pageOffset, substring);
				
				request.setAttribute(AttributeNames.ADMIN_USERS_DTO.toString(), adminUsersDto);
				request.setAttribute(AttributeNames.INDEX_OF_CURRENT_PAGE.toString(), new Integer(currentPage));
				setFirstItemNumber(request, currentPage, pageOffset);
				setBoundaryPages(request, currentPage, adminUsersDto.getPageCount());
				
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.ADMIN_PROFILE_JSP.toString())
				.forward(request, response);
			}else {}
		
		}else {
			
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
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
	
	
	
	private boolean setFirstItemNumber(HttpServletRequest request, int currentPage, int pageOffset) {
		int from=  (currentPage-1) * pageOffset +1;
		request.setAttribute(AttributeNames.INDEX_OF_FIRST_ITEM_ON_PAGE.toString(), new Integer(from));
		return true;
	}
	private boolean setBoundaryPages(HttpServletRequest request, int currentPage, int pageCount) {
		int rightBoundaryPage = currentPage;
		int leftBoundaryPage = currentPage;
		for(int pages=DEFAULT_AVAILABLE_PAGES_COUNT-1; pages>0; ) {
			if(leftBoundaryPage==1 && rightBoundaryPage==pageCount) {
				break;
			}
			if(leftBoundaryPage>1) {
				pages--;
				leftBoundaryPage--;
			}
			if(rightBoundaryPage<pageCount && pages>0) {
				pages--;
				rightBoundaryPage++;
			}
		}
		request.setAttribute(AttributeNames.LEFT_BOUNDARY_PAGE.toString(), new Integer(leftBoundaryPage));
		request.setAttribute(AttributeNames.RIGHT_BOUNDARY_PAGE.toString(), new Integer(rightBoundaryPage));
		return true;
	}
}
