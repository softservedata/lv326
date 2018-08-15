package com.softserve.edu.project.controller.users;

import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.ViewUrls;
import com.softserve.edu.project.controller.commons.UserInSession;
import com.softserve.edu.project.dto.UserDto;
import com.softserve.edu.project.service.IocContainer;
import com.softserve.edu.project.service.RoleService;
import com.softserve.edu.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/useredit")
public class UserEditServlet extends HttpServlet {
    private UserService userService;
    private RoleService roleService;

    public UserEditServlet() {

        this.userService =  this.userService = IocContainer.get().getUserService();
        this.roleService=new RoleService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(UserInSession.isUserInCurrectSession(request,response)==false){
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
                    .forward(request, response);
        }else
      request.setAttribute("userEdit",userService.findUserDtoByUsername(request.getParameter("username")));
      request.setAttribute("roles",roleService.getAllRoles());
                 getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.USER_EDIT_JSP.toString())
                .forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(UserInSession.isUserInCurrectSession(request,response)==false){
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
                    .forward(request, response);
        }else
userService.updateUser(new UserDto(
        request.getParameter("username"),
        request.getParameter("password"),
        request.getParameter("name"),
        request.getParameter("surname"),
        request.getParameter("role")
));
        request.setAttribute("usersList",userService.getAllUsersDto());
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.USER_LIST_JSP.toString())
                .forward(request, response);
    }

}
