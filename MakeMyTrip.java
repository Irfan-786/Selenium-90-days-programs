package selenium.ninetydays;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip {

	public static ChromeDriver driver;
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByXPath("//li[contains(@class,'menu_Hotels')]").click();
		driver.findElementByXPath("(//input[contains(@type,'text')])[1]").click();
		driver.findElementByXPath("//input[contains(@placeholder,'Enter city/ Hotel/ Area/ Building')]").sendKeys("Goa");
		Thread.sleep(4000);
		driver.findElementByXPath("(//div[contains(@class,'flexOne')])[5]").click();
		driver.findElementByXPath("(//div[text()='15'])[2]").click();
		driver.findElementByXPath("(//div[text()='20'])[2]").click();
		driver.findElementByXPath("//label[contains(@for,'guest')]").click();
		driver.findElementByXPath("(//li[text()='2'])[1]").click();
		driver.findElementByXPath("(//li[text()='1'])[2]").click();
		//driver.findElementByClassName("ageSelectBox").click();
		WebElement eleDrop = driver.findElementById("0");
		Select dropDown = new Select(eleDrop);
		dropDown.selectByIndex(11);

		driver.findElementByXPath("//button[text()='APPLY']").click();
		driver.findElementByXPath("//button[text()='Search']").click();
		Thread.sleep(3000);
		
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		driver.findElementByXPath("//label[text()='Baga']").click();
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		WebElement element = driver.findElementByXPath("//label[text()='5 Star']");
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		
		
		driver.findElementByXPath("//span[text()='The Park Baga River Goa']").click();
		
		Set<String> winSet = driver.getWindowHandles();
		List<String> WinLis = new ArrayList<String>(winSet);
		driver.switchTo().window(WinLis.get(1));
		Thread.sleep(3000);
		
		String htlName = driver.findElementByXPath("//h1[@id='detpg_hotel_name']").getText();
		System.out.println("The Hotel name is " + htlName);
		Thread.sleep(1000);
		
		driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
		driver.findElementByXPath("(//span[text()='SELECT'])[1]").click();
		driver.findElementByXPath("//span[contains(@class,'close')]").click();
		Thread.sleep(2000);
		
		//System.out.println("Due to ongoing pandemic issue the dates are restricted hence closing the browser");
		String str = driver.findElementByXPath("//span[text()='Given the ongoing Coronavirus pandemic, we request you to keep track of travel advisory & check travel restrictions to make informed decisions.']").getText().replaceAll("\\D","");
		System.out.println(str);
		driver.quit();
		

	}

}
