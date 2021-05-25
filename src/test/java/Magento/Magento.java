package Magento;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Magento 
{
	@Test(priority=2,enabled=false,dependsOnMethods="register")	 
	public void login()
		{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://magento.com");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); //stateless wait. Waits only until the element is present or not.  
		driver.findElement(By.id("gnav_557")).click();
		driver.findElement(By.id("email")).sendKeys("deepthi.s@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("pass@123");
		driver.findElement(By.id("send2")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")));
		String error = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText();
		System.out.println("Error :" +error);
//			if(error.equals("Invalid login or password."))
//				{ 	System.out.println("TestPass");
//				}
		Assert.assertEquals(error, "Invalid login or password."); // validation is done usinf Testng instead of abv. forif loop
		driver.quit();
		}
	
	 @Test(priority=1,invocationCount=2)
	 public void register() throws InterruptedException
	 {
		    WebDriverManager.chromedriver().setup();
		    WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https://magento.com");
			driver.findElement(By.id("gnav_557")).click();
			driver.findElement(By.id("register")).click();
			System.out.println(driver.getTitle());
			driver.findElement(By.id("firstname")).sendKeys("Deepthi");
			driver.findElement(By.id("lastname")).sendKeys("Somu");
			driver.findElement(By.id("self_defined_company")).sendKeys("Magento");
			driver.findElement(By.id("email_address")).sendKeys("deepthi.s@gmail.com");
			
			Select cp = new Select(driver.findElement(By.id("company_type")));
			cp.selectByIndex(3);
						
			Select cp1 = new Select(driver.findElement(By.id("individual_role")));
			cp1.selectByValue("technical/developer");
			
			Select cp2 = new Select(driver.findElement(By.id("country")));
			cp2.selectByVisibleText("United States");
			
			
			driver.findElement(By.xpath("//*[@id=\"country\"]/option[228]")).click();
			driver.findElement(By.id("password")).sendKeys("password@123");
			driver.findElement(By.id("password-confirmation")).sendKeys("password@123");
			
			driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"recaptcha-f979c2ff515d921c34af9bd2aee8ef076b719d03\"]/div/div/iframe")));
			driver.findElement(By.className("recaptcha-checkbox-border")).click(); 
			driver.switchTo().defaultContent();
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");	

		 	if(!driver.findElement(By.id("agree_terms")).isSelected())
			{
		 		driver.findElement(By.id("agree_terms")).click();
			}
			
			Thread.sleep(7000);
			driver.quit();
	 }
}
