package com.softserve.edu.project.service;



import com.softserve.edu.project.dao.TeamDao;
import com.softserve.edu.project.dao.UserHasTeamsDao;
import com.softserve.edu.project.entity.Team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeamService {

    private TeamDao teamDao;
    private UserHasTeamsDao userHasTeamsDao;

    public TeamService() {
        teamDao = new TeamDao();
        userHasTeamsDao=new UserHasTeamsDao();
    }

    public TeamService(TeamDao teamDao,UserHasTeamsDao ud) {
        this.teamDao = teamDao;
        this.userHasTeamsDao=ud;
    }

    public TeamService(TeamDao teamDao) {
        teamDao = teamDao;
    }

    public void createTransferTeam(){
        if(findTeamByNameCountryCity("TRANSFER","TRANSFER","TRANSFER").getId()==null){
            teamDao.insert(new Team(0L,"TRANSFER","TRANSFER","TRANSFER"));
        }
    }

    public List<Team> getAllTeams(){
        List<Team> teams=teamDao.getAll();
        if(teams.size()==0){
            return new ArrayList<Team>();
        }
        return teams;
    }
    public Team findTeamById(Long id_team)
    {
        Team team=teamDao.getById(id_team);
        if(team.getId()==null){
            return new Team();
        }
        return team;
    }

    public List<Team> findTeamsByName(String name_team){
        List<Team> teams=teamDao.getByFieldName("name",name_team);
        if(teams.size()==0){
            return new ArrayList<Team>();
        }return teams;
    }

    public boolean createTeam(Team team){
        if(findTeamByNameCountryCity(team.getName(),team.getCountry(),team.getCity()).getId()==null) {
            teamDao.insert(new Team(0L
                    , team.getName()
                    , MyFilter.firstLetterUppCaseOtherLowercase(team.getCountry())
                    , MyFilter.firstLetterUppCaseOtherLowercase(team.getCity())
            ));
            return true;
        }
        return false;
    }
    public Team findTeamByNameCountryCity(String name,String country,String city){
        List<Team> teams=findTeamsByName(name);
        for (Team t: teams
                ) {
            if( t.getCountry().equalsIgnoreCase(country) &&  t.getCity().equalsIgnoreCase(city)){
                return t;
            }
        }return new Team();
    }

    public boolean deleteTeamById(Long id_team){
        Team team1=findTeamById(id_team);
        Team transfer=findTeamByNameCountryCity("TRANSFER","TRANSFER","TRANSFER");
        if(team1.getId()==null || team1.getId()==transfer.getId()) {
            return false;
        }else
            userHasTeamsDao.deleteByFieldName("idTeam",id_team.toString());
            teamDao.delete(team1);
        return true;
    }
    public Set<Team> findAllTeamsByText(String team_string){
        Set<Team>teams=new HashSet<Team>();
        teams.addAll(teamDao.getByFieldName("name",team_string));
        teams.addAll(teamDao.getByFieldName("country",MyFilter.firstLetterUppCaseOtherLowercase(team_string)));
        teams.addAll(teamDao.getByFieldName("city",MyFilter.firstLetterUppCaseOtherLowercase(team_string)));
        return teams;
    }

    public List<Team> getTeamPagination(int currentPage, int recordsPerPage){
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Team>teams=teamDao.getPagination(start,recordsPerPage);
        if(teams.isEmpty()){
            return new ArrayList<Team>();
        }
        return teams;
    }
    public int getNumberOfRows() {
        int numOfRows = 0;
        numOfRows=getAllTeams().size();
        return numOfRows;
    }

    public void updateTeam(Team team){
        teamDao.updateByEntity(team);
    }
}
