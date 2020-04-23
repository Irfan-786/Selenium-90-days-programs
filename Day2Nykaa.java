package selenium.ninetydays;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Day2Nykaa {

	public static ChromeDriver driver;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement hover = driver.findElementByXPath("//li[contains(@class,'menu-dropdown-icon')]");
		Actions actions = new Actions(driver);
		actions.moveToElement(hover).perform();
		driver.findElementByLinkText("L'Oreal Paris").click();
		Thread.sleep(3000);
		
		String str = driver.findElementByXPath("//h1[contains(@class,'heading--fancy')]").getText();
		String[] strSplit = str.split("\\s");
		
		String str2 = strSplit[0] + " " + strSplit[1];
		if (str2.equalsIgnoreCase("L'Oreal Paris")) {
			System.out.println("The page title contains " + str2);	
			} else {
			System.out.println("The page title does not contains " + str2);
			}
		driver.findElementByXPath("//i[contains(@class,'fa fa-angle-down')]").click();
		driver.findElementByXPath("//div[contains(@for,'3')]").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("(//span[contains(text(), 'Shampoo')])[1]").click();
		
		String filterShamp = driver.findElementByXPath("//li[contains(text(),'Shampoo')]").getText();
		if (filterShamp.contains("Shampoo")) {
			System.out.println("The filter is applied for Shampoo");
		} else {
			System.out.println("The filter is not applied for Shampoo");
		}
		
		driver.findElementByXPath("(//img[contains(@class,'listing-img')])[4]").click();
		
		Set<String> winSet = driver.getWindowHandles();
		List<String> WinLis = new ArrayList<String>(winSet);
		driver.switchTo().window(WinLis.get(1));
		
		driver.findElementByXPath("(//span[contains(@class,'size-pallets')])[2]").click();
		String pdtPrice = driver.findElementByXPath("//span[contains(@class,'post-card__content-price-offer')]").getText().replaceAll("\\D", "");
		System.out.println("The MRP price of the shampoo is " + pdtPrice );
		driver.findElementByXPath("(//button[text()='ADD TO BAG'])[1]").click();
		driver.findElementByClassName("AddBagIcon").click();
		String grTotal = driver.findElementByXPath("//div[contains(@class,'value medium-strong')]").getText().replaceAll("\\D", "");
		System.out.println("The Grand Total is " + grTotal);
		driver.findElementByXPath("//span[text()='Proceed']").click();
		driver.findElementByXPath("//button[text()='Ok']").click();
		Thread.sleep(5000);
		String warnMess = driver.findElementByXPath("//div[contains(@class,'message')]").getText();
		System.out.println("The warning message is " + warnMess);
		driver.quit();
		
	}

}
