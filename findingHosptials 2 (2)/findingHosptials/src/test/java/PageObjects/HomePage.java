package PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	
	//locating inputbox of location
	@FindBy(xpath="//*[@id=\"c-omni-container\"]/div/div[1]/div/input")
	WebElement locationInputbox;
	
	//locating searchbar 
	@FindBy(xpath="//*[@id=\"c-omni-container\"]/div/div[2]/div[1]/input")
	WebElement searchBar;
	
	//locating suggestions
	@FindBy(xpath="//*[@id=\"c-omni-container\"]/div/div[2]/div[2]/div[1]/div[1]")
	WebElement dentist;
	
	//locting suggestion
	@FindBy(xpath="//*[@id=\"c-omni-container\"]/div/div[1]/div[2]/div[2]/div[1]")
	WebElement location;
	
	
	//action methods for entering location
	public void enterLocation(String location) {
		locationInputbox.clear();
		locationInputbox.sendKeys(location);
	}
	
	//action method for entering doctor specialist in search box
	public void enterSpecialist(String speciality) {
		searchBar.sendKeys(speciality);
	}

	//action method to click enter
	public void enterClick() {
		dentist.click();
	}
	
	//action method to click location
	public void locClick() {
		location.click();
	}

}
