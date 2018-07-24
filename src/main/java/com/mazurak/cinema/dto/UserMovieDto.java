package com.mazurak.cinema.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserMovieDto {

	private String userLogin;
	private List<MovieDto> movies;

	public UserMovieDto() {
//		this.userLogin = userLogin;
		this.movies = new ArrayList<MovieDto>();
	}

	public UserMovieDto(String userLogin) {
		this.userLogin = userLogin;
		this.movies = new ArrayList<MovieDto>();
	}

	public void addMovieDto(MovieDto movieDto) {
		movies.add(movieDto);
	}
}
