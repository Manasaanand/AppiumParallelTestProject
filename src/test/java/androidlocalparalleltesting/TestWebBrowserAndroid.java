package androidlocalparalleltesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestWebBrowserAndroid {

	public static AndroidDriver<WebElement> driver;
	// public static String firstDeviceName = "emulator-5554";
	// public static String secondDeviceName = "emulator-5556";

	@BeforeClass
	@Parameters({ "port", "deviceID", "androidVersion" })
	public void startTest(String port, String device_ID, String androidVersion) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device_ID);
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability(MobileCapabilityType.VERSION, androidVersion);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability("chromedriverExecutable", "/Users/anandjois/Downloads/chromedriver");

		driver = new AndroidDriver<WebElement>(new URL("http://localhost:" + port + "/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}

	@Test
	public void testWebsite() {
		driver.get("https://1cover.com.au");

	}

	@AfterClass
	public void qui() {
		driver.quit();
	}
}
