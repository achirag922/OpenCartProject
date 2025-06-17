package baseTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		logger = LogManager.getLogger(this.getClass());
		FileReader file = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
		p = new Properties();
		p.load(file);

		if (p.getProperty("execution-env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			if (os.equalsIgnoreCase("Mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else if (os.equalsIgnoreCase("Windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else {
				System.out.println("No Matching OS");
				return;
			}

			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("edge");
				break;
			case "default":
				System.out.println("No Matching Browser");
				return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}

		if (p.getProperty("execution-env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "default":
				System.out.println("Invalid Browser");
				return;
			}
		}
		driver.get(p.getProperty("appUrl"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	public String randomNumbers() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(4);
		String generatedNumbers = RandomStringUtils.randomNumeric(4);
		return (generatedString + generatedNumbers);
	}

	public String captureScreenshot(String tname) {

		String timeStamp = new SimpleDateFormat("YYYYMMddHHmmss").format(new Date());

		TakesScreenshot sc = (TakesScreenshot) driver;
		File sourceFile = sc.getScreenshotAs(OutputType.FILE);
		String targetFilepath = System.getProperty("user.dir") + "/Screenshots/" + tname + "_" + timeStamp;
		File targetFile = new File(targetFilepath);
		sourceFile.renameTo(targetFile);
		return targetFilepath;
	}

}
