package StepDefinitions;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BeanClass.MovieDetails;
import POM.Wikipedia;

public class WikipediaDefnitions {
	
	Wikipedia wikipedia = new Wikipedia();
	
	public void OpenWebsite(WebDriver driver) {

		wikipedia.OpenWebsite(driver);

	}
	
	public void SearchMovie(WebDriver driver, String MovieName) throws POM.Wikipedia.MyException, InterruptedException {
		
		wikipedia.SearchMovie(driver, MovieName);
		
	}
	
	public MovieDetails GetMovieDetails(WebDriver driver, String MovieName) {
		
		MovieDetails PushpaWikipedia = new MovieDetails();
		List<WebElement> MovieData = wikipedia.GetMovieDetails(driver, MovieName);
		for (Iterator<WebElement> iterator = MovieData.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			String data = webElement.getText().replace("\n", "");
			if(data.contains("Release date")) {
				PushpaWikipedia.setReleaseDate(data.replace("Release date", ""));
			}else if(data.contains("Country"))
				PushpaWikipedia.setCountry(data.replace("Country ", ""));
		}
		
		return PushpaWikipedia;
	}

}
