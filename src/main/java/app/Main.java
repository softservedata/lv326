package app;

import com.mazurak.cinema.dao.MovieDao;
import com.mazurak.cinema.service.UserMovieService;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start...");
		
		MovieDao movieDao = new MovieDao();
		UserMovieService userMovieService = new UserMovieService();
		System.out.println(userMovieService.searchMovie(""));
		System.out.println("Finish...");
	}

}
