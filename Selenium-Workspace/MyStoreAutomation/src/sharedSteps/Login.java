package sharedSteps;

import org.openqa.selenium.*;
import java.util.List;

public class Login extends Browser
{
		
		
	// Set Login Credentials
		public static String EmailAddress;
		public static String Password;
		public static String NameOfUser;
		
			public static void navigateToSignIn()
			{
				// Navigate to the Sign In screen by clicking on the Sign In button
				WebElement SignInScreen = driver.findElement(By.cssSelector("a.login"));
				builder.moveToElement(SignInScreen)
					.click()
					.build()
					.perform();				
			}
			
						
			public static void signInAsCorrectUserIfNotAlready()
			{
				// Check if Sign Out Element exists
				// Check if current user is required user
				
				List<WebElement> SignedIn = driver.findElements(By.cssSelector("a.logout"));	
				int elementExists = SignedIn.size();
				if(elementExists!=0)
				{
					String currentUser = driver.findElement(By.xpath("//*[@class='account']/span")).getText();
					System.out.println("I am logged in as : "+currentUser); 
					if(currentUser.contentEquals(NameOfUser))
					{
						System.out.println("Already logged in as required user");
					}
					else
					{
						System.out.println("Not logged in as required user - Logging out");
						signOut();
						// Navigate to Sign In Screen
						Login.navigateToSignIn();
						// Sign In
						signIn();
					}
				}
				else
				{
					System.out.println("No one is currently signed in");
					// Navigate to Sign In Screen
					Login.navigateToSignIn();
					// Sign In
					signIn();
				}
			
			}
			
			public static void signOut()
			{
				// Navigate to the Sign In screen by clicking on the Sign In button
				WebElement SignOutButton = driver.findElement(By.cssSelector("a.logout"));
				builder.moveToElement(SignOutButton)
					.click()
					.build()
					.perform();		
				System.out.println("I am signing out");
			}
			
		
			public static void signIn()
			{
				// Get locations of Sign In elements
				WebElement username = driver.findElement(By.name("email"));
				WebElement password = driver.findElement(By.name("passwd"));
				WebElement SignInButton = driver.findElement(By.id("SubmitLogin"));

				// Enter username and password then click Login or Submit Login
				username.sendKeys(EmailAddress);
				password.sendKeys(Password);
				SignInButton.click();
				System.out.println("I have just signed in as : "+NameOfUser);
			}
}
