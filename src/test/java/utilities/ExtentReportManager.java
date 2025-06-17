package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseTest.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;

	String reportName;

	public void onStart(ITestContext context) {

		String timeStamp = new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss").format(new Date());

		reportName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + reportName);

		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);

		extentReports.setSystemInfo("Application", "Opencart");
		extentReports.setSystemInfo("Module", "Admin");
		extentReports.setSystemInfo("Sub Module", "Customers");
		extentReports.setSystemInfo("Username", System.getProperty("user.name"));
		extentReports.setSystemInfo("Environment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os");
		extentReports.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extentReports.setSystemInfo("Browser", browser);

		List<String> includedgroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedgroups.isEmpty()) {
			extentReports.setSystemInfo("Groups", includedgroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {

		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.PASS, "Test Case Passed is : " + result.getName());

	}

	public void onTestFailure(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());

		extentTest.log(Status.FAIL, "Test Case Failed is : " + result.getName());
		extentTest.log(Status.FAIL, "Test Case Failed cause is : " + result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreenshot(result.getName());
			extentTest.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.SKIP, "Test Case Skip is : " + result.getName());
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		extentReports.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "/Reports/" + reportName;
		File ExtentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(ExtentReport.toURI());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		try {
//			 URL url = new URL("file:///"+System.getProperty("user.dir")+"/Reports/"+reportName); 
//			 //Create the email message
//			 ImageHtmlEmail email = new ImageHtmlEmail();
//			 email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			 email.setHostName("https://mail.google.com/mail/");
//			 email.setSmtpPort(465);
//			 email.setAuthenticator(new DefaultAuthenticator("achirag922@gmail.com", "password"));
//		}

	}
}
