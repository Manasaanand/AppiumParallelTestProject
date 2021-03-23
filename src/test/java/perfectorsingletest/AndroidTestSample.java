package perfectorsingletest;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;

public class AndroidTestSample {
	public static String securityTocken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJiYVZyVnJ0V2V3ZVN5dk02cEVObzh2QlJzVzBIeUZOY3V6QzdBMkRBT0M0In0.eyJqdGkiOiI4ZDhjODE2MC0xMTIxLTQ2ODAtOTg3ZC1iZWFlOTdkOTc2NzciLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTcyNTcxOTM0LCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL21vYmlsZWNsb3VkLXBlcmZlY3RvbW9iaWxlLWNvbSIsImF1ZCI6Im9mZmxpbmUtdG9rZW4tZ2VuZXJhdG9yIiwic3ViIjoiMzM5YjgxMmYtZjRlYy00NzEwLThkNDEtNjU1N2Y4NzM0OTM3IiwidHlwIjoiT2ZmbGluZSIsImF6cCI6Im9mZmxpbmUtdG9rZW4tZ2VuZXJhdG9yIiwibm9uY2UiOiI2NTdjMDMxMi03MTY4LTQzZGMtYTUwYS1hZWNiNzdmYzc3MWEiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiI2MDI4NDJiNy00ZmYzLTQ5NzctOWFkMi04ODQzNWUzN2I1ZGMiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX19.DGtb2DBU6sD4dNLX1RB-Gc1XDGQIkdlh_v1seF1akQzhMm2gz_YQzDu1JvvLxwFsYyYikqIzsrA07ddxcuAwdb0yzNxX5cHlzTgmZ2jKs5vQSTyT-PV_twL83SDIu6qi0MrpdJ3f4pANI1IoX0J4Vu4Mi7iW5ocsDffHHn3gk4mdSUjf0XM2aCSSwrQVsj7tMQwwu1h0dG9Y0DoY2Pm84fbyRdA0b4acadhon-9FoQZyK-ipZC9ank1YZP41kz_L4e1eLftRjLCTa07ZzTsOiwfxpU4lPRzIXMv05famgVbyVHhZiVKVrUpF5bJ-buNk2TwinoBobV0T-yN_gAdyLQ";

	public static void main(String[] args) throws IOException {

		String cloudName = "mobilecloud";
		// Update securityToken variable with your perfecto security token.

		// A sample perfecto connect appium script to connect with a perfecto android
		// device and perform addition validation in calculator app.
		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		capabilities.setCapability("securityToken", securityTocken);
		capabilities.setCapability("model", "Galaxy S8");
		capabilities.setCapability("openDeviceTimeout", 2);
		capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");

		WebDriver driver = new RemoteWebDriver(
				new URL("https://" + cloudName + ".perfectomobile.com/nexperience/perfectomobile/wd/hub"),
				capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		PerfectoExecutionContext perfectoExecutionContext;
		// Reporting client. For more details, see
		// https://developers.perfectomobile.com/display/PD/Java
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
			driver.findElement(By.xpath("//*[contains(@resource-id,'popupcalculator:id/bt_01')]")).click();
			driver.findElement(By.xpath("//*[contains(@resource-id,'add')]")).click();
			driver.findElement(By.xpath("//*[contains(@resource-id,'popupcalculator:id/bt_01')]")).click();
			driver.findElement(By.xpath("//*[contains(@resource-id,'equal')]")).click();
			reportiumClient.stepEnd();

			reportiumClient.stepStart("Verify Total");
			WebElement results = driver.findElement(By.xpath("//*[contains(@class,'EditText')]"));
			if (!results.getText().equals("2"))
				throw new RuntimeException("Actual calculated number is : " + results.getText()
						+ ". It did not match with expected value: 2");
			reportiumClient.stepEnd();

			// STOP TEST
			TestResult testResult = TestResultFactory.createSuccess();
			reportiumClient.testStop(testResult);

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
