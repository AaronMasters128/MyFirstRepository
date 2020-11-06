package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import sharedSteps.Browser;
import sharedSteps.DatabaseCommands;
import sharedSteps.Login;
import sharedSteps.SearchItems;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.SQLException;

public class SearchMyStore
{
	// Open Browser and log into site.
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
	
	// Perform a simple search based on criteria within hard-coded within the test itself.
	@Test (priority=1)
	
	// I intend to bind this to a csv file containing different items and their expected values.
	// I will then create a loop to so that it searches for each item in the file.
	// It will also contain items that do not appear in the search. A field in the file will determine whether it should be found or not.
	// The test will perform different actions depending on whether the csv file states whether the item should exist.
	public static void performASearch()
	{
		
		SearchItems searchItem = new SearchItems();
		searchItem.searchItemName="Blouse";
		searchItem.expectedSearchResultPrice="$27.00";
		
		SearchItems.searchForItem();
		SearchItems.simpleCheckSearchResults();
		
		System.out.println("I have now performed a search for one item");
	}

	// Perform a search based on data in an MS-Access DB file.
	@Test (priority=2)
	public static void performASearchUsingTheDB() throws SQLException
	{
		
		System.out.println("I will now start testing against the Access DB");
		/**
		DatabaseCommands myStoreDB = new DatabaseCommands();
		myStoreDB.DBFileDir="//C:/Users/aaron.masters/Aarons-Workspaces/Selenium-Workspace/MyStoreAutomation/Data/";
		myStoreDB.DBFileName="MyStoreRobot.accdb";
		myStoreDB.DBTableName="MyStoreSearch";
		
		DatabaseCommands.SelectAllFromTheRequiredDB();
		*/
	
		
		// Specify location of MS Access file as variable dbFile
		String dbFile = "//C:/Users/aaron.masters/Aarons-Workspaces/Selenium-Workspace/MyStoreAutomation/Data/MyStoreRobot.accdb";
		
		// Query to execute
		String query = "select * from MyStoreSearch;";
				
		// Create connection to the DB
		Connection con = DriverManager.getConnection("jdbc:ucanaccess:"+dbFile+";memory=false");
		
		// Create statement Object
		Statement stmt = con.createStatement();
		
		// Execute the SQL query. Store results in ResultSet
		ResultSet rs = stmt.executeQuery(query);

		// While Loop to iterate through all data and print results
		
		int foundInSearchColumn = 13;
		
		while (rs.next())
		{
//-----------------------------
			// Confirm and display the name of the search string
			String SearchField = rs.getNString("Search");
			System.out.println("");
			System.out.println("I will search for "+SearchField);
			System.out.println("");

			// Confirm and display the column name determining whether the item should appear in the search
			ResultSetMetaData rsmd = rs.getMetaData();
			String columnName = rsmd.getColumnName(foundInSearchColumn);
			System.out.println("--------------");
			System.out.println("Checking against Column name = "+columnName);
			System.out.println("--------------");	
//--------------------------------
			
			// Perform a search for the item in the Search field
			SearchItems searchItem = new SearchItems();
			searchItem.searchItemName=SearchField;
			SearchItems.searchForItem();
//--------------------------------

			// For each search, loop through the 7 items in the DB, and confirm whether or not they appear in the results.
			
				// Query to execute
				String query2 = "select * from MyStoreSearch;";
			
				// Create statement Object
				Statement stmt2 = con.createStatement();
				
				// Execute the SQL query. Store results in ResultSet
				ResultSet rs2 = stmt2.executeQuery(query2);
				
				// While Loop to iterate through all data and print results
				while (rs2.next())
				{
					System.out.println("I will now check whether each item appears in the search results");
					
					String foundInSearch = rs2.getString(foundInSearchColumn);
					String TitleField = rs2.getNString("Title");
					String OnSaleField = rs2.getNString("OnSale");
					String DescriptionField = rs2.getNString("Product Description");
					String PriceField = rs2.getNString("Price");
					String SalePriceField = rs2.getNString("SalePrice");					

					System.out.println("Should item be found in search : "+foundInSearch);
					System.out.println("The item titled : "+TitleField);
					System.out.println("with the description : "+DescriptionField);
					
					if(foundInSearch.equals("Yes"))
					{
						if(OnSaleField.equals("Yes"))
						{
//							WebElement results = Browser.driver.findElement(By.xpath("//*[contains(@class, 'product_list')]//*[@class='product-name' and contains(., '"+TitleField+"')]//ancestor::*[(@class='right-block')]//*[@class='product-desc' and contains(., '"+DescriptionField+"')]//ancestor::*[(@class='right-block')]//*[@class='price product-price' and contains(., '"+SalePriceField+"')]//following-sibling::*[@class='old-price product-price' and contains(., '"+PriceField+"')]"));
//							String FoundSearchItem = results.getText();
//							System.out.println("Result : "+FoundSearchItem);
//							System.out.println("The item titled : "+TitleField);
//							System.out.println("with the description : "+DescriptionField);
							System.out.println("originally costing : "+PriceField);
							System.out.println("but is on sale at : "+SalePriceField);
							System.out.println("WAS found in the search results");
						}
						else
						{
//							WebElement results = Browser.driver.findElement(By.xpath("//*[contains(@class, 'product_list')]//*[@class='product-name' and contains(., '"+TitleField+"')]//ancestor::*[(@class='right-block')]//*[@class='product-desc' and contains(., '"+DescriptionField+"')]//ancestor::*[(@class='right-block')]//*[@class='price product-price' and contains(., '"+PriceField+"')]"));
//							String FoundSearchItem = results.getText();
//							System.out.println("Result : "+FoundSearchItem);
//							System.out.println("The item titled : "+TitleField);
//							System.out.println("with the description : "+DescriptionField);
							System.out.println("costing : "+PriceField);
							System.out.println("and is not on sale");
							System.out.println("WAS found in the search results");
						}						
					}
					else
					{
						System.out.println("Item should not be found");

//						System.out.println("The item titled : "+TitleField);
//						System.out.println("with the description : "+DescriptionField);
//						System.out.println("costing : "+PriceField);
//						System.out.println("and is not on sale");
//						System.out.println("WAS found in the search results");
						
						List<WebElement> results = Browser.driver.findElements(By.xpath("//*[contains(@class, 'product_list')]//*[@class='product-desc' and contains(., \""+DescriptionField+"\")]"));	
						int elementExists = results.size();
						if(elementExists!=0)
						{
							System.out.println("This item was correctly not found");
						}
						else
						{
							System.out.println("This item was incorrectly found");
						}
							
							
						
//						List<WebElement> results2 = Browser.driver.findElements(By.xpath("//*[contains(@class, 'product_list')]//*[@class='product-desc' and contains(., '"+DescriptionField+"')]"));
//						int FoundSearchItem = results.size();
//						System.out.println("Result : "+FoundSearchItem);
						//*[contains(@class, 'product_list')]//*[@class='product-desc' and contains(., '"+DescriptionField+"')]
					}
					

					

					
					//searchItem.expectedSearchResultPrice=PriceField;
				}
			foundInSearchColumn = foundInSearchColumn + 1;
			System.out.println("Found In Search now equals " +foundInSearchColumn);				
			
//			SearchItems.advancedCheckSearchResults();

			System.out.println("I have now performed a search for " +SearchField );
			
		}

	}
}