package com.softserve.edu.project.controller.teams;

import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.ViewUrls;
import com.softserve.edu.project.controller.commons.UserInSession;
import com.softserve.edu.project.service.UserHasTeamsService;
import com.softserve.edu.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myTeamAdd")
public class MyTeamAddServlet extends HttpServlet {
    private UserHasTeamsService userHasTeamsService;
    private UserService userService;

    public MyTeamAddServlet() {
        this.userHasTeamsService = new UserHasTeamsService();
        userService=new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(UserInSession.isUserInCurrectSession(request,response)==false){
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
                    .forward(request, response);
        }else
userHasTeamsService.addUserTeam(userService.findUserByUsername
        (request.getParameter("username")).getId(),Long.parseLong(request.getParameter("idTeam")));
        request.setAttribute("teamsList",userHasTeamsService.getAllUsersHasTeamsDto(userService.findUserByUsername
                (request.getParameter("username")).getId()));
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.USER_HAS_TEAMS_JSP.toString()+"?username="+request.getParameter("username"))
                .forward(request, response);


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}

