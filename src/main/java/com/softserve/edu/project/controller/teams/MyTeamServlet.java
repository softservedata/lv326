package com.softserve.edu.project.controller.teams;


import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.ViewUrls;

import com.softserve.edu.project.controller.commons.UserInSession;
import com.softserve.edu.project.service.IocContainer;
import com.softserve.edu.project.service.UserHasTeamsService;
import com.softserve.edu.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/myteams")
public class MyTeamServlet extends HttpServlet {
    private UserHasTeamsService userHasTeamsService;
    private UserService userService;

    public MyTeamServlet() {
        this.userHasTeamsService = new UserHasTeamsService();
        userService=IocContainer.get().getUserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
if(UserInSession.isUserInCurrectSession(request,response)==false){
    getServletConfig()
            .getServletContext()
            .getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
            .forward(request, response);
}else
        request.setAttribute("teamsList",userHasTeamsService.getAllUsersHasTeamsDto(userService.findUserByUsername
                (request.getParameter("username")).getId()));
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.USER_HAS_TEAMS_JSP.toString())
                .forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}

