package browserstacksingletesting;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestAndroidApp {

	public static String accessKey = "MASi6q41mPmfVjrvMpxw";
	public static String userName = "anandjois1";

	public static void main(String args[]) throws MalformedURLException, InterruptedException {
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("device", "Samsung Galaxy S8");
		caps.setCapability("os_version", "7.0");
		caps.setCapability("app", "bs://b2ca4426a8962d8e3b71613ddf603ba4a6dd9d47");

		AndroidDriver<AndroidElement> driver = new AndroidDriver(
				new URL("http://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);

		/* Write your Custom code here */

		driver.quit();
	}
}
