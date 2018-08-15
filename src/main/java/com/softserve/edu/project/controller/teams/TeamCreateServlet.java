package com.softserve.edu.project.controller.teams;

import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.ViewUrls;
import com.softserve.edu.project.controller.commons.UserInSession;
import com.softserve.edu.project.entity.Team;
import com.softserve.edu.project.service.IocContainer;
import com.softserve.edu.project.service.TeamService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teamcreate")
public class TeamCreateServlet extends HttpServlet {
    private TeamService teamService;

    public TeamCreateServlet() {
        teamService=IocContainer.get().getTeamService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(UserInSession.isUserInCurrectSession(request,response)==false){
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
                    .forward(request, response);
        }else
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.TEAM_CREATE_JSP.toString())
                .forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (UserInSession.isUserInCurrectSession(request, response) == false) {
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
                    .forward(request, response);
        } else {
            Team team = teamService.findTeamByNameCountryCity(request.getParameter("name"), request.getParameter("country"), request.getParameter("city"));
            if (team.getId() != null) {
                request.setAttribute("teamEdit", new Team(0L, request.getParameter("name"), request.getParameter("country"), request.getParameter("city")));
                request.setAttribute("errorTeam", "this team is exist");
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.TEAM_CREATE_JSP.toString())
                        .forward(request, response);
            } else {
                team = new Team(0L, request.getParameter("name"), request.getParameter("country"), request.getParameter("city"));
                teamService.createTeam(team);
                request.setAttribute("teamsList", teamService.getAllTeams());
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.TEAM_LIST_JSP.toString())
                        .forward(request, response);
            }
        }
    }
}
