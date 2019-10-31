package ioslocalparalleltesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class IOSApp {

	public static IOSDriver<IOSElement> driver;
	public static DesiredCapabilities cap;

	@BeforeClass
	@Parameters({ "port", "deviceID", "iOSVersion" })
	public void startTest(String port, String device_ID, String iOSVersion)
			throws MalformedURLException, InterruptedException {
		cap = new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device_ID);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, iOSVersion);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		cap.setCapability(MobileCapabilityType.APP,
				System.getProperty("user.dir")+"/WebDriverAgent-ezhomvxlqzquotbyfgilgzmjprzu/Build/Products/Debug-iphonesimulator/IntegrationApp.app");
		driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), cap);

		Thread.sleep(3000L);

	}

	@Test
	public void testiOSApp() throws InterruptedException {
		driver.findElement(MobileBy.AccessibilityId("Scrolling")).click();

		driver.runAppInBackground(Duration.ofSeconds(-1));

		int i = 0;
		while (!driver.findElement(MobileBy.AccessibilityId("Settings")).isDisplayed()) {
			scrollOrSwipe(24, 172, 285, 172, 2);
			i++;
		}
		driver.findElement(MobileBy.AccessibilityId("Settings")).click();

		System.out.println(driver.getDeviceTime());
		// driver.lockDevice();
		Thread.sleep(3000L);
		driver.quit();
	}

	public static void scrollOrSwipe(int x_start, int y_start, int x_end, int y_end, int duration) {

		new TouchAction(driver).press(PointOption.point(x_start, y_start))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(duration)))
				.moveTo(PointOption.point(x_end, y_end)).release().perform();

	}

	private static WaitOptions WaitOptions(Duration ofSeconds) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void tapByElement(WebElement element) {
		new TouchAction(driver).tap(new TapOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
	}

	public static void tapByCoordinates(int x, int y) {
		new TouchAction(driver).tap(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
				.perform();
	}

	@AfterClass
	public void qui() {
		driver.quit();
	}
}
