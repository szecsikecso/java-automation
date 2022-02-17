package com.home.qa.util;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports createInstance() {

		String filename = getReportName();
		String directory = System.getProperty("user.dir") + "/test-output/Reports/";
		new File(directory).mkdirs();

		String path = directory + filename;
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);

		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Home");
		extent.setSystemInfo("Project", "Home");
		extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		extent.attachReporter(htmlReporter);

		return extent;
	}

	public static String getReportName() {

		Date d = new Date();
		String filename = "AutomationReport.html"/*
													 * + "_" + d.toString().replace(":", "_").replace(" ", "_") +
													 * ".html"
													 */;

		return filename;
	}

	public static String getReportLocation() {

		String fileLoc = System.getProperty("user.dir") + "/test-output/Reports/" + getReportName();

		return fileLoc;
	}

}
