package com.softserve.edu.comics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ComicsDto {

    private Long idComics;
    private String title;
    private String author;
    private String publishingOffice;
    private String description;
    private Long idUser;

}
