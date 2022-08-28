package POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestCases.AppTest;

public class Wikipedia {

	private String WikipediaUrl = "https://www.wikipedia.org/";
	WebDriverWait wait;

	public void OpenWebsite(WebDriver driver) {

		driver.get(WikipediaUrl);

	}

	public void SearchMovie(WebDriver driver, String MovieName) throws MyException, InterruptedException {

		WebDriverWait wait = AppTest.wait;
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("searchInput")));
		driver.findElement(By.id("searchInput")).sendKeys(MovieName);
		driver.findElement(By.xpath("//*[text() = 'Search']")).click();

		Thread.sleep(1000);

		try {
			
			driver.findElement(By.id("firstHeading"));
		
			driver.findElement(By.xpath("//li[@class = 'mw-search-result']/div/a")).click();
			
		}catch (NoSuchElementException e) {
			// TODO: handle exception
//			throw new MyException("Something went wrong in wiki search");
			
		}

	}
	
	public List<WebElement> GetMovieDetails(WebDriver driver, String MovieName) {
		
		
		 return driver.findElements(By.xpath("//*[@class = 'infobox vevent']/tbody/tr"));
		
	
	}

	@SuppressWarnings("serial")
	public class MyException extends Exception {
		public MyException(String s) {
			// Call constructor of parent Exception
			super(s);
		}
	}
}
