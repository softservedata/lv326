package com.mazurak.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class MovieDto {

	private Long movieId;
	private String filmName;
	private String description;
	private String ageLimit;
	private Integer year;
	private Long userId;

}
