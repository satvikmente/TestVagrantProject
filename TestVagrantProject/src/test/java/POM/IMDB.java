package POM;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestCases.AppTest;

public class IMDB {

	private String IMDBUrl = "https://www.imdb.com/";
	WebDriverWait wait;

	public void OpenWebsite(WebDriver driver) {

		driver.get(IMDBUrl);

	}

	public void SearchMovie(WebDriver driver, String MovieName) throws MyException, InterruptedException {

		WebDriverWait wait = AppTest.wait;

		// Search Movie name in search bar and click search
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("suggestion-search")));
		driver.findElement(By.id("suggestion-search")).sendKeys(MovieName);
		driver.findElement(By.id("suggestion-search-button")).click();

		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'" + MovieName + "')]")));
			driver.findElement(By.xpath("//a[contains(text(),'" + MovieName + "')]")).click();
			Thread.sleep(1000);
		} catch (TimeoutException e) {
			// TODO: handle exception
			 throw new MyException(MovieName+" Movie doesn't exists");
		}

	}

	public String GetMovieCountry(WebDriver driver) {

		WebDriverWait wait = AppTest.wait;

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid = 'title-details-origin']/div")));

		String data = driver.findElement(By.xpath("//*[@data-testid = 'title-details-origin']/div")).getText();
//		System.out.println("date imdb -"+data);
		return data;

	}
	
	public String GetMovieReleaseDate(WebDriver driver) {
		
	
		String data = driver.findElement(By.xpath("//*[@data-testid = 'title-details-releasedate']/div")).getText();
		if (data.contains("India")) {
			
			String[] s = data.split("\\s+");
			
			 data = s[1].replace(",", "")+" "+s[0]+" "+s[2];
			return data;
		}
		else {

			driver.findElement(
					By.xpath("//*[@data-testid = 'title-details-releasedate']//*[@id= 'iconContext-chevron-right']"))
			.click();

			List<WebElement> TableData = driver
					.findElements(By.xpath("//tr[@class = 'ipl-zebra-list__item release-date-item']"));

			for (Iterator<WebElement> iterator = TableData.iterator(); iterator.hasNext();) {
				WebElement webElement = (WebElement) iterator.next();
				data = webElement.getText();
				if (data.contains("India")) {
					return data.replace("India ", "");
	
				}
			}
		}
		return data;

		
	}

	@SuppressWarnings("serial")
	public class MyException extends Exception {
		public MyException(String s) {
			// Call constructor of parent Exception
			super(s);
		}
	}
}
