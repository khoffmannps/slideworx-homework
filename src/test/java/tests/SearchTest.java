package tests;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.*;
import utils.Runner;

import java.util.concurrent.TimeUnit;
public class SearchTest {
	
	public WebDriver driver;
		
@BeforeTest
	public void setUp() {
	driver = Runner.startDriver();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
}
@AfterTest
public void tearDown() {
	driver.quit();
}

@Test 
public void testSearch() {
	MainPage page = PageFactory.initElements(driver, MainPage.class);
	page.acceptRodo();
	page.enterSearchQuery("rower");
	ResultsPage resultspage = page.clickSearchButton();
	
	int priceFirst = resultspage.getArticlePrice(0);
	int auctionsFound = resultspage.getResultsNumber();
	
	System.out.println("Cena pierwszego produktu w groszach:");
	System.out.println(priceFirst);
	System.out.println("Liczba znalezionych aukcji:");
	System.out.println(auctionsFound);
	
	Assert.assertTrue(priceFirst > auctionsFound, "Wartość przedmiotu w groszach nie jest większa od liczby aukcji!");
}
}
