package WorkTypeGroup;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.jsoup.select.Evaluator.IsEmpty;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditWorkTypeGroup {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		Actions action = new Actions(driver);
		
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
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
		
		WebElement workTypeGroup = driver.findElementByXPath("(//a/span[contains(text(),'Work Type Groups')])[1]");
		executor.executeScript("arguments[0].click()", workTypeGroup);
		
		
		//driver.findElementByXPath("//input[@type='search' and @name='WorkTypeGroup-search-input']").click();
		//driver.findElementByXPath("//input[@type='search' and @name='WorkTypeGroup-search-input']").sendKeys("Salesforce Automation by Sandhiya", Keys.ENTER);
		
		Thread.sleep(2000);
		List<WebElement> workTypeTableRows = driver.findElementsByXPath("(//table[contains(@class,'forceRecordLayout')]/tbody/tr)");
		
		System.out.println(workTypeTableRows.size());
		int tableSize = workTypeTableRows.size();
		if(tableSize>0) {
			for (WebElement row: workTypeTableRows) {
				System.out.println(row);
				//executor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", row);
				WebElement rows = row;
				WebElement rowDetail = row.findElement(By.xpath(".//th//a"));
				
				executor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", rowDetail);
				String workTypeName = rowDetail.getText();
				if(workTypeName.equalsIgnoreCase("Salesforce Automation by Sandhiya")) {
					System.out.println(row);
					
					rows.findElement(By.xpath(".//td//a")).click();
					System.out.println("Pass");
					WebElement editBtn = driver.findElementByXPath("//div[text()='Edit']");
					executor.executeScript("arguments[0].click()", editBtn);
					driver.findElementByClassName("textarea").click();
					driver.findElementByClassName("textarea").sendKeys("Automation");
					driver.findElementByXPath("//button[@title='Save']").click();
					String successObj = driver.findElementByXPath("//div[contains(@class,'slds-theme--success')]//span[contains(@class,'toastMessage')]").getText();
					System.out.println(successObj);
					if(successObj.indexOf("\"Salesforce Automation by Sandhiya\" was saved")>0) {
						System.out.println("Pass");

					}else {
						System.out.println("Fail");
					}
					
					break;
				}
				
			}
		}else{
			System.out.println("Value is not matched to edit");
		}
		
		driver.quit();
		
	}
}


