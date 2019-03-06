package pageobjects;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Runner;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.Arrays;

public class ResultsPage {
	
private final WebDriver driver;
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
	
	}
	@FindBy (css = ".listing-title__counter-value")
	private WebElement resultsNumber;
	
	@FindBy (css = "div.opbox-listing-sort div div select" )
	private WebElement sortResults;
	
	@FindBy (xpath ="//article[@data-analytics-view-custom-index0]")
	private List<WebElement> articleList;
	
	@FindBy (xpath ="//*[contains(text(), 'Lista Ofert')]")
	private WebElement regularOffersHeader;
	
	
	/* Available Sorts:
	 * "m" - most accurate, default sort
	 * "p" - price:lowest
	 * "pd" - price:highest
	 * "d" - price with delivery:lowest
	 * "dd" - price with delivery:highest
	 * "qd" - popularity:highest
	 * "t" - least time till auction's end
	 * "n" - newest auctions
	 */
	public ResultsPage sortResults(String value) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(sortResults));
		Select sortSelect = new Select (sortResults);
		sortSelect.selectByValue(value);
		return this;
	}
	
	public int countArticles() {
		return articleList.size();
	}
	
	public int getArticlePrice(int articleindex) {
		String articlelocator = "data-analytics-view-custom-index0='";
		String index = Integer.toString(articleindex);
		String xPathStart = "//article[@";
		String xPathEnd = "']/div/div/div[2]/div[2]/div[1]/div/span/span";
		String xPathZlote = xPathStart.concat(articlelocator).concat(index).concat(xPathEnd);

		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathZlote)));
		
		WebElement priceZlote = driver.findElement(By.xpath(xPathZlote));
		return Runner.numbersFromString(priceZlote.getText());
		
	}
	
	public int getLowestPriceOnPage() {
		int articlesOnPage = countArticles();
		int articles [] = new int[articlesOnPage];
		for (int i = 0; i < articlesOnPage;i++) {
			articles[i] = getArticlePrice(i);
		}
		return Arrays.stream(articles).min().getAsInt();
	}
	
	public int getHighestPriceOnPage() {
		int articlesOnPage = countArticles();
		int articles [] = new int[articlesOnPage];
		for (int i = 0; i < articlesOnPage;i++) {
			articles[i] = getArticlePrice(i);
		}
		return Arrays.stream(articles).max().getAsInt();
	}
	
	public int sumArticlePrices (int[] articles) {
		int sumofprices = 0;
		for(int i = 0; i <articles.length;i++ ) {
			sumofprices = sumofprices + getArticlePrice (articles[i]);
		}
		
		return sumofprices;
	}

	
	public int getResultsNumber() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(resultsNumber));		
		return Runner.numbersFromString(resultsNumber.getText());
		
	}
}
