package com.softserve.edu.project.controller.users;



import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.commons.UserInSession;
import com.softserve.edu.project.service.IocContainer;
import com.softserve.edu.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userdelete")
public class UserDeleteServlet extends HttpServlet {
    private UserService userService;

    public UserDeleteServlet() {
        this.userService =  this.userService = IocContainer.get().getUserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(UserInSession.isUserInCurrectSession(request,response)==false){
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
                    .forward(request, response);
        }else
        userService.deleteUserByUsername(request.getParameter("username"));
                 getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ControllerUrls.USER_LIST_SERVLET.toString())
                .forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    }






