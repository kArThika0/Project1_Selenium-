package utils;



import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.baseTest;

public class RetryAndScreenshot implements ITestListener, IAnnotationTransformer{

	 public void onTestFailure(ITestResult result) {
		   
		 String filename= System.getProperty("user.dir")+"Miniproject/sceenshots/1";
		 File f1=((TakesScreenshot)baseTest.driver).getScreenshotAs(OutputType.FILE);
		 try {
			FileUtils.copyFile(f1, new File(filename+ ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 }
	
}
