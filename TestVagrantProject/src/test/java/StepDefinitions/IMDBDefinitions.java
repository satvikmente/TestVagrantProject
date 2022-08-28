package StepDefinitions;

import org.openqa.selenium.WebDriver;

import BeanClass.MovieDetails;
import POM.IMDB;

public class IMDBDefinitions {
	
	IMDB imdb = new IMDB();
	
	public void OpenWebsite(WebDriver driver) {
		
		imdb.OpenWebsite(driver);
	}
	
	public void SearchMovie(WebDriver driver, String MovieName) throws POM.IMDB.MyException, InterruptedException {
		
		imdb.SearchMovie(driver, MovieName);
		
	}
	
	public MovieDetails GetMovieDetails(WebDriver driver) {
		
		MovieDetails movieone = new MovieDetails();
		
		movieone.setCountry(imdb.GetMovieCountry(driver));
		
		movieone.setReleaseDate(imdb.GetMovieReleaseDate(driver));
		
		return movieone;
	}

	
//	@SuppressWarnings("serial")
//	class MyException extends Exception {
//		public MyException(String s) {
//			// Call constructor of parent Exception
//			super(s);
//		}
//	}
}
