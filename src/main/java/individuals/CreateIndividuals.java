package individuals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateIndividuals {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		driver.findElementById("username").sendKeys("matschie@testleaf.com");
		driver.findElementById("password").sendKeys("SelBootcamp$123");
		driver.findElementById("Login").click();
		
		driver.findElementByClassName("slds-icon-waffle").click();
		driver.findElementByXPath("//button[text()='View All']").click();
		WebElement Individuals = driver.findElementByXPath("//p[text()='Individuals']");
		action.moveToElement(Individuals).perform();
		driver.findElementByXPath("//p[text()='Individuals']").click();
		
		List<WebElement> newIndividualObj = driver.findElements(By.xpath("//a[contains(@title,'Individuals')]/following-sibling::one-app-nav-bar-item-dropdown/div"));
		System.out.println(newIndividualObj.size());
		if(newIndividualObj.size()>0){
			newIndividualObj.get(0).click();
			WebElement newIndividual = driver.findElementByXPath("//span[text()='New Individual']");
			newIndividual.click();
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", newIndividual);
			
		}else {
			driver.findElementByXPath("//div[text()='New']").click();
		}
		
		driver.findElementByClassName("lastName").sendKeys("Kumar");
		driver.findElementByXPath("//button[@title='Save']").click();
		System.out.println(driver.findElementByXPath("//div[contains(@class,'active')]//span[@class='uiOutputText' and text()='Kumar']").isDisplayed());
		driver.findElementByXPath("//div[contains(@class,'active')]//span[@class='uiOutputText' and text()='Kumar']").isDisplayed();
		driver.close();

	}

}
