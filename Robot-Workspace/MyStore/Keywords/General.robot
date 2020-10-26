*** Settings ***



Resource	../Resources/Resources.robot

*** Keywords ***
Open Mystore In Browser
	Open Browser  ${MyStoreURL}  ${BROWSER}  remote_url=${REMOTEURL}  ff_profile_dir=${FIREFOXPROFILE}  desired_capabilities=${CAPABILITIES}
	Maximize Browser Window
	Set Selenium Speed  ${DELAY}
	Page Should Contain Element  //*[@src='http://automationpractice.com/img/logo.jpg']

Click on item with class [${Class}]
    Click Element   //*[@class='${Class}']

I login to MyStore as [${USERNAME}] with password [${PASSWORD}] and name [${NameOfUser}]
	Input Text  //input[@id='email']  ${USERNAME}
	Input Text  //input[@id='passwd']  ${PASSWORD}
	Click Element  //button[@id='SubmitLogin']
	Page should contain element     //*[@title='View my customer account']/span[contains(., '${NameOfUser}')]

I search for the item [${SearchItem}]
    Input Text  //input[@id='search_query_top']  ${SearchItem}
    Click Element   //button[@name='submit_search']

I search for the item from the DB
    # Get the required data from the db. For this loop, we are only interested in the 7 search items in column 1.
    # This query tool doesn't seem to allow me to just acquire the single column. If I find a better tool, I will change it.
    @{SearchItems} =  Query  SELECT * from MyStoreSearch
    # Log the results of the data search for reference
    Log Many    @{SearchItems}
    Set Test Variable   ${count}  0
    # Columns 14 to 20 in the DB define whether or not each item appears in each search result.
    # As it is 0 based, this variable tells the test to start with the 14th column
    Set Test Variable   ${FoundInSearch}    13
    FOR  ${i}  IN  @{SearchItems}
       # Logging the search item from column 1 for reference
       # This query tool doesn't allow me to enter the column title, only the column number. If I find a better tool, I will change it.
       Log  ${SearchItems[${count}][0]}
       Input Text  //input[@id='search_query_top']  ${SearchItems[${count}][0]}
       Click Element   //button[@name='submit_search']

        Confirm whether each item appears in the search result

       # Increases the loop count by 1
       ${newcount} =    Evaluate    int(${count})+1
       Set Test Variable   ${count}    ${newcount}
       # Increases the Results column by 1
       ${newFoundInSearch} =    Evaluate    int(${FoundInSearch})+1
       Set Test Variable   ${FoundInSearch}     ${newFoundInSearch}
    END

Confirm whether each item appears in the search result
    # Acquire the full list of items from the db, oncluding whether they are due to appear in each search result.
    @{Search2Items} =  Query  SELECT * from MyStoreSearch
    Log Many    @{Search2Items}
    Set Test Variable   ${count2}  0
    Log     ${FoundInSearch}
        # For each search result, it will loop through each item in the DB. There are only 7 total results on this test web site.
        FOR  ${i2}   IN  @{Search2Items}
            Set Test Variable   ${Search2Items}
            Log  ${Search2Items[${count2}][${FoundInSearch}]}
            # Each item in the DB states whether or not it should appear in each search.
            # This If Statement only runs if it is expecting to find the item.
            Run Keyword If  '${Search2Items[${count2}][${FoundInSearch}]}' == 'Yes'
    ...         Find Elements In Search
            # This If statement confirms that the item doesn't appear in the search results.
            Run Keyword If  '${Search2Items[${count2}][${FoundInSearch}]}' == 'No'
    ...         Do Not Find Elements In Search

            ${newcount2} =    Evaluate    int(${count2})+1
            Set Test Variable   ${count2}    ${newcount2}
       END

Find Elements In Search
    # This confirms that the required item name appears with the correct description and price.
    # The Xpath is configured so that it checks the description and price appears against the same item, as opposed to within the search results.
    # If item is on sale, check against sale price
    Run Keyword If  '${Search2Items[${count2}][7]}' == 'Yes'
    ...     Run keyword and continue on failure     I confirm an item named [${Search2Items[${count2}][3]}] described as [${Search2Items[${count2}][4]}] and is on sale at [${Search2Items[${count2}][8]}] originally costing [${Search2Items[${count2}][6]}] appears in the Search Results
    # If item is not on sale, check against normal price.
    Run Keyword If  '${Search2Items[${count2}][7]}' == 'No'
    ...     Run keyword and continue on failure     I confirm an item named [${Search2Items[${count2}][3]}] described as [${Search2Items[${count2}][4]}] and costing [${Search2Items[${count2}][6]}] appears in the Search Results


Do Not Find Elements In Search
    # As there are multiple items with the same name, it cannot confirm that the item name does not appear as a different item with the same name may do so.
#    Run keyword and continue on failure     I confirm my item named [${Search2Items[${count2}][3]}] does not appear in the Search Results
    Run keyword and continue on failure     I confirm my item Described as [${Search2Items[${count2}][4]}] does not appear in the Search Results
    Run keyword and continue on failure     I confirm my item priced at [${Search2Items[${count2}][6]}] does not appear in the Search Results

I search for the item from the CSV
    @{SearchItems} =  GetData  SELECT * from MyStoreRobot
    Log Many    @{SearchItems}
    Set Test Variable   ${count}  0

    FOR  ${i}  IN  @{SearchItems}
       Log  ${SearchItems[${count}]['Search']}
       Input Text  //input[@id='search_query_top']  ${SearchItems[${count}]['Search']}


       Click Element   //button[@name='submit_search']
       ${newcount} =    Evaluate    int(${count})+1
       Set Test Variable   ${count}    ${newcount}
    END

I confirm my item named [${ItemName}] appears in the Search Results
    Page should contain element  //*[contains(@class, 'product_list')]//*[@class='product-name' and contains(., '${ItemName}')]

I confirm my item Described as [${ItemDescription}] appears in the Search Results
    Page should contain element  //*[contains(@class, 'product_list')]//*[@class='product-desc' and contains(., '${ItemDescription}')]

I confirm my item priced at [${ItemPrice}] appears in the Search Results
    Page should contain element  //*[contains(@class, 'product_list')]//*[@class='price product-price' and contains(., '${ItemPrice}')]

#Item is not on sale
I confirm an item named [${ItemName}] described as [${ItemDescription}] and costing [${ItemPrice}] appears in the Search Results
    Page should contain element     //*[contains(@class, 'product_list')]//*[@class='product-name' and contains(., '${ItemName}')]//ancestor::*[(@class='right-block')]//*[@class='product-desc' and contains(., "${ItemDescription}")]//ancestor::*[(@class='right-block')]//*[@class='price product-price' and contains(., '${ItemPrice}')]
# Item is on sale
I confirm an item named [${ItemName}] described as [${ItemDescription}] and is on sale at [${SalePrice}] originally costing [${ItemPrice}] appears in the Search Results
    Page should contain element     //*[contains(@class, 'product_list')]//*[@class='product-name' and contains(., '${ItemName}')]//ancestor::*[(@class='right-block')]//*[@class='product-desc' and contains(., "${ItemDescription}")]//ancestor::*[(@class='right-block')]//*[@class='price product-price' and contains(., '${SalePrice}')]//following-sibling::*[@class='old-price product-price' and contains(., '${ItemPrice}')]


I confirm my item named [${ItemName}] does not appear in the Search Results
    Page should not contain element  //*[contains(@class, 'product_list')]//*[@class='product-name' and contains(., '${ItemName}')]

I confirm my item Described as [${ItemDescription}] does not appear in the Search Results
    Page should not contain element  //*[contains(@class, 'product_list')]//*[@class='product-desc' and contains(., "${ItemDescription}")]

I confirm my item priced at [${ItemPrice}] does not appear in the Search Results
    Page should not contain element  //*[contains(@class, 'product_list')]//*[@class='price product-price' and contains(., '${ItemPrice}')]
