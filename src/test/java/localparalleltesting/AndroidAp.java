package localparalleltesting;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidAp {

	public AndroidDriver<AndroidElement> driver;
	public static DesiredCapabilities cap;

	@BeforeClass
	@Parameters({ "port", "deviceID", "androidVersion" })
	public void startTest(String port, String device_ID, String androidVersion) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device_ID);
		cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability(MobileCapabilityType.VERSION, androidVersion);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		File apkfile = new File(System.getProperty("user.dir") + "//General-Store.apk"); // "//ApiDemos-debug.apk");
		cap.setCapability(MobileCapabilityType.APP, apkfile.getAbsolutePath());
		System.out.println(apkfile.getAbsolutePath());
		cap.setCapability(CapabilityType.BROWSER_NAME, "");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		cap.setCapability("device", "Android");

		driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:" + port + "/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test
	public void testWebsite() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//*[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
		// driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new
		// UiSelector().scrollable(true).instance(0)).scrollIntoView(new
		// UiSelector().textMatches(\"" + containedText + "\").instance(0))"));
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		// driver.findElement(MobileBy.AndroidUIAutomator(
		// "new UiScrollable(new
		// UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new
		// UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));
		// int count =
		// driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		// for (int i = 0; i < count; i++) {
		// String text =
		// driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
		// if (text.equalsIgnoreCase("Jordan 6 Rings")) {
		// driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
		// break;
		// }
		// }
		// driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		// String lastpageText =
		// driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		// Assert.assertEquals("Jordan 6 Rings", lastpageText);

		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();

		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		Thread.sleep(4000);

		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();

		double sum = 0;

		for (int i = 0; i < count; i++)

		{

			String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i)
					.getText();

			double amount = getAmount(amount1);

			sum = sum + amount;// 280.97+116.97

		}

		System.out.println(sum + "sum of products");

		String total = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();

		total = total.substring(1);

		double totalValue = Double.parseDouble(total);

		System.out.println(totalValue + "Total value of products");

		Assert.assertEquals(sum, totalValue);

		// Mobile Gestures

		WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));

		TouchAction t = new TouchAction(driver);

		t.tap(tapOptions().withElement(element(checkbox))).perform();

		WebElement tc = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));

		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();

		driver.findElement(By.id("android:id/button1")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

	}

	public static double getAmount(String value)

	{

		value = value.substring(1);

		double amount2value = Double.parseDouble(value);

		return amount2value;

	}

	@AfterClass
	public void qui() {
		driver.quit();
	}
}
