package PageObjects;

import java.io.IOException;
import java.util.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utilitis.ExcelUtilis;

public class beCognizant extends basePage {
	
	WebDriver driver;
	
	public beCognizant(WebDriver driver) 
	{
		super(driver);
	
	}
	//ExcelUtilis eu = new ExcelUtilis();

	@FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
	WebElement userprofile;
	
	@FindBy(id="mectrl_currentAccount_primary")
	WebElement name;
	
	@FindBy(id="mectrl_currentAccount_secondary")
	WebElement emailId;
	
	@FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
	WebElement profileXpathClose;
	
	@FindBy(id="mectrl_headerPicture")
	WebElement profileIdClose;
	
	@FindBy(xpath="//div[@title='OneCognizant']")
	WebElement oneCognizant;
	
	

	
	public void clickProfile()
	{
		userprofile.click();
		
	}
	
	public void getProfile() throws IOException
	{
		
		String Name = name.getText();
		String Email = emailId.getText();
		System.out.println("--------------- Personal Info ---------------");
		System.out.println("Name     : "+Name+"\nMail Id  : "+Email);
		System.out.println("---------------------------------------------");
		System.out.println("");
		ExcelUtilis.write("Profile Info", 0, 0, Name);
		ExcelUtilis.write("Profile Info", 0, 1, Email);
		
	}
	
	public void closeProfile()
	{
		
		try
		{
			profileXpathClose.click();
		}
		catch(Exception e)
		{
			profileIdClose.click();
		}
		
	}
	
	public void openOneCognizant(WebDriver driver) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//WebElement flag=driver.findElement(By.xpath("//div[@title='OneCognizant']"));
		js.executeScript("arguments[0].scrollIntoView();",oneCognizant);
		Thread.sleep(4000);
		oneCognizant.click();
		
	}
	
	
	public void windowHandlesOneCog(WebDriver driver) throws InterruptedException
	{
		
		Thread.sleep(10000);
		Set <String> Window = driver.getWindowHandles();
	    List <String> Window1 = new ArrayList<String>(Window); 
	    //System.out.println("One Cognizant's Window Handle - "+ Window1.get(1));
	    driver.switchTo().window(Window1.get(1));
	    
	}

	

}