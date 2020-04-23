package selenium.ninetydays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Day1Myntra {
	public static ChromeDriver driver;
	static int totalCategory = 0;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement hover = driver.findElementByXPath("//div[contains(@class,'desktop-navContent')][2]");
		Actions actions = new Actions(driver);
		actions.moveToElement(hover).perform();
		driver.findElementByLinkText("Jackets & Coats").click();
		Thread.sleep(3000);
		//Map<String, Object> prefs = new HashMap<String, Object>();
		//prefs.put("profile.default_content_setting_values.notifications", 2);
		//ChromeOptions options = new ChromeOptions();
		//options.setExperimentalOption("prefs", prefs);
		//WebDriver noti = new ChromeDriver(options);
		String itemCountstr = driver.findElementByXPath("//span[contains(@class,'title-count')]").getText().replaceAll("\\D","");
		int i = Integer.parseInt(itemCountstr);
		System.out.println("The total count is " + i);
		List<WebElement> eleCategories = driver.findElementsByXPath("//span[@class='categories-num']");
		System.out.println("Total Category " + eleCategories.size());
		for (WebElement t1 : eleCategories) {
			String text = t1.getText();
			text = text.replace("(", "");
			text = text.replace(")", "");
			int i1 = Integer.parseInt(text);
			totalCategory = totalCategory + i1;
		}
		System.out.println("Sum of each Category is " + totalCategory);

		if (i==totalCategory) {
			System.out.println("Count Match");	
		}
		driver.findElementByXPath("(//div[contains(@class ,'common-checkboxIndicator')])[2]").click();	
		driver.findElementByXPath("//div[contains(@class,'brand-more')]").click();
		driver.findElementByXPath("//input[contains(@class,'FilterDirectory-searchInput')]").sendKeys("MANGO");
		driver.findElementByXPath("//ul[contains(@class,'FilterDirectory-list')]/li[2]").click();
		driver.findElementByXPath("//span[contains(@class,'myntraweb-sprite FilterDirectory-close sprites-remove')]").click();
		Thread.sleep(2000);
		List<WebElement> brandName = driver.findElementsByXPath("//h3[@class='product-brand']");
		for (WebElement each : brandName) {

		if (each.getText().equalsIgnoreCase("MANGO")) {
		System.out.println("All products are related to the Brand - MANGO");
		} else {
		System.out.println("TC's Fail. Product Brand mis match..!");
		break;
		 }
		}

		WebElement hover1 = driver.findElementByXPath("//div[contains(@class,'sort-sortBy')]");
		actions.moveToElement(hover1).perform();
		driver.findElementByXPath("(//label[@class='sort-label '])[3]").click();
		actions.moveToElement(driver.findElementByXPath("//ul[@class='filter-summary-filterList']")).perform();
		Thread.sleep(3000);
		
		List<WebElement> eachProductPrice = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		String t1 = eachProductPrice.get(0).getText().replaceAll("\\D","");
		int p1 = Integer.parseInt(t1);
		System.out.println(p1);
		WebElement hover2 = driver.findElementByXPath("//span[@class='product-discountedPrice']");
		actions.moveToElement(hover2).perform();
		driver.findElementByXPath("//span[@class='product-launchDate']").click();
		driver.close();
	}

}
