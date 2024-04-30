package PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class oneCognizant extends basePage {

	public oneCognizant(WebDriver driver)
	{
		super(driver);
		
	}
	
	@FindBy(xpath="//*[@id='oneC_searchAutoComplete']")
	WebElement searchBar;
	
	@FindBy(xpath="//*[@id='newSearchQALST']//div[contains(text(),'Submit Timesheet')]")
	WebElement timeSheetIcon;
	
	@FindBy(xpath="//*[@class='searchTopBar']")
	WebElement searchEdgeIcon;
	
	@FindBy(xpath="//*[@id='oneCSearchTop']")
	WebElement searchBarEdge;

	

	public void inputSearchBar(String input) throws InterruptedException
	{
		Thread.sleep(3000);
		searchBar.sendKeys(input);
		timeSheetIcon.click();
	}
	public void inputSearchBarEdge(String input) throws InterruptedException
	{
		//searchBar.sendKeys(input);
		searchEdgeIcon.click();
		searchBarEdge.sendKeys(input);
		Thread.sleep(2000);
		timeSheetIcon.click();
	}

	
	
	public void windowHandelsTimesheet(WebDriver driver) throws InterruptedException
	{
		Set <String> Window = driver.getWindowHandles();
	    List <String> Window1 = new ArrayList<String>(Window);
	    //System.out.println("Timesheet's Window Handle - " + Window1.get(2));
		driver.switchTo().window(Window1.get(2));
		
	}
}
