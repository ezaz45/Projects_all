package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.SurgeriesPage;
import PageObjects.healthWillnessForm;
import PageObjects.specialistPage;
import testBase.Base;
import utilites.ExcelUtility;

public class TC_05 extends Base {
	public HomePage p;
	public specialistPage sp;
	public SurgeriesPage surgeries;
	public healthWillnessForm hf;
	public SurgeriesPage spage;
	
	@Test (groups= {"smoke","regression"})
	public void endToEnd() throws IOException, InterruptedException {
		p=new HomePage(driver);
		sp=new specialistPage(driver);
		spage=new SurgeriesPage(driver);
		hf=new healthWillnessForm(driver);
		logger.info("****Starting Testcase ten extracting doctor detials****");
		p.enterLocation(getProperties().getProperty("location"));
		Thread.sleep(2000);		
		p.locClick();
		
		p.enterSpecialist(getProperties().getProperty("specialist"));
		Thread.sleep(2000);
		p.enterClick();
		
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
		
		sp.printDetials();
		spage.surgries.click();
		spage.printSurgiers();
		
		spage.openForm();
		scrollDown(hf.scroll);
		hf.formDetials(ExcelUtility.getData(System.getProperty("user.dir")+"\\testData\\testInputData.xlsx", "Sheet1",6 , 0));
	    boolean status=hf.submitButtonStatus();
	    if(!status) {
	    	logger.info("****submit button disabled****");
	    	logger.info("***TestCase three excuted successfully***");
	    }
	    captureScreen("invalidDetials");
	   Assert.assertEquals(status, false);
	   refresh();

		scrollDown(hf.scroll);
		hf.formDetials(ExcelUtility.getData(System.getProperty("user.dir")+"\\testData\\testInputData.xlsx", "Sheet1",6 , 1));
	    boolean status1=hf.submitButtonStatus();
	    if(status1) {
	    	hf.clickSubmit();
	    	logger.info("****submit button enabled****");
	    	
	    }
	    Assert.assertEquals(hf.getMsg(), getProperties().getProperty("msg"));
	    captureScreen("thankyouMsg");
	   
		
		
	}

}
