package com.softserve.edu.comics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LoginDto {

    private String login;
    private String password;

}
