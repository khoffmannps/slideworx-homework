package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {

	private final WebDriver driver;
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://allegro.pl");
	}
	
	@FindBy(css = "[data-role=search-input")
	private WebElement searchBox;
	
	@FindBy(css = "[data-role=search-button]")
	private WebElement searchButton;
	
	@FindBy(css = "button[data-role=accept-consent")
	private WebElement rodoCloseButton;
	
	public MainPage enterSearchQuery(String query) {
		searchBox.click();
		searchBox.sendKeys(query);
		return this;
	}
	public ResultsPage clickSearchButton() {
		searchButton.click();
		return PageFactory.initElements(driver, ResultsPage.class);
	}
	
	public MainPage acceptRodo() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(rodoCloseButton));
		rodoCloseButton.click();
		return this;
	}
	public ResultsPage clickEnterInSearchbox() {
		searchBox.sendKeys(Keys.ENTER);
		return PageFactory.initElements(driver, ResultsPage.class);
	}
}

