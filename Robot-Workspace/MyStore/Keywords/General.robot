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

