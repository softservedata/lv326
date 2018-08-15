package com.softserve.edu.project.dto;

import java.util.ArrayList;
import java.util.List;

public class UserHasPlayersDto {
  private  List<PlayerDto> players;

    public UserHasPlayersDto() {
        this.players = new ArrayList<PlayerDto>();
    }

    public UserHasPlayersDto(List<PlayerDto> players) {
        this.players = players;
    }

    public void addPlayerDto(PlayerDto playerDto){
        players.add(playerDto);
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }
}
