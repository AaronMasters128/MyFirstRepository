package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import SharedSteps.Browser;
import SharedSteps.Login;

public class SearchMyStore
{
	@Test (priority=0)
	public static void LogIntoMyStore() 
	{
		// Open Browser to required URL
		Browser.getBrowserChrome();
		Browser.openUrl();
	
		// Details of Log In User
		Login myUser = new Login();	
			myUser.EmailAddress="aaron_masters_128@yahoo.com";
			myUser.Password="Automation123";
			myUser.NameOfUser="Aaron Masters";	 

		
		// Check if no user or incorrect user is logged in.
		// Log out if incorrect user
		// Log in as correct user
		Login.signInAsCorrectUserIfNotAlready(); 
	
	}	
	
	@Test (priority=1)
	public static void performASearch()
	{
		System.out.println("I still need to create this test");
	}


	  

}