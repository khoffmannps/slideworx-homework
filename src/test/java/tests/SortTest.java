package tests;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.*;
import utils.Runner;
public class SortTest {

	public WebDriver driver;
	
	@BeforeTest
	public void setUp() {
	Runner.loadProperties();
	driver = Runner.startDriver();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
}
@AfterTest
public void tearDown() {
	driver.quit();
	}

@Test ()
public void homeworkTask4(){
	MainPage page = PageFactory.initElements(driver, MainPage.class);
	page.acceptRodo();
	page.enterSearchQuery("rower");
	ResultsPage resultspage = page.clickSearchButton();
	//resultspage.sortResults("pd");
	
	
	boolean testpassed = true;

	if(resultspage.getArticlePrice(0) < 50000){
		int[] resultstosum = {0,1,2,3,4};
		System.out.println("Suma wartości pierwszych 5 artykułów (w groszach) to:");
		System.out.println(resultspage.sumArticlePrices(resultstosum));
	} else if (resultspage.getArticlePrice(0)> 72100) {
		int difference = resultspage.getHighestPriceOnPage() - resultspage.getLowestPriceOnPage();
		System.out.println("Różnica pomiędzy ceną najdroższego i najtańszego artykułu to:");
		System.out.println(difference);
	}else {
		testpassed = false;
	}
	
	Assert.assertTrue(testpassed, "Wartość pierwszego przedmiotu poza oczekiwanym przedziałem!");

}
}