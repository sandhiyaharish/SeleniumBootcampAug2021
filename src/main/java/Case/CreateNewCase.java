package Case;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewCase {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Actions action = new Actions(driver);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.findElementById("username").sendKeys("matschie@testleaf.com");
		driver.findElementById("password").sendKeys("SelBootcamp$123");
		driver.findElementById("Login").click();
		
		driver.findElementByClassName("slds-icon-waffle").click();
		driver.findElementByXPath("//button[text()='View All']").click();
		
		WebElement Sales = driver.findElementByXPath("//p[text()='Sales']");
		executor.executeScript("arguments[0].click()",Sales);
		
		Thread.sleep(2000);
		WebElement moreOption = driver.findElementByXPath("//a//span[text()='More']");
		executor.executeScript("arguments[0].click()",moreOption);
		
		WebElement casesObj = driver.findElementByXPath("//div[contains(@class,'more-item')]//a//span[text()='Cases']");
		executor.executeScript("arguments[0].click()", casesObj);
		
		driver.findElementByXPath("//div[text()='New']").click();
		
		WebElement contactName = driver.findElementByXPath("//input[contains(@class,'uiInputTextForAutocomplete') and ancestor::div[preceding-sibling::label/span[text()='Contact Name']]]");
		contactName.click();
		contactName.sendKeys("ss chan");
		
		driver.findElementByXPath("//div/ul[@class='lookup__list  visible']/li//div[@title='SS Chan']").click();
		
		driver.findElementByXPath("//input[@role='combobox' and ancestor::div[preceding-sibling::label/abbr]]").click();
		WebElement escalatedItem = driver.findElementByXPath("//lightning-base-combobox-item[@data-value='Escalated']");
		executor.executeScript("arguments[0].click()",escalatedItem);
		
		
		
		
		

	}

}
