package com.softserve.edu.project.controller.users;



import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.ViewUrls;
import com.softserve.edu.project.controller.commons.UserInSession;
import com.softserve.edu.project.dto.UserDto;
import com.softserve.edu.project.service.IocContainer;
import com.softserve.edu.project.service.MyFilter;
import com.softserve.edu.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/usercreate")
public class UserCreateServlet extends HttpServlet {

    private UserService userService;

    public UserCreateServlet()
    {
        this.userService = IocContainer.get().getUserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.USER_CREATE_JSP.toString())
                .forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            UserDto userDto = new UserDto(
                    request.getParameter("username")
                    , request.getParameter("password")
                    , MyFilter.firstLetterUppCaseOtherLowercase(request.getParameter("firstname"))
                    , MyFilter.firstLetterUppCaseOtherLowercase(request.getParameter("lastname"))
                    , request.getParameter("role")
            );
            if (userService.findUserByUsername(userDto.getUsername()).getId() == null) {
                userService.createUserDto(userDto);
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                        .forward(request, response);
            } else {
                request.setAttribute("errorUser", "Username is exist, pls try another");
                request.setAttribute("user", userDto);
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.USER_CREATE_JSP.toString())
                        .include(request, response);
            }
        }

    }


