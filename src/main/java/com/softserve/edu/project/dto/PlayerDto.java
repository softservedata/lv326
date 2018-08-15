package com.softserve.edu.project.dto;

public class PlayerDto {

    private Long idPlayer;
    private String name;
    private String surname;
    private String nameTeam;
    private String countryTeam;
    private String cityTeam;

    public PlayerDto(Long idPlayer, String name, String surname, String nameTeam, String countryTeam, String cityTeam) {
        this.idPlayer = idPlayer;
        this.name = name;
        this.surname = surname;
        this.nameTeam = nameTeam;
        this.countryTeam = countryTeam;
        this.cityTeam = cityTeam;
    }

    public Long getIdPlayer() {
        return idPlayer;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getCountry() {
        return countryTeam;
    }

    public void setCountry(String countryTeam) {
        this.countryTeam = countryTeam;
    }

    public String getCity() {
        return cityTeam;
    }

    public void setCity(String cityTeam) {
        this.cityTeam = cityTeam;
    }
}
