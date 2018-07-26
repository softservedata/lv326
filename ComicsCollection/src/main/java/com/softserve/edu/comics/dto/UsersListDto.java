package com.softserve.edu.comics.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UsersListDto {

    private List<UserDto> users;

    public UsersListDto() {
        this.users = new ArrayList<>();
    }

    public void addUserDtoToList(UserDto userDto){
        users.add(userDto);
    }
}
