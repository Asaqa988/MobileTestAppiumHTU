import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.DrbgParameters.Capability;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class myTestCases {

	AndroidDriver driver;

	DesiredCapabilities caps = new DesiredCapabilities();

	Random rand = new Random();

	@BeforeTest
	public void mySetup() {

		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "abc");
		File myapp = new File("src/myapplication/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myapp.getAbsolutePath());
	}

	@BeforeMethod
	public void thisPartStartsBeforeEachTest() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

	}

	@Test(priority = 1, description = "multiply 2 numbers", groups = "happy", enabled = false)

	public void myTest() throws MalformedURLException {

		driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();

		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();

		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
	}

	@Test(priority = 2, enabled = false)
	public void ClickOnAllNumbersOnly() {

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {

			if (allButtons.get(i).getDomAttribute("resource-id").contains("digit"))
				allButtons.get(i).click();
		}
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_1")).click();

		String results = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		System.out.println(results);
		String expectedResults = "7894561230";
		Assert.assertEquals(results, expectedResults);

	}

	@Test(priority = 3, enabled = false)
	public void ClickOnOddNumbers() {

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {

			if (allButtons.get(i).getDomAttribute("resource-id").contains("digit_8")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("digit_4")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("digit_6")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("digit_2")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("digit_0"))

				allButtons.get(i).click();
		}

	}

	@Test(priority = 4)
	public void clickOnTwoRandomNumber() {

		rand.nextInt(0, 10);

		String firstNumber = Integer.toString(rand.nextInt(0, 10));

		String SecondNumber = Integer.toString(rand.nextInt(0, 10));

		System.out.println(firstNumber);
		System.out.println(SecondNumber);

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < allButtons.size(); i++) {
			if (allButtons.get(i).getDomAttribute("resource-id").contains(firstNumber)
					|| allButtons.get(i).getDomAttribute("resource-id").contains(SecondNumber)) {
				allButtons.get(i).click();
				driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
			} else if (allButtons.get(i).getDomAttribute("resource-id").contains(SecondNumber)
					|| allButtons.get(i).getDomAttribute("resource-id").contains(firstNumber)) {

				allButtons.get(i).click();
			}
		}

	}

	@AfterTest
	public void AftermyTest() {
	}

}
