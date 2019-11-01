package browserstacksingletesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

public class AndroidNativeApp {

	public static AndroidDriver<AndroidElement> driver;
	public static String accessKey = "MASi6q41mPmfVjrvMpxw";
	public static String userName = "anandjois1";

	@BeforeClass
	public void beforeRun() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("device", "Samsung Galaxy S8");
		caps.setCapability("os_version", "7.0");
		caps.setCapability("app", "bs://b2ca4426a8962d8e3b71613ddf603ba4a6dd9d47");
		driver = new AndroidDriver<AndroidElement>(new URL("http://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"),
				caps);
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
