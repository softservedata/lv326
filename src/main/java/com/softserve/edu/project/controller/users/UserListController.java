package com.softserve.edu.project.controller.users;



import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.ViewUrls;
import com.softserve.edu.project.controller.commons.UserInSession;
import com.softserve.edu.project.dao.UserDao;
import com.softserve.edu.project.dto.UserDto;
import com.softserve.edu.project.service.IocContainer;
import com.softserve.edu.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet( "/usersList")
public class UserListController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    public UserListController() {
        super();
        userService = IocContainer.get().getUserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (UserInSession.isUserInCurrectSession(request, response) == false) {
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
                    .forward(request, response);
        } else {

            request.setAttribute("usersList", userService.getAllUsersDto());

            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.USER_LIST_JSP.toString())
                    .forward(request, response);

        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
