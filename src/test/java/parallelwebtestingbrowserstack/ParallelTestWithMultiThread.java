package parallelwebtestingbrowserstack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ParallelTestWithMultiThread {

	WebDriver driver;

	@Test()
	public void testOnChromeWithBrowserStackUrl() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Driver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.browserstack.com/");
		driver.manage().window().maximize();
		System.out.println(
				"this is the test related to chrome browserstack homepage" + " " + Thread.currentThread().getId());

	}
	
	@Test()
	public void testOnChromeWithBrowserStackUrl2() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Driver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.browserstack.com/");
		driver.manage().window().maximize();
		System.out.println(
				"this is the test related to chrome browserstack homepage" + " " + Thread.currentThread().getId());

	}

	@Test()
	public void testOnChromeWithBrowserStackSignUp() {
		System.setProperty("webdriver.gecko.driver",  System.getProperty("user.dir")+"/Driver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.browserstack.com/users/sign_up");
		driver.manage().window().maximize();
		driver.findElement(By.id("user_full_name")).sendKeys("Sadhvi Singh");
		driver.findElement(By.id("user_email_login")).sendKeys("sadhvisingh24@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("browserstack");
		System.out.println(
				"this is the test related to chrome browserstack login" + " " + Thread.currentThread().getId());

	}
	
	@Test()
	public void testOnChromeWithBrowserStackSignUp2() {
		System.setProperty("webdriver.gecko.driver",  System.getProperty("user.dir")+"/Driver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.browserstack.com/users/sign_up");
		driver.manage().window().maximize();
		driver.findElement(By.id("user_full_name")).sendKeys("Sadhvi Singh");
		driver.findElement(By.id("user_email_login")).sendKeys("sadhvisingh24@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("browserstack");
		System.out.println(
				"this is the test related to chrome browserstack login" + " " + Thread.currentThread().getId());

	}

	@AfterClass
	public void close() {
		driver.quit();
	}

}
