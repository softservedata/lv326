package com.passwordsaver.controllers.users.keys;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passwordsaver.controllers.AttributeNames;
import com.passwordsaver.controllers.ControllerUrls;
import com.passwordsaver.controllers.ParameterNames;
import com.passwordsaver.controllers.ServicesContainer;
import com.passwordsaver.controllers.ViewUrls;
import com.passwordsaver.controllers.securities.UserSecurity;
import com.passwordsaver.dto.KeyDto;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.services.KeyService;
import com.passwordsaver.services.UserService;

/**
 * Servlet implementation class AddKeyServlet
 */
@WebServlet("/AddKeyServlet")
public class AddKeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private KeyService keyService;
       private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddKeyServlet() {
        super();
        keyService = ServicesContainer.getInstance().getKeyService();
        userService = ServicesContainer.getInstance().getUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(UserSecurity.isLoginedUser(request)) {
			
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.ADD_KEY_JSP.toString())
			.forward(request, response);
			
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
		if(UserSecurity.isLoginedUser(request)) {
			UserDto userDto = (UserDto)request.getSession().getAttribute(AttributeNames.USER_DTO.toString());
			Long idUser = userService.getIdUserByUserDto(userDto);
			
			String service = (String)request.getParameter(ParameterNames.NEW_KEY_SERVICE.toString());
			String password = (String)request.getParameter(ParameterNames.NEW_KEY_PASSWORD.toString());
			
			KeyDto keyDto = new KeyDto(0l,service,password);
			keyService.setKeyDto(keyDto, idUser);
			response.sendRedirect(request.getContextPath()+ControllerUrls.PERSONAL_CABINET_SERVLET.toString());
		}else {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
			.forward(request, response);	
		}
	}

}
