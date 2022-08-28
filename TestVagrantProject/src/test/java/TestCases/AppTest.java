package TestCases;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import BeanClass.MovieDetails;
import StepDefinitions.IMDBDefinitions;
import StepDefinitions.WikipediaDefnitions;

public class AppTest {

	private String MovieName = "Pushpa: The Rise";
	String driverPath = "C://Users//lopam\\//Documents//automation//chromedriver//chromedriver.exe";
	public WebDriver driver;
	public static WebDriverWait wait;

	@BeforeTest  
	public void beforeTest() {

		// set the system property for Chrome driver
		System.setProperty("webdriver.chrome.driver", driverPath);

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setJavascriptEnabled(true);
		// Create driver object for CHROME browser

//		ChromeOptions op = new ChromeOptions();
//		op.addArguments("--headless");
		
		driver = new ChromeDriver(cap);
	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);  
		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, 5);

	}

	@Test
	public void CompareData() {

		IMDBDefinitions imdb = new IMDBDefinitions();
		imdb.OpenWebsite(driver);
		try {
			imdb.SearchMovie(driver, MovieName);
			MovieDetails IMDB = imdb.GetMovieDetails(driver);
			
//			System.out.println(IMDB.getReleaseDate());

			WikipediaDefnitions wikipidea = new WikipediaDefnitions();
			wikipidea.OpenWebsite(driver);
			wikipidea.SearchMovie(driver, MovieName);

			MovieDetails Wiki = wikipidea.GetMovieDetails(driver, MovieName);
//			System.out.println(Wiki.getReleaseDate());

			Boolean DateAssert = IMDB.getReleaseDate().contentEquals(Wiki.getReleaseDate());
			Boolean CountryAssert = IMDB.getCountry().contentEquals(Wiki.getCountry());

//			System.out.println(DateAssert + "  "+ CountryAssert );
			assertTrue(DateAssert && CountryAssert);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			assertTrue(false);
		}

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}