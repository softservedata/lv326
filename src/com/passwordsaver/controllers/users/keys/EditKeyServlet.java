package com.passwordsaver.controllers.users.keys;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.passwordsaver.controllers.AttributeNames;
import com.passwordsaver.controllers.ControllerUrls;
import com.passwordsaver.controllers.ParameterNames;
import com.passwordsaver.controllers.ServicesContainer;
import com.passwordsaver.controllers.ViewUrls;
import com.passwordsaver.controllers.securities.UserSecurity;
import com.passwordsaver.dto.KeyDto;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.dto.UserKeysDto;
import com.passwordsaver.services.KeyService;
import com.passwordsaver.services.UserService;

/**
 * Servlet implementation class SetKeyServlet
 */
@WebServlet("/EditKeyServlet")
public class EditKeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       KeyService keyService;
       UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditKeyServlet() {
        super();
        keyService = ServicesContainer.getInstance().getKeyService();
        userService = ServicesContainer.getInstance().getUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(UserSecurity.isLoginedUser(request)) {
			Long idKey = Long.parseLong(request.getParameter(ParameterNames.ID_KEY.toString()));
			KeyDto keyDto = keyService.getKeyDto(idKey);
			request.setAttribute("keyDto", keyDto);
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.EDIT_KEY_JSP.toString())
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
			
			Long idKey = Long.parseLong(request.getParameter(ParameterNames.ID_KEY.toString()));
			String newService = request.getParameter(ParameterNames.NEW_KEY_SERVICE.toString());
			String newPassword = request.getParameter(ParameterNames.NEW_KEY_PASSWORD.toString());
			
			KeyDto keyDto = new KeyDto(idKey, newService, newPassword);
			
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
