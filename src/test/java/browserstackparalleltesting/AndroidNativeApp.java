package browserstackparalleltesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AndroidNativeApp {

	public static AndroidDriver<AndroidElement> driver;
	public static String accessKey = "MASi6q41mPmfVjrvMpxw";
	public static String userName = "anandjois1";

	@BeforeClass
	@Parameters({ "deviceID", "appDetails" })
	public void beforeRun(String deviceID,  String appDetails) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("device", deviceID);
		// caps.setCapability("os_version", "7.0");
		caps.setCapability("app", appDetails);
		driver = new AndroidDriver<AndroidElement>(
				new URL("http://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
	}

	@Test
	public void testAndroidApp() {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Anand");
		// Hide Keyboard
		driver.hideKeyboard();
		// bullet points
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	}

	@AfterClass
	public void after() {
		driver.quit();
	}

}
