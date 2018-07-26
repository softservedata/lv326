package com.softserve.edu.comics.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserComicsDto {

    private String userLogin;
    private List<ComicsDto> comics;


    public UserComicsDto(String userLogin) {
        this.userLogin = userLogin;
        this.comics = new ArrayList<>();
    }

    public void addComicsDto(ComicsDto comicsDto){
        comics.add(comicsDto);
    }

}
