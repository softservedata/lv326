package com.softserve.edu.project.service;

import com.softserve.edu.project.dao.UserDao;
import com.softserve.edu.project.dao.UserHasTeamsDao;
import com.softserve.edu.project.dto.UserHasTeamsDto;
import com.softserve.edu.project.entity.UserHasTeams;

import java.util.ArrayList;
import java.util.List;

public class UserHasTeamsService {

    private UserHasTeamsDao userHasTeamsDao;
    private UserDao userDao;
    private TeamService teamService;

    public UserHasTeamsService() {
        userHasTeamsDao = new UserHasTeamsDao();
        userDao=new UserDao();
        teamService = new TeamService();
    }

    public void addUserTeam(Long id_User,Long id_Team){
        System.out.println(ifUserHasThisTeamAlready(id_User,id_Team));
        if(ifUserHasThisTeamAlready(id_User,id_Team)==false){
            userHasTeamsDao.insert(new UserHasTeams(0L,id_User,id_Team));
        }

    }
    public void deleteUserTeam(Long id_User,Long id_Team){
        for (UserHasTeams u: findAllUsersTeams(id_User)
                ) {
            if(u.getIdTeam()==id_Team){
                userHasTeamsDao.deleteById(u.getId());
            }
        }
    }

    // if user has team in list it will return true
    public boolean ifUserHasThisTeamAlready(Long id_User,Long id_Team){
        for (UserHasTeams u: findAllUsersTeams(id_User)
                ) {
            if(u.getIdTeam()==id_Team){
                return true;
            }
        }
        return false;
    }

    public List<UserHasTeams> findAllUsersTeams(Long id_User){
        List<UserHasTeams>userHasTeams=userHasTeamsDao.getByFieldName("idUser",id_User.toString());
        if(userHasTeams.size()==0){
            return new ArrayList<UserHasTeams>();
        }
        return userHasTeams;
    }
    public UserHasTeamsDto getAllUsersHasTeamsDto(Long id_User){
        UserHasTeamsDto user= new UserHasTeamsDto( userDao.getById(id_User).getUsername());
        for (UserHasTeams u:findAllUsersTeams(id_User)
                ) {
            user.addTeam(teamService.findTeamById(u.getIdTeam()));
        }
        return user;
    }
    public UserHasTeamsDto getUserHasTeamsDtoPagination(Long id_User,int currentPage, int recordsPerPage){
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<UserHasTeams>teams=userHasTeamsDao.getPagination("idUser",id_User.toString(),start,recordsPerPage);
        UserHasTeamsDto userHasTeamsDto= new UserHasTeamsDto( userDao.getById(id_User).getUsername());
        for (UserHasTeams u:teams
                ) {
            userHasTeamsDto.addTeam(teamService.findTeamById(u.getIdTeam()));
        }
        return userHasTeamsDto;
    }
    public int getNumberOfRows(Long id_User) {
        int numOfRows = 0;
        numOfRows=getAllUsersHasTeamsDto(id_User).getTeams().size();
        return numOfRows;
    }
}

