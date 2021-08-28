package WorkTypeGroup;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateWorkTypeGroup {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		Actions action = new Actions(driver);
		
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		JavascriptExecutor executor =(JavascriptExecutor)driver;
		
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.findElementById("username").sendKeys("matschie@testleaf.com");
		driver.findElementById("password").sendKeys("SelBootcamp$123");
		driver.findElementById("Login").click();
		
		driver.findElementByClassName("slds-icon-waffle").click();
		driver.findElementByXPath("//button[text()='View All']").click();
		
		action.moveToElement(driver.findElementByXPath("//p[text()='Work Type Groups']")).perform();
		
		driver.findElementByXPath("//p[text()='Work Type Groups']").click();
		
		List<WebElement> worktypegroupobjs = driver.findElementsByXPath("//a/span[contains(text(),'Work Type Groups')]");
		
		WebElement workTypeGroup = driver.findElementByXPath("(//a/span[contains(text(),'Work Type Groups')])[1]");
		executor.executeScript("arguments[0].click()", workTypeGroup);
		
		
		//driver.findElementByXPath("//a/span[contains(text(),'Work Type Groups')]").click();
		List<WebElement> dropdownObjs = driver.findElements(By.xpath("//a[contains(@title,'Work Type Groups')]//following-sibling::one-app-nav-bar-item-dropdown//lightning-icon"));
	
		WebElement dropdownBtn = driver.findElement(By.xpath("//a[contains(@title,'Work Type Groups')]//following-sibling::one-app-nav-bar-item-dropdown//lightning-icon"));
		
		executor.executeScript("arguments[0].click()", dropdownBtn);
		
		WebElement newWorkTypeGroup = driver.findElement(By.xpath("//span[text()='New Work Type Group']"));
		executor.executeScript("arguments[0].click()",newWorkTypeGroup);
		
		driver.findElement(By.xpath("//label[descendant::span[text()='Work Type Group Name']]/following-sibling::input")).sendKeys("Salesforce Automation by Sandhiya");
		driver.findElementByXPath("//button[@title='Save']").click();
		
		driver.findElementByXPath("//div[contains(@class,'slds-page-header__title')]//span[@class='uiOutputText' and text()='Salesforce Automation by Sandhiya']").isDisplayed();
		
		String successObj = driver.findElementByXPath("//div[contains(@class,'slds-theme--success')]//span[contains(@class,'toastMessage')]").getText();
		
		if(successObj.indexOf("\"Salesforce Automation by Sandhiya\" was created")>0) {
			System.out.println("Pass");
			
		}else {
			System.out.println("Fail");
		}
		
		driver.quit();

	}

}
