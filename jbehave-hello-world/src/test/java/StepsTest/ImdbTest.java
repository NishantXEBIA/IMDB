package StepsTest;

import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ImdbTest {
	
	private  static final String SEARCH_TEXT="//input[@id='navbar-query']";
	private  static final String SUBMIT_BUTTON="//button[@id='navbar-submit-button']";
	private  static final String SEARCH_RESULT="//div[@id='main']/div/div[2]/table/tbody/tr[1]/td[2]/a";
	private  static final String CREW_TEXT="//td[@id='overview-top']/div[6]/span[2]/a";
	private  static final String SPCL_THANKS="//div[@id='fullcredits_content']/table[29]/tbody/tr";
	
	WebDriver driver= new FirefoxDriver();
	WebElement element= null;
	
	@Given("That I'm on IMDB website")
	public void goToIMDBWeb() throws Exception {
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.imdb.com");
	
	}
	@Then("I enter Star Wars: Episode I in search field")
	public void enterTextInSearchField() throws Exception {
		element = driver.findElement(By.xpath(SEARCH_TEXT));
		element.sendKeys("Star Wars: Episode I");
		
	}
	
	@When("I click on Submit button")
	public void clickOnSubmit() throws Exception {
		element = driver.findElement(By.xpath(SUBMIT_BUTTON));
		element.click();
		Thread.sleep(10000);
	
	}
	
	@Then("I should see Star Wars: Episode I in search result")
	public void verifySearchResult() throws Exception {
		element = driver.findElement(By.xpath(SEARCH_RESULT));
		String searchText= element.getText();
	String textArray[] = searchText.split("-",2);
	
	Assert.assertEquals("Did not get the search item in search result", "Star Wars: Episode I", textArray[0].trim());
	}
	
	@When("I click on Star Wars: Episode I")
	public void clickOnSearchResult() throws Exception {
	element= driver.findElement(By.partialLinkText("Star Wars: Episode I -"));
	element.click();
	}
	@Then("I should Navigate to full crew member list")
	public void verifyCrewList() throws Exception {
	element= driver.findElement(By.xpath(CREW_TEXT));
	String crewLink = element.getText();
	Assert.assertEquals("Did not navigate to crew memeber list ","See full cast and crew", crewLink);
		
	}
	@When("I click on Full Crew Mwmber list")
	public void clickOnCrewList() throws Exception {
		element= driver.findElement(By.xpath(CREW_TEXT));
		element.click();
		
	}
	@Then("I should see Jim Morris Name With Special Thanks")
	public void searchForJim() throws Exception {
	element = driver.findElement(By.xpath(SPCL_THANKS));
	String JimAppr= element.getText();
	System.out.println("Specil thank for Marris" +JimAppr);
	String [] spclThanks = JimAppr.split("\\.\\.\\.");
	Assert.assertEquals("Jim Morris is not present","Jim Morris",spclThanks[0].trim());
	Assert.assertEquals("Special thanks is not attached with Jim ","special thanks",spclThanks[1].trim());
	driver.quit();
	
	
	}
	
	
}
