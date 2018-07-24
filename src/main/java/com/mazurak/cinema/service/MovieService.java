package com.mazurak.cinema.service;

import com.mazurak.cinema.dao.MovieDao;
import com.mazurak.cinema.dto.MovieDto;
import com.mazurak.cinema.entity.Movie;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class MovieService {
	
	private MovieDao movieDao;

	public MovieService() {
		movieDao = new MovieDao();
	}

	public MovieDto getMovieDto(Long id) {
		Movie movie = movieDao.getById(id);
		return new MovieDto(movie.getId(),movie.getFilmName(), movie.getDescription(), movie.getAgeLimit(),
				movie.getYear(),movie.getUserId());
	}

	public boolean setMovieDto(MovieDto movieDto, Long userId) {
		boolean result = false;
		Movie movie = new Movie(movieDto.getMovieId(), movieDto.getFilmName(), movieDto.getDescription(),
				movieDto.getAgeLimit(), movieDto.getYear(), userId);
		if (movieDto.getMovieId() > 0) {
			if (isExistsMovie(movie.getId())) {
				movieDao.updateByEntity(movie);
				result = true;
			} else {
				movieDao.insert(movie);
				result = true;
			}
		}
		return result;
	}

	public boolean isExistsMovie(Long id) {
		boolean result = true;
		try {
			movieDao.getById(id);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public void saveMovieToDataBase(MovieDto movieDto) {
		Movie movie = new Movie(1L, 
				movieDto.getFilmName(),
				movieDto.getDescription(),
				movieDto.getAgeLimit(),
				movieDto.getYear(),
				movieDto.getUserId());
		movieDao.insert(movie);
	}

}
