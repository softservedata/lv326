package com.softserve.edu.items.controllers.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.controllers.commons.Security;
import com.softserve.edu.items.dao.UserDao;
import com.softserve.edu.items.dto.UserDto;
import com.softserve.edu.items.services.IocContainer;
import com.softserve.edu.items.services.UserService;

/**
 * Servlet implementation class UserProfilesEditAdminServlet
 */
@WebServlet("/usersprofileseditadmin")
public class UsersProfilesEditAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersProfilesEditAdminServlet() {
        super();
        userService = IocContainer.get().getUserService();
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
		UserDao userDao= new UserDao();
		int numberOfUser=userDao.getAll().size();
		String [] arrayNumberOfUser= new String[numberOfUser];		
		for(int i=0; i<numberOfUser; i++) {
			arrayNumberOfUser[i]=userDao.getAll().get(i).getName();
		}
		/*int numberOfUser = userService.getAllUsersName().size();
		UserDto userDto = userService.getUserDto((LoginDto)request.getSession(false).getAttribute("loginDto"));		
		List<String> listAllUsersName= new ArrayList<>();
		for(int i=0; i<numberOfUser; i++) {
			if(!userService.getAllUsersName().get(i).equals(userDto.getName())) {
			listAllUsersName.add(userService.getAllUsersName().get(i));
			}
		}*/
		request.setAttribute("arrayNumberOfUser", arrayNumberOfUser);	
				
		if(request.getParameter("nameOfUserSelect")!=null && !request.getParameter("nameOfUserSelect").equals("Choose User")) {//the data send in get method(in URL line) check if User doesn`t edit nameOfUserSelect; check if this name exist in DB
			String nameOfUserSelect= request.getParameter("nameOfUserSelect");
			request.setAttribute("nameOfUserSelect", nameOfUserSelect);
			if(userDao.getByFieldName("name", nameOfUserSelect).get(0).getIdRole()==1) {
				request.setAttribute("currentRole", "Administrator");
			}
			else {
				request.setAttribute("currentRole", "User");
			}
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.EDIT_PROFILES_USERS_JSP.toString())
			.forward(request, response);
		}		
		else {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.EDIT_PROFILES_USERS_JSP.toString())
			.forward(request, response);
		}		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		UserDto userDto = userService.getUserDtoByName( request.getParameter("name"));
		
        if( request.getParameter("newRole")!= null && 
        		request.getParameter("newRole").equals("Administrator") &&
        		!request.getParameter("newRole").equals(request.getParameter("currentRole"))) {			
			        
        	userDto.setRole(request.getParameter("newRole"));
        	userService.setUserDto(userDto);
        	
        	request.setAttribute("changesSavedSuccessfully", "Changes Saved Successfully");
			request.setAttribute("nameOfUserSelect", request.getParameter("name"));
			request.setAttribute("currentRole", "Administrator");
        	response.sendRedirect(request.getContextPath()
					+ ControllerUrls.USERS_PROFILES_EDIT_ADMIN_SERVLET.toString());
			/*getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.EDIT_PROFILES_USERS_JSP.toString())
			.forward(request, response);*/
		}
		else if( request.getParameter("newRole")!= null && 
				     request.getParameter("newRole").equals("User") &&
				     !request.getParameter("newRole").equals(request.getParameter("currentRole"))) {
						
			userDto.setRole(request.getParameter("newRole"));
			userService.setUserDto(userDto);
			request.setAttribute("changesSavedSuccessfully", "Changes Saved Successfully");
			request.setAttribute("nameOfUserSelect", request.getParameter("name"));
			request.setAttribute("currentRole", "User");
			response.sendRedirect(request.getContextPath()
					+ ControllerUrls.USERS_PROFILES_EDIT_ADMIN_SERVLET.toString());
			/*getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.EDIT_PROFILES_USERS_JSP.toString())
			.forward(request, response);*/
			
		}
		else {
			// Show Error Validator
			request.setAttribute("nameOfUserSelect", request.getParameter("name"));
			request.setAttribute("currentRole", request.getParameter("currentRole"));
			request.setAttribute("error", "Invalid data entered");
						getServletConfig()
							.getServletContext()
							.getRequestDispatcher(ViewUrls.EDIT_PROFILES_USERS_JSP.toString())
							.forward(request, response);
		}
	}

}
