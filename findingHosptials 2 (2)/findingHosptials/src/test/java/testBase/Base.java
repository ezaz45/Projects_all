package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j


public class Base {
	
	public WebDriver driver;
	public Properties p;
	public static String targetFilePath;
	public Logger logger;
	@BeforeClass(groups= {"smoke","regression"})
	@Parameters({"os","browser"})
	  public void setup(String os,String br) throws IOException {
		if(getProperties().getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os.....");
				return;
			}
			
			switch(br.toLowerCase()) {
			case "chrome":cap.setBrowserName("chrome");
						break;
			case "edge":cap.setBrowserName("MicrosoftEdge");
						break;
			default:System.out.println("No matching browser....");
						return;
			
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			
		}
		else if(getProperties().getProperty("execution_env").equals("local")) {
			switch(br) 
			{
			case "chrome": driver=new ChromeDriver();
			                break;
			case "edge": driver=new EdgeDriver();
			                 break;
			case "firefox":driver=new FirefoxDriver();
			                 break;
			}
		}
		
		
			
				
			
			logger=LogManager.getLogger(this.getClass());
	       	driver.get(getProperties().getProperty("appUrl"));
	    	driver.manage().window().maximize();
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	    }
		
		
		
		@AfterClass(groups= {"smoke","regression"})
		public void tearDown() {
	    	driver.quit();
	    }
		
		public Properties getProperties() throws IOException {
			FileReader file=new FileReader(".//src//test//resources//config.properties");
			p=new Properties();
			p.load(file);
			return p;
		}
		
		
		public String[] convert_String_to_Array(String s) {
			String[] arr=s.split("\n");
			
			return arr;
		}



		public void captureScreen(String name) throws IOException {
			
			
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + name +  ".png";
			File targetFile=new File(targetFilePath);
			
			FileUtils.copyFile(sourceFile, targetFile);
		
			
		}
		
		public void scrollDown(WebElement ele) {
			
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[1].scrollIntoView();", ele,ele);
			
		} 
		
	
     public void refresh() {
    	 driver.navigate().refresh();
     }
     
}
