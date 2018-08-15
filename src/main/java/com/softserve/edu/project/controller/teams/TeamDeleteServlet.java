package com.softserve.edu.project.controller.teams;



import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.commons.UserInSession;
import com.softserve.edu.project.service.IocContainer;
import com.softserve.edu.project.service.TeamService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teamdelete")
public class TeamDeleteServlet extends HttpServlet {
    private TeamService teamService;

    public TeamDeleteServlet() {
        teamService=IocContainer.get().getTeamService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(UserInSession.isUserInCurrectSession(request,response)==false){
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
                    .forward(request, response);
        }else
        teamService.deleteTeamById(Long.parseLong(request.getParameter("idTeam")));
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ControllerUrls.TEAM_LIST_SERVLET.toString())
                .forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
