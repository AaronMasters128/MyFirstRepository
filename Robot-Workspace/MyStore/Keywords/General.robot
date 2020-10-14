*** Settings ***

Resource	../Resources/Resources.robot

*** Keywords ***
Open Mystore In Browser
	Open Browser  ${MyStoreURL}  ${BROWSER}  remote_url=${REMOTEURL}  ff_profile_dir=${FIREFOXPROFILE}  desired_capabilities=${CAPABILITIES}
	Maximize Browser Window
	Set Selenium Speed  ${DELAY}
	Page Should Contain Element  //*[@src='http://automationpractice.com/img/logo.jpg']


