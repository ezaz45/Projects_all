package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import PageObjects.HomePage;
import PageObjects.SurgeriesPage;
import PageObjects.healthWillnessForm;
import PageObjects.specialistPage;
import testBase.Base;
import utilites.ExcelUtility;

public class TC_01 extends Base{
	public HomePage p;
	public specialistPage sp;
	public SurgeriesPage surgeries;
	public healthWillnessForm hf;
	public List<String> doctorDetialsString;
	
	
	@Test (priority=1,groups={"smoke"})
	void checkUrl() throws IOException {
		String title=driver.getTitle();
		Assert.assertEquals(title, getProperties().getProperty("title"));
		captureScreen("HomePage");
		
		logger.info("**Practo Url opended Successfully**");
	}
	
	@Test(groups= {"smoke"},dependsOnMethods="checkUrl")
	void checkLocations() throws InterruptedException, IOException {
		p=new HomePage(driver);
		sp=new specialistPage(driver);
		
		p.enterLocation(getProperties().getProperty("location"));
		Thread.sleep(2000);		
		p.locClick();
		
		p.enterSpecialist(getProperties().getProperty("specialist"));
		Thread.sleep(2000);
		p.enterClick();
		logger.info("****checking the selected location ****");
		if(sp.getSelectedLocation().contains(getProperties().getProperty("location"))){
			Assert.assertEquals(true, true);
		}
		
		
		
	}
	
	@Test(groups= {"smoke"},dependsOnMethods="checkUrl")
	void checkSpecialist() throws IOException {
		String specialist=sp.getSelectedSpecialist();
		logger.info("checking the selected specialist");
		if(specialist.contains(getProperties().getProperty("specialist"))) {
			Assert.assertEquals(true, true);
		}
		captureScreen("HomePage");
	}
	
	@Test (priority=2,groups="Regression",dependsOnMethods={"checkLocations","checkSpecialist"})
	void checkDoctorDetials() throws IOException, InterruptedException {
		
		logger.info("giving the options in dropdowns");
		sp.dropDownclick(sp.patientStoriesDropdownClick);
		sp.selectDropdown(sp.patientStories);
		Thread.sleep(2000);
		
		sp.dropDownclick(sp.experienceDropdownClick);
		sp.selectDropdown(sp.experience);
		Thread.sleep(2000);
		
		sp.dropDownclick(sp.allFiltersClick);
		sp.selectDropdown(sp.feeDropdown);
		Thread.sleep(2000);
		
		sp.dropDownclick(sp.allFiltersClick);
		sp.selectDropdown(sp.availability);
		Thread.sleep(4000);
		
		sp.dropDownclick(sp.sortByClick);
		sp.selectDropdown(sp.sortBy);		
		
		captureScreen("doctorDetials");
		doctorDetialsString=sp.getString(sp.doctorDetials);
		int listCount=1;
		for(String s:doctorDetialsString) {
			String[] data=convert_String_to_Array(s);
			
				
			ExcelUtility.setData(System.getProperty("user.dir")+"\\testData\\testOutput.xlsx","Sheet1",listCount,data.length,data);
			
			
			
			
			listCount++;
			
		}
		logger.info("First five doctor detials entered succesfully in Excel");
		
		
		
		
		
		
		
	}

}
