package base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.internal.TestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.Constant;

public class baseTest {

	public static WebDriver driver;
	public ExtentReports extent;
	public ExtentTest logger;
	public ExtentSparkReporter sparkReporter;


	@BeforeTest
	public void beforeTest() {

		sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir") + "Miniproject/reports/");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

	}

	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browser, Method testMethod) {
		logger= extent.createTest(testMethod.getName());
		setupDriver(browser);
		driver.get(Constant.url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}


	@AfterMethod
	public void afterMethod(ITestResult result) {

		if(result.getStatus()== ITestResult.FAILURE) {
			logger.log(Status.FAIL,"Test case Fail");
		}
		else if(result.getStatus()== ITestResult.SKIP) {
			logger.log(Status.SKIP,"Test case Skipped");
		}
		else if(result.getStatus()== ITestResult.SUCCESS) {
			logger.log(Status.PASS,"Test case Pass");
		}
	}

	@AfterTest
	public void afterTest() {
		extent .flush();	
	}

	public void setupDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			driver= new FirefoxDriver();
		}
		else {
			driver= new InternetExplorerDriver();
		}
	}
}
