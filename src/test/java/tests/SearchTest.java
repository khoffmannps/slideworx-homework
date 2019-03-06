package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.*;
import utils.Runner;

import java.util.concurrent.TimeUnit;
public class SearchTest {
	
	public WebDriver driver;
	private MainPage page;
		
@BeforeMethod
	public void setUp() {
	driver = Runner.startDriver();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	page =  PageFactory.initElements(driver, MainPage.class);
	page.acceptRodo();
}
@AfterMethod
public void tearDown() {
	driver.quit();
}

@Test
public void homeworkTask2() {
	ResultsPage resultspage = page.enterSearchQuery("rower").clickSearchButton();
	
	int priceFirst = resultspage.getArticlePrice(0);
	int auctionsFound = resultspage.getResultsNumber();
	
	System.out.println("Cena pierwszego produktu w groszach:");
	System.out.println(priceFirst);
	System.out.println("Liczba znalezionych aukcji:");
	System.out.println(auctionsFound);
	
	Assert.assertTrue(priceFirst > auctionsFound, "Wartość przedmiotu w groszach nie jest większa od liczby aukcji!");
}

/*Sprawdzam jedynie działanie przycisku Enter w searchboksie,
przycisk szukaj sprawdzany jest przy okazji innych testów
moim zdaniem tak jest lepiej z perspektywy wydajności*/
@Test
public void checkEnterWorksInSearchbox() {
	page.enterSearchQuery("test").clickEnterInSearchbox();
	Assert.assertTrue(driver.getCurrentUrl().contains("https://allegro.pl/listing"), "Listing się nie otworzył po kliknięciu enter!");
	
}

}
