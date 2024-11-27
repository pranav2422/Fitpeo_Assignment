package TestCases;

import org.testng.annotations.Test;

import PageEvent.CalculateRevenuePage;


public class validateRevenueCalculator extends BaseTest.BaseTest {
	CalculateRevenuePage c = new CalculateRevenuePage();
	
	@Test(priority=1)
	public void nevigateToHomePage() {
		c.nevigateToHomePageMethod();
	}
	
	@Test(priority=2)
	public void nevigateToRevenueCalculatorPage() {
		c.nevigateToRevenueCalculatorPageimp();
	}
	
	@Test(priority=3)
	public void scrolldownPageTillCalculator() {
		c.scrolldownPageTillCalculatorimp();
	}
	
	@Test(priority=4)
	public void adjustslidebar() throws Exception {
		c.adjustslidebarimp();
	}
	
	@Test(priority=5)
	public void updatetextfield() throws InterruptedException {
		c.updatetextfieldimp(560);
	}
	
	@Test(priority=6)
	public void checktheboxes() throws InterruptedException {
		c.selectCPTValue();
	}
	
	@Test(priority=7)
	public void validateText() {
		c.validatetextimp();
	}
	
	@Test(priority=8)
	public void validateTotalRecuurringRembursementValue() {
		c.validateTotalRecuurringRembursementValueImp();
	}

}
