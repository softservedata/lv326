package com.softserve.edu.project.controller.users;

import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.ViewUrls;
import com.softserve.edu.project.controller.commons.UserInSession;
import com.softserve.edu.project.dto.LoginDto;
import com.softserve.edu.project.service.IocContainer;
import com.softserve.edu.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/userprofile")
public class UserProfileServlet extends HttpServlet {

    private UserService userService;

    public UserProfileServlet() {

        this.userService = IocContainer.get().getUserService();
        userService.createAdmin();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(UserInSession.isUserInCurrectSession(request,response)==false){
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
                    .forward(request, response);
        } else
                request.setAttribute("user",userService.findUserDtoByUsername(request.getParameter("username")));
                 getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.USER_PROFILE_JSP.toString())
                .forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
