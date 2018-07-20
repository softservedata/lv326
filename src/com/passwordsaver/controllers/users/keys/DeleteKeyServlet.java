package com.passwordsaver.controllers.users.keys;

import java.io.IOException;
import java.util.List;

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
import com.passwordsaver.controllers.securities.KeySecurity;
import com.passwordsaver.controllers.securities.UserSecurity;
import com.passwordsaver.dto.KeyDto;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.dto.UserKeysDto;
import com.passwordsaver.services.KeyService;
import com.passwordsaver.services.UserService;

/**
 * Servlet implementation class deleteKeyServlet
 */
@WebServlet("/DeleteKeyServlet")
public class DeleteKeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private KeyService keyService;
       private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteKeyServlet() {
        super();
        keyService = ServicesContainer.getInstance().getKeyService();
        userService = ServicesContainer.getInstance().getUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(UserSecurity.isLoginedUser(request)) {
			UserDto userDto = (UserDto)request.getSession().getAttribute(AttributeNames.USER_DTO.toString());
			
			Long idKey = Long.parseLong(request.getParameter(ParameterNames.ID_KEY.toString()));
			if(KeySecurity.isUrerKey(userService, keyService, userDto, idKey)) {
				
				KeyDto keyDto = keyService.getKeyDto(idKey);
				keyService.deleteKeyByDto(keyDto);
				response.sendRedirect(request.getContextPath()+ControllerUrls.PERSONAL_CABINET_SERVLET);
				
			}else {
				response.getWriter().println("It is not your key");
			}
			
		}else {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
			.forward(request, response);
		}
		
	}

}
