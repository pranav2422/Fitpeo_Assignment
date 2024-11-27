package BaseTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utils.ReadProprtyFile;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static WebDriver driver;
    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
	
    @BeforeSuite
	@Parameters(value = {"browsername"}) 
    public void beforeeclass(String bname) throws IOException {
		setupdriver(bname); 
		driver.get(ReadProprtyFile.getproperty("homepageurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
    @BeforeTest
	public void beforeTests() {
      htmlReporter = new ExtentHtmlReporter(".//TestReport//AutomationReport.html");
      htmlReporter.config().setEncoding("utf-8");
      htmlReporter.config().setDocumentTitle("Automation Report");
      htmlReporter.config().setReportName("Automation Test Result");
      htmlReporter.config().setTheme(Theme.DARK);
      extent = new ExtentReports();
      extent.attachReporter(htmlReporter);
      extent.setSystemInfo("Automation Tester", "Pranv disale");
	}
    
    
    
   

	@BeforeMethod
	public void beforeMethods(Method testMethod ) {
		//String browsername = "chrome";
		logger=extent.createTest(testMethod.getName());
	}

	@AfterMethod
	public void afterMethods(ITestResult result) {
		
		if(result.getStatus()==ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: "+methodName + " Passed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, m);
			
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: "+methodName + " Failed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, m);
			//System.out.println(" reach");
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: "+methodName + " Skipped";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			logger.log(Status.SKIP, m);
			
		}
				
	}

	@AfterTest
	public void afterTests() {
		extent.flush();
		
	}
	
	@AfterSuite
	public void afterrclass() {
		driver.quit();
	}
	


	
  public void setupdriver(String browsernamee) {
		  
	  if(browsernamee.equalsIgnoreCase("chrome")) {
	      WebDriverManager.chromedriver().setup(); 
	      driver = new ChromeDriver();
	  }
	  
	  else if (browsernamee.equalsIgnoreCase("firefox")) {
		  driver = new FirefoxDriver();
	  }
	  
	  else {
		  driver = new ChromeDriver(); 
	 } 
 }

}
