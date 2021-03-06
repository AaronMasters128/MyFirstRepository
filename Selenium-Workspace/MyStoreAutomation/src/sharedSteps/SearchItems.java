
package sharedSteps;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;

public class SearchItems extends Browser
{
	
	public static String searchItemName;
	public static String expectedSearchResultPrice;
	
	public static void searchForItem()
	{
		// Get locations of Search elements
		WebElement searchField = driver.findElement(By.name("search_query"));
		WebElement searchButton = driver.findElement(By.name("submit_search"));
		
		// Delete the contents of the Search field before performing a new search
		searchField.sendKeys("\u0001");
		// Enter the Search criteria
		searchField.sendKeys(searchItemName);

		// Enter username and password then click Login or Submit Login
	
		Action SearchForItem = builder.moveToElement(searchButton)
			.click()
			.build();
		SearchForItem.perform();
	}
	public static void simpleCheckSearchResults()
	{
		// Confirm correct results appear based on price
		WebElement results = driver.findElement(By.xpath("//*[@class='right-block']//*[@itemprop='price']"));
		String resultPrice = results.getText();
		System.out.println("resultPrice : "+resultPrice);
		System.out.println("expectedPrice : "+expectedSearchResultPrice);
		if(resultPrice.equals(expectedSearchResultPrice))
		{
			System.out.println("An item at the expected price was found");
		}
		else
		{
			System.out.println("The test did not find an item at the expected price");
		}
	}
	
	public static void advancedCheckSearchResults()
	{
		
		// Confirm correct results appear based on price
		WebElement results = driver.findElement(By.xpath("//*[@class='right-block']//*[@itemprop='price']"));
		String resultPrice = results.getText();
		System.out.println("resultPrice : "+resultPrice);
		System.out.println("expectedPrice : "+expectedSearchResultPrice);
		if(resultPrice.equals(expectedSearchResultPrice))
		{
			
			System.out.println("An item at the expected price was found");
		}
		else
		{
			System.out.println("The test did not find an item at the expected price");
		}
	}

}

