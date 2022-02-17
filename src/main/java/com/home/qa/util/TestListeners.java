package com.home.qa.util;

import com.home.qa.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class TestListeners extends TestBase implements ITestListener {

    private static final ExtentReports extent = ExtentManager.createInstance();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        ExtentTest test = extent
                .createTest(result.getTestClass().getName() + "::" + result.getMethod().getMethodName());

        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        String logText = "<b>Test Method_" + result.getMethod().getMethodName() + " : Successful</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, m);

    }

    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        /*
        extentTest.get().fail("<details><summary><b><font color = red>" +
                "Exception Occured, click to see details:" + "</font></b></summary>" +
                exceptionMessage.replaceAll(",", "<br>") + "</details> n");

        String path = takeScreenshot(driver, result.getMethod().getMethodName());

        extentTest.get().fail("<b><font color = red>" + "Screenshot o failure" + "</font></b>",
                MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());

        String logText = "<b>Test Method_ " + methodName + " :Failed</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
         */
    }

    public void onTestSkipped(ITestResult result) {
        String logText = "<b>Test Method" + result.getMethod().getMethodName() + "Successful</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, m);

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }

    }

    public String takeScreenshot(WebDriver driver, String methodName) {

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String encodedBase64 = null;
        FileInputStream fileInputStreamReader = null;
        try {
            fileInputStreamReader = new FileInputStream(scrFile);
            byte[] bytes = new byte[(int) scrFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/png;base64," + encodedBase64;
    }

    public String getScreenshotName(String methodName) {

        Date d = new Date();
        String fileName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
        return fileName;
    }

}
