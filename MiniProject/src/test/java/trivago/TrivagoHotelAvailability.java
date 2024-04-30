package trivago;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TrivagoHotelAvailability
{
	public static List<String>hotelprices=new ArrayList<String>();
	public static List<String> hotelnames=new ArrayList<String>();
	public static WebDriver driver ;
	public static WebDriver setDriver()
	{
		driver = driverSetup.getWebDriver();
		return driver;
	}
	public static void openUrl() throws InterruptedException
	{
		driver.get("https://www.trivago.in/"); //open the given website
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	public static void handleCookie()
	{
		//deleting the cookies
		driver.manage().deleteAllCookies();
	}
	public static void sendDestination() throws InterruptedException, IOException
	{
		//explicit wait
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		//send mumbai as destination through excel
		driver.findElement(By.xpath("//input[@id=\'input-auto-complete\']")).sendKeys(excelUtils.excel(0,0));
		WebElement suggestionlist=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"suggestion-list\"]/ul/li")));
		Thread.sleep(500);
	    suggestionlist.click();
		
	}
	public  static void checkInMonth() throws InterruptedException 
	{
		WebElement checkin=driver.findElement(By.xpath("//button[@data-testid=\"search-form-calendar-checkin\"]"));
		//select a nextweek date based on today date
		LocalDate checkindate = LocalDate.now().plusWeeks(1);
		//select the checkout date one day prior to checkindate
		LocalDate checkoutdate = checkindate.plusDays(1);
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM yyyy");
		String monthYear = checkindate.format(dateFormat);
		int nextWeekDay = checkindate.getDayOfMonth();
		String date1 = Integer.toString(nextWeekDay);
		int checkoutdates =checkoutdate.getDayOfMonth();
		String date2 = Integer.toString(checkoutdates);
		while(true)
		{
		//Storing the value of month and year
		WebElement checkInMonth=driver.findElement(By.xpath("(//div[@class='text-center']/h3)[1]"));
		String checkIn=checkInMonth.getText();
		
		//check whether the located month element matches to the given month and year
		if(checkIn.equals(monthYear))
		{
			System.out.println("Month is: "+checkIn);
			break;
		}
		//navigate to the next months until the expected month reaches
		driver.findElement(By.xpath("//button[@data-testid=\"calendar-button-next\"]")).click();
		}
	    try
	    {
		List<WebElement> dates = driver.findElements(By.xpath("//div[@class='grid grid-cols-2 gap-5']//div[1]//div[2]/button/time"));
		for (WebElement date : dates)
		{
			
			if (date.getText().equals(date1))
			{
				System.out.println("The Check-in date is : "+date.getText());
				date.click();
				System.out.println("The Check-out date is : "+date2);
				break;
			}
		}
	    }
		catch(Exception e)
		{
			List<WebElement> dates = driver.findElements(By.xpath("//div[@class='grid grid-cols-2 gap-5']//div[1]//div[2]/button/time"));
			for (WebElement date : dates)
			{
				
				if (date.getText().equals(date1))
				{
					System.out.println("The Check-in date is : "+date.getText());
					date.click();
					System.out.println("The Check-out date is : "+date2);
					break;
				}
			}
			
		}
	}
	public static void adultRoomSearch() throws InterruptedException
	{
	//click on a room textbox
	driver.findElement(By.xpath("//button[@class='group w-full text-left truncate h-15 px-1 bg-white active:bg-grey-200']")).click();
	Thread.sleep(300);
	//select an adult as 1
	driver.findElement(By.xpath("//button[@data-testid=\'adults-amount-minus-button\']")).click();
	//And then click on apply
	driver.findElement(By.xpath("//button[text()='Apply']")).click();
	}
	public static void search() 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			//click on search to find hotels
			driver.findElement(By.xpath("//button[contains(@class,'SearchButton')]")).click();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void sortByRating() throws InterruptedException
	{
		
		try
		{
			Thread.sleep(3000);
			WebElement sortBy = driver.findElement(By.xpath("//select[@id='sorting-selector']"));
			Select dropdown=new Select(sortBy);
			//select the rating only statement from the displayed text
			dropdown.selectByVisibleText("Rating only");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		}
		catch(Exception e)
		{
			WebElement sortBy = driver.findElement(By.id("sorting-selector"));
			Select dropdown=new Select(sortBy);
			dropdown.selectByVisibleText("Rating only");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		}
	}
	public static void priceRating() throws InterruptedException
	{
		try
		{
			List<String> ratings=new ArrayList<String>();//to store the ratings of each hotel
			//locate the last pagination number
			WebElement nextpage=driver.findElement(By.xpath("//nav[@class='self-center']//li[5]//button"));
			//get the text of a last pagination
			//convert string to integer
			int lastpage=Integer.parseInt(nextpage.getText());
			for(int j=1;j<=lastpage;j++)
			{
				Thread.sleep(5000);
				List<WebElement> prices=driver.findElements(By.xpath("//p[@data-testid='recommended-price']"));
				List<WebElement> rates = driver.findElements(By.xpath("//span[@itemprop='ratingValue']"));
				List<WebElement> namesofahotel = driver.findElements(By.xpath("//button[contains(@class,'text-left w-full truncate font-bold')]"));
				for(WebElement names : namesofahotel)
				{
					hotelnames.add(names.getText());
				}
				for(WebElement priceOfHotel: prices) 
				{
					hotelprices.add(priceOfHotel.getText());
				}
				for(WebElement ratingsfound:rates)
				{
					ratings.add(ratingsfound.getText().trim());
				}
				if(j==lastpage)
				{
					break;
				}
				else
				{
				 //next page iteration
					if(driver.findElement(By.xpath("//button[@data-testid=\"next-result-page\"]")).isDisplayed());
					{
					 driver.findElement(By.xpath("//button[@data-testid=\"next-result-page\"]")).click();
					 //Thread.sleep(1000);
					}
				}
		}		 
		//to display the hotel prices
		System.out.println("Names of all the hotels upto last page : "+hotelnames);
		System.out.println("Prices of all the hotels upto last page : "+hotelprices);
		boolean alldesc=true;
		for(int i=0;i<ratings.size()-1;i++)
		{
			Double currentRating = Double.parseDouble(ratings.get(i));
			Double nextRating = Double.parseDouble(ratings.get(i+1));
			if(currentRating < nextRating)
			{
				alldesc=false;
				break;
			}
		}
		
		if(alldesc)
		{
			System.out.println("Ratings are in descending order");
		}
		else
		{
			System.out.println("The ratings are not in descending order ");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void firstFiveHotels() throws InterruptedException
	{
		Thread.sleep(5000);
		for(int i=1;i<=5;i++)
		{
			WebElement hotelMumbai=driver.findElement(By.xpath("(//button[@data-testid='distance-label-section'])["+i+"]"));
			hotelMumbai.click();
			//check whether first five hotels belongs to mumbai or not
			
			String cityname = driver.findElement(By.xpath("//div[@itemprop=\'address\']//span[@itemprop=\'addressLocaspanty\']")).getText();
			if(cityname.contains("Mumbai"))
			{
				System.out.println("Hotel " +i+ " is in " + "Mumbai");
			}
			
			
		}
	}
	public static void closeBrowser()
	{
		driver.quit();
	}
	
			
			
public static void main(String[] args) throws Throwable
{
	setDriver();
	openUrl();
	screenshot.TakeScreenshot(driver, "loginpage");
	handleCookie();
	sendDestination();
	screenshot.TakeScreenshot(driver, "SelectedCity");
	checkInMonth();
	adultRoomSearch();
	search();
	screenshot.TakeScreenshot(driver, "search");
	sortByRating();
	firstFiveHotels();
	screenshot.TakeScreenshot(driver, "Displayedhotels");
	priceRating();
	screenshot.TakeScreenshot(driver, "prices");
	excelUtils.Output(hotelnames,hotelprices);
	closeBrowser();
	
}
}



