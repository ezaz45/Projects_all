package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.SurgeriesPage;
import testBase.Base;

public class TC_02 extends Base{

	public SurgeriesPage sp;
	@Test(priority=3,groups={"smoke"})
	void checkSurgiersPageOpened() throws IOException {
		sp=new SurgeriesPage(driver);
		sp.surgiersClick();
		String title=driver.getTitle();
		Assert.assertEquals(title, getProperties().getProperty("surgiersTitle"));
		captureScreen("SurgiersPage");
	
		logger.info("**Surgies page opended Successfully**");
	}
	
	@Test(priority=4,groups={"Regression"},dependsOnMethods= {"checkSurgiersPageOpened"})
	void printSurgeries() throws IOException {
		
		
		
		
		logger.info("****Surgieres list printed****");
		sp.printSurgiers();
		scrollDown(sp.popularSurgiers);
		captureScreen("surgiersList");
		
		
	}
	
}
