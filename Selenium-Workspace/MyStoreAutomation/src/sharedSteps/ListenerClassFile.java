package sharedSteps;
//@Guru99
//import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClassFile implements ITestListener
{

// When Test Case is started, this method is called.
public void onTestStart(ITestResult result) {
	// TODO Auto-generated method stub
	System.out.println("STARTED : "+result.getName());
}

	// When a Test Case passed, this method is called.
public void onTestSuccess(ITestResult result) {
	// TODO Auto-generated method stub
	System.out.println("PASSED : "+result.getName());
}

// When Test Case fails, this method is called
public void onTestFailure(ITestResult result) {
	// TODO Auto-generated method stub
	System.out.println("FAILED : "+result.getName());
}

// When Test Case is skipped, this method is called
public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub
	System.out.println("SKIPPED : "+result.getName());
}

public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	// TODO Auto-generated method stub
	
}

public void onStart(ITestContext context) {
	// TODO Auto-generated method stub
	
}

public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub
	
}
}
