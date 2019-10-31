package ioslocalparalleltesting;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class IOSWebApp {
	public static IOSDriver<WebElement> driver;

	public DesiredCapabilities cap;

	@BeforeClass
	@Parameters({ "port", "deviceID", "iOSVersion" })
	public void startTest(String port, String device_ID, String iOSVersion)
			throws MalformedURLException, InterruptedException {
		cap = new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device_ID);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, iOSVersion);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");

		driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), cap);

		Thread.sleep(3000L);

	}

	@Test
	public void testiOSApp() throws InterruptedException {
		System.out.println("Ilove web apps");
	}

	@AfterClass
	public void qui() {
		driver.quit();
	}

}
