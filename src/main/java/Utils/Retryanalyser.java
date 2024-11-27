package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retryanalyser implements IRetryAnalyzer {
	
	int count =0;
	int retrycount = 1;

	public boolean retry(ITestResult result) {
		while(count<retrycount) {
			count++;
			return true;
		}
		return false;
	}

}
