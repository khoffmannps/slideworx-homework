package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.*;
import utils.Runner;
public class SortTest {

	public WebDriver driver;
	private MainPage page;
	
	@BeforeMethod
	public void setUp() {
	Runner.loadProperties();
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
public void homeworkTask4(){
	page.enterSearchQuery("rower");
	ResultsPage resultspage = page.clickSearchButton();

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

/*Asercja sprawdzająca URL nie jest tutaj optymalna, test powinien sprawdzać czy posortowana lista się załadowała.
Niestety, sortowanie na stronie Allegro wciąż nie działa i nie podjąłem się pisania metody sprawdzającej na ślepo */
@Test
public void checkAllSortingTypesWork() {
	ResultsPage resultspage = page.enterSearchQuery("rowerek").clickSearchButton();
	SoftAssert assertion = new SoftAssert();
	
	resultspage.sortResults("p");
	assertion.assertTrue(driver.getCurrentUrl().contains("order=p"), "URL strony nie zmienił się na\"cena od najniższej\"");
	
	resultspage.sortResults("pd");
	assertion.assertTrue(driver.getCurrentUrl().contains("order=pd"), "URL strony nie zmienił się na\"cena od najwyższej\"");
	
	resultspage.sortResults("d");
	assertion.assertTrue(driver.getCurrentUrl().contains("order=d"), "URL strony nie zmienił się na\"cena z dostawą od najniższej\"");

	resultspage.sortResults("dd");
	assertion.assertTrue(driver.getCurrentUrl().contains("order=dd"), "URL strony nie zmienił się na\"cena z dostawą od najwyższej\"");

	resultspage.sortResults("qd");
	assertion.assertTrue(driver.getCurrentUrl().contains("order=qd"), "URL strony nie zmienił się na\"najpopularniejsze\"");

	resultspage.sortResults("t");
	assertion.assertTrue(driver.getCurrentUrl().contains("order=t"), "URL strony nie zmienił się na\"czas do końca aukcji\"");

	resultspage.sortResults("n");
	assertion.assertTrue(driver.getCurrentUrl().contains("order=n"), "URL strony nie zmienił się na\"najnowsze aukcje\"");

	resultspage.sortResults("m");
	assertion.assertTrue(driver.getCurrentUrl().contains("order=m"), "URL strony nie zmienił się na\"trafność\"");

	assertion.assertAll();
}

}