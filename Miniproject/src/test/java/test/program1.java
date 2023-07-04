package test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class program1 {
	

		public WebDriver driver;
		
		
		@BeforeTest
		public void setUp() {
			driver = new ChromeDriver();
			driver.get("https://amazon.co.uk/ ");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		}
		
		@Test
		public void test() {
			String actualTitle=driver.getTitle();
			System.out.println(actualTitle);
			
			
			
		}
		
		@AfterTest
		public void teardown() {
			driver.quit();
		}
	}


