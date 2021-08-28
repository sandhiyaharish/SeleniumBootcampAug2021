package WorkTypeGroup;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteWorkTypeGroup {
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
		
		
		driver.findElementByXPath("//input[@type='search' and @name='WorkTypeGroup-search-input']").click();
		driver.findElementByXPath("//input[@type='search' and @name='WorkTypeGroup-search-input']").sendKeys("Salesforce Automation by Sandhiya", Keys.ENTER);
		
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
					
					WebElement dropdownBtn = rows.findElement(By.xpath(".//td//a"));
					executor.executeScript("arguments[0].click()", dropdownBtn);
					System.out.println("Pass");
					WebElement deleteBtn = driver.findElementByXPath("//div[text()='Delete']");
					executor.executeScript("arguments[0].click()", deleteBtn);
					driver.findElementByXPath("//span[text()='Delete']").click();
					
					Thread.sleep(5000);
					String successObj = driver.findElementByXPath("//div[contains(@class,'slds-theme--success')]//span[contains(@class,'toastMessage')]").getText();
					System.out.println(successObj);
					if(successObj.indexOf("\"Salesforce Automation by Sandhiya\" was deleted")>0) {
						System.out.println("Pass");

					}else {
						System.out.println("Fail");
					}
					break;
				}
			}
		}
		
		List<WebElement> workTypeTableRowsAfterDeletion = driver.findElementsByXPath("(//table[contains(@class,'forceRecordLayout')]/tbody/tr)");
		if(workTypeTableRowsAfterDeletion.size()<tableSize) {
			System.out.println("Worktype is deleted successfully");
		}
		
	}
}