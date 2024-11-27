package PageEvent;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Utils.ReadProprtyFile;

public class CalculateRevenuePage {
	
	public void nevigateToHomePageMethod() {
		try {
			BaseTest.BaseTest.driver.get(ReadProprtyFile.getproperty("homepageurl"));
			BaseTest.BaseTest.logger.info("landed on Fitpeo HomePage");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("not able to nevigate to RevenueCalculatorPage");
		}
	}
	
	
	public void nevigateToRevenueCalculatorPageimp() {
		try {
			BaseTest.BaseTest.driver.get(ReadProprtyFile.getproperty("revenuecalculatorpageurl"));
			BaseTest.BaseTest.logger.info("landed on RevenueCalculatorPage");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("not able to nevigate to RevenueCalculatorPage");
		}
	}
	
	public void scrolldownPageTillCalculatorimp() {
		WebElement RevenueCalculator = BaseTest.BaseTest.driver.findElement(By.xpath("//h5[text()='Total Gross Revenue Per Year']"));
		try {
			JavascriptExecutor js = (JavascriptExecutor)  BaseTest.BaseTest.driver;
			js.executeScript("arguments[0].scrollIntoView(true);", RevenueCalculator);
			Thread.sleep(2000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
			throw new RuntimeException("not able to scroll till view") ;
		}
	}
	
	public void adjustslidebarimp() throws InterruptedException {
		WebElement slidebar = BaseTest.BaseTest.driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-thumb') and @data-index='0']//input"));
		System.out.println(slidebar.getSize().getWidth());
		Actions a = new Actions(BaseTest.BaseTest.driver);
		a.clickAndHold(slidebar).moveByOffset(94, 0).release().perform();
	}
	
	public void updatetextfieldimp(int value) throws InterruptedException {
		WebElement textfield = BaseTest.BaseTest.driver.findElement(By.xpath("//input[@id=':R57alklff9da:']"));
		WebElement RevenueCalculator = BaseTest.BaseTest.driver.findElement(By.xpath("//h5[text()='Total Gross Revenue Per Year']"));
		JavascriptExecutor js = (JavascriptExecutor)  BaseTest.BaseTest.driver;
		js.executeScript("arguments[0].scrollIntoView(true);", RevenueCalculator);
		textfield.clear();
		js.executeScript("arguments[0].value = arguments[1];", textfield, value);
		Thread.sleep(2000);
		String expected_ariavaluenow = "823";
		String Actual_ariavaluenow = BaseTest.BaseTest.driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-thumb') and @data-index='0']//input")).getAttribute("aria-valuenow");
		if(Actual_ariavaluenow.equals(expected_ariavaluenow)) {
			BaseTest.BaseTest.logger.info("value set correctly on slider");
			System.out.println("value set correctly on slider");
		}else {
			BaseTest.BaseTest.logger.info("value not set correctly on slider");
			System.out.println("value not set correctly on slider");
		}			
	}
	
	public void selectCPTValue() throws InterruptedException {
		WebElement CPT99091 = BaseTest.BaseTest.driver.findElement(By.xpath("//p[text()='CPT-99091']/..//input"));
		WebElement CPT99453 = BaseTest.BaseTest.driver.findElement(By.xpath("//p[text()='CPT-99453']/..//input"));
		WebElement CPT99454 = BaseTest.BaseTest.driver.findElement(By.xpath("//p[text()='CPT-99454']/..//input"));
		WebElement CPT99474 = BaseTest.BaseTest.driver.findElement(By.xpath("//p[text()='CPT-99474']/..//input"));
		try {
			CPT99091.click();
			CPT99453.click();
			CPT99454.click();
			CPT99474.click();
			BaseTest.BaseTest.logger.info("checboex checked");
		} catch (Exception e) {
			throw new RuntimeException("checkbox is ot interactable");
		}		
	}
	
	public void validatetextimp() {
		WebElement text=BaseTest.BaseTest.driver.findElement(By.xpath("//p[text()='Total Recurring Reimbursement for all Patients Per Month:']"));
		String ExpectedText = "Total Recurring Reimbursement for all Patients Per Month";
		String ActualText = text.getText();
		String Actualm= ActualText.substring(0, ActualText.indexOf(":"));
		
		Assert.assertEquals(ExpectedText, Actualm);
		BaseTest.BaseTest.logger.info("Text of total recuurring rembursement validate successfully");
	}
	
	public void validateTotalRecuurringRembursementValueImp() {
		WebElement s2 = BaseTest.BaseTest.driver.findElement(By.xpath("//p[contains(text(),'Total Recurring Reimbursement')]/following-sibling::p"));
		int expectedValue = 111105;
		String value = s2.getText();
		int actualvalue = Integer.valueOf(value.substring(1));
		
		Assert.assertEquals(expectedValue, actualvalue);
		if(expectedValue==actualvalue) {
			BaseTest.BaseTest.logger.info("Total Recuurring Rembursement Value is correct");
		}else {
			BaseTest.BaseTest.logger.info("Total Recuurring Rembursement Value is not correct");
		}
		
	}

}
