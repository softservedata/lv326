package com.softserve.edu.comics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserDto {

    private String login;
    private String password;
    private String name;
    private String role;

}
