package browserstackparalleltesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class IOSNativeApp {

	public static IOSDriver<IOSElement> driver;
	public static String accessKey = "MASi6q41mPmfVjrvMpxw";
	public static String userName = "anandjois1";

	@BeforeClass
	@Parameters({ "deviceID", "appDetails" })
	public void beforeRun(String deviceID,String appDetails) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("device", deviceID);
		// caps.setCapability("os_version", iOSVersion);
		caps.setCapability("app", appDetails);
		driver = new IOSDriver<IOSElement>(
				new URL("http://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
	}

	@Test
	public void testAndroidApp() throws InterruptedException {
		IOSElement textButton = (IOSElement) new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Button")));
		textButton.click();
		IOSElement textInput = (IOSElement) new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Input")));
		textInput.sendKeys("hello@browserstack.com" + "\n");

		Thread.sleep(5000);

		IOSElement textOutput = (IOSElement) new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Output")));

		if (textOutput != null && textOutput.getText().equals("hello@browserstack.com"))
			assert (true);
		else
			assert (false);
	}

	@AfterClass
	public void after() {
		driver.quit();
	}

}
