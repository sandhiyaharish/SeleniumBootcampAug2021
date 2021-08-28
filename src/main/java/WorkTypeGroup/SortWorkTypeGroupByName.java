package WorkTypeGroup;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortWorkTypeGroupByName {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(option);
		Actions action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		boolean sorted = false;
		
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
		
		Thread.sleep(2000);		
		
		WebElement sort = driver.findElementByXPath("//a/span[text()='Sort' and following-sibling::span[text()='Work Type Group Name']]");
		executor.executeScript("arguments[0].click()", sort);
		Thread.sleep(2000);
		List<WebElement> workTypeTableRows = driver.findElementsByXPath("(//table[contains(@class,'forceRecordLayout')]/tbody/tr)");
		int tableSize = workTypeTableRows.size();
		System.out.println(tableSize);
		if(tableSize>0) {
			for(int i =0; i<tableSize;i++) {
				if(i!=tableSize-1) {
				WebElement rowDetail = workTypeTableRows.get(i).findElement(By.xpath(".//th//a"));
				WebElement rowDetailNext = workTypeTableRows.get(i+1).findElement(By.xpath(".//th//a"));
				
				String firstRowName = rowDetail.getText();
				String secondRowName = rowDetailNext.getText();
				System.out.println(firstRowName);
				System.out.println(secondRowName);
				System.out.println(firstRowName.toLowerCase().compareTo(secondRowName.toLowerCase()));
				
				if(firstRowName.toLowerCase().compareTo(secondRowName.toLowerCase())<=0) {
					sorted = true;
				}else {
					sorted =false;
					break;
				}
				System.out.println(sorted);
				}
			}
		
		}else {
			System.out.println("Table is empty");
		}
		
		System.out.println(sorted ? "sorted":"NotSorted");
		driver.quit();
	}

}
