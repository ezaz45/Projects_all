package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class healthWillnessForm extends BasePage{
	
	Select orgDropdown;
	Select intrstDropdown;
	public healthWillnessForm(WebDriver driver) {
		super(driver);
		
	}
	
	//locating input name Field
	@FindBy(xpath="//*[@id=\"name\"]")
	WebElement inputName;
	
	//locating organizationname Field
	@FindBy(xpath="//*[@id=\"organizationName\"]")
	WebElement organizationName;
	
	//locating contactNumber Field
	@FindBy(xpath="//*[@id=\"contactNumber\"]")
	WebElement contactNumber;
	
	//locating emailId Field
	@FindBy(xpath="//*[@id=\"officialEmailId\"]")
	WebElement emailId;
	
	//locating orginzationSize dropDown
	@FindBy(xpath="//*[@id=\"organizationSize\"]")
	WebElement orgDropDown;
	
	//locating intrestedIn dropDown
	@FindBy(xpath="//*[@id=\"interestedIn\"]")
	WebElement intrestedDropDown;
	
	//locating submit button
	@FindBy(xpath="//*[@id=\"header\"]/div[2]/div/form/button")
	WebElement submit;
	
	//locating thankyou message
	@FindBy(xpath="/html/body/div[4]/div/div/div/div[1]")
	WebElement msg;
	
	//locating element for scrolling
	@FindBy(xpath="//*[@id=\"header\"]/div[1]/div/div/div/h1")
	public WebElement scroll;
	
	
	public void createSelect() {
		 orgDropdown=new Select(orgDropDown);
		 intrstDropdown=new Select(intrestedDropDown);
	}
	
	
	
	
	
	//sending values to form
	public void formDetials(List<String> arr) {
		inputName.sendKeys(arr.get(0));
		organizationName.sendKeys(arr.get(1));
		contactNumber.sendKeys(arr.get(2));
		emailId.sendKeys(arr.get(3));
		createSelect();
		orgDropdown.selectByVisibleText(arr.get(4));
		intrstDropdown.selectByVisibleText(arr.get(5));
	}
	
	public boolean submitButtonStatus() {
		return submit.isEnabled();
	}
	
	
	public void clickSubmit() {
		submit.click();
	}
	
	public String getMsg() {
		return msg.getText();
	}
	


}
