package com.softserve.edu.project.controller.teams;

import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.controller.ViewUrls;
import com.softserve.edu.project.entity.Team;
import com.softserve.edu.project.service.IocContainer;
import com.softserve.edu.project.service.MyFilter;
import com.softserve.edu.project.service.TeamService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teamEdit")
public class TeamEditServlet extends HttpServlet {
    private TeamService teamService;

    public TeamEditServlet() {
        teamService=IocContainer.get().getTeamService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("teamEdit",teamService.findTeamById(Long.parseLong(request.getParameter("idTeam"))));
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.TEAM_EDIT_JSP.toString())
                .forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Team team=teamService.findTeamByNameCountryCity(request.getParameter("name")
                ,request.getParameter("country"),request.getParameter("city"));
        if(team.getId()==null){
            teamService.updateTeam(new Team(Long.parseLong(request.getParameter("id")),request.getParameter("name")
                    ,MyFilter.firstLetterUppCaseOtherLowercase(request.getParameter("country"))
                    ,MyFilter.firstLetterUppCaseOtherLowercase(request.getParameter("city"))));
            request.setAttribute("id",team.getId());
            request.setAttribute("teamsList",teamService.getAllTeams());
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.TEAM_LIST_JSP.toString())
                    .forward(request, response);
        }else{
            request.setAttribute("teamEdit",new Team(0L,request.getParameter("name")
                    ,request.getParameter("country"),request.getParameter("city")));
            request.setAttribute("errorTeam","this team is exist");
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.TEAM_EDIT_JSP.toString())
                    .forward(request, response);

        }
    }
}
