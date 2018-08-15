package com.softserve.edu.project.dto;

import com.softserve.edu.project.entity.Team;

import java.util.ArrayList;
import java.util.List;

public class UserHasTeamsDto {
    private String username;
    private List<Team> teams;

    public UserHasTeamsDto(String username) {
        this.username = username;
        this.teams = new ArrayList<Team>();
    }

    public UserHasTeamsDto(String username, List<Team> teams) {
        this.username = username;
        this.teams = teams;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void addTeam(Team team){
        teams.add(team);
}
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
