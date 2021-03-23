package perfectorsingletest;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;

public class AndroidApp {

	public static String securityTocken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJiYVZyVnJ0V2V3ZVN5dk02cEVObzh2QlJzVzBIeUZOY3V6QzdBMkRBT0M0In0.eyJqdGkiOiI4ZDhjODE2MC0xMTIxLTQ2ODAtOTg3ZC1iZWFlOTdkOTc2NzciLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTcyNTcxOTM0LCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL21vYmlsZWNsb3VkLXBlcmZlY3RvbW9iaWxlLWNvbSIsImF1ZCI6Im9mZmxpbmUtdG9rZW4tZ2VuZXJhdG9yIiwic3ViIjoiMzM5YjgxMmYtZjRlYy00NzEwLThkNDEtNjU1N2Y4NzM0OTM3IiwidHlwIjoiT2ZmbGluZSIsImF6cCI6Im9mZmxpbmUtdG9rZW4tZ2VuZXJhdG9yIiwibm9uY2UiOiI2NTdjMDMxMi03MTY4LTQzZGMtYTUwYS1hZWNiNzdmYzc3MWEiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiI2MDI4NDJiNy00ZmYzLTQ5NzctOWFkMi04ODQzNWUzN2I1ZGMiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX19.DGtb2DBU6sD4dNLX1RB-Gc1XDGQIkdlh_v1seF1akQzhMm2gz_YQzDu1JvvLxwFsYyYikqIzsrA07ddxcuAwdb0yzNxX5cHlzTgmZ2jKs5vQSTyT-PV_twL83SDIu6qi0MrpdJ3f4pANI1IoX0J4Vu4Mi7iW5ocsDffHHn3gk4mdSUjf0XM2aCSSwrQVsj7tMQwwu1h0dG9Y0DoY2Pm84fbyRdA0b4acadhon-9FoQZyK-ipZC9ank1YZP41kz_L4e1eLftRjLCTa07ZzTsOiwfxpU4lPRzIXMv05famgVbyVHhZiVKVrUpF5bJ-buNk2TwinoBobV0T-yN_gAdyLQ";

	@Test
	public void androidTest() throws IOException {

		String cloudName = "mobilecloud";
		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		capabilities.setCapability("user", "anand.abi@gmail.com");
		capabilities.setCapability("password", "Atindra16!");//"UneNa6Uvu");
		// capabilities.setCapability("automationName", "Appium");
		// capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		// capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		// capabilities.setCapability("device", "Android");
		// capabilities.setCapability("securityToken", securityTocken);
		// capabilities.setCapability("platformName", "Android");
		// capabilities.setCapability("platformVersion", "9");
		// capabilities.setCapability("location", "EU-DE-FRA");
		// capabilities.setCapability("resolution", "1440x3040");
		// capabilities.setCapability("manufacturer", "Samsung");
		// capabilities.setCapability("model", "Galaxy S10");
		// capabilities.setCapability("openDeviceTimeout", 2);
		// capabilities.setCapability("deviceName", "RF8M60HL2EJ");
		String host = "mobilecloud.perfectomobile.com";

//				 PerfectoLabUtils.uploadMedia(host, "anand.abi@gmail.com", "UneNa6Uvu", System.getProperty("user.dir") + "//selendroid-test-app-0.17.0.apk", "PRIVATE:applications/selendroid-test-app-0.17.0.apk");
//		 capabilities.setCapability("appPackage", "io.selendroid.testapp");
//		 capabilities.setCapability("appActivity", ".HomeScreenActivity");
		// capabilities.setCapability("autoInstrument", true);

//		String host = "mobilecloud.perfectomobile.com";
		capabilities.setCapability("securityToken", securityTocken);
		capabilities.setCapability("deviceName", "RF8M60HL2EJ");
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability("useAppiumForWeb", true);
//		PerfectoLabUtils.setExecutionIdCapability(capabilities, host);

		RemoteWebDriver driver = new RemoteWebDriver(
				new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"),
				capabilities);

		PerfectoExecutionContext perfectoExecutionContext;
		if (System.getProperty("reportium-job-name") != null) {
			perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
					.withProject(new Project("My Project", "1.0"))
					.withJob(new Job(System.getProperty("reportium-job-name"),
							Integer.parseInt(System.getProperty("reportium-job-number"))))
					.withContextTags("tag1").withWebDriver(driver).build();
		} else {
			perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
					.withProject(new Project("My Project", "1.0")).withContextTags("tag1").withWebDriver(driver)
					.build();
		}
		ReportiumClient reportiumClient = new ReportiumClientFactory()
				.createPerfectoReportiumClient(perfectoExecutionContext);

		try {
			reportiumClient.testStart("My Calculator Test", new TestContext("tag2", "tag3"));
			reportiumClient.stepStart("Perform addition");
			driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
			// driver.hideKeyboard();
			driver.findElement(By.xpath("//*[@text='Female']")).click();
			driver.findElement(By.id("android:id/text1")).click();
			// driver.findElementByAndroidUIAutomator(
			// "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
			throw new RuntimeException(
					"Actual calculated number is : \"+results.getText() + \". It did not match with expected value: 2");

		} catch (Exception e) {
			TestResult testResult = TestResultFactory.createFailure(e);
			reportiumClient.testStop(testResult);
			e.printStackTrace();

		} finally {
			driver.close();
			driver.quit();
			// Retrieve the URL to the DigitalZoom Report
			String reportURL = reportiumClient.getReportUrl();
			System.out.println(reportURL);
		}
	}

}
