*** Settings ***
#Library		Selenium2Library	timeout=45 s
#Library		SeleniumLibrary	timeout=45 s
#Library     BuiltIn
#Library		String
#Library 	Collections



*** Variables ***

#${ENVIRONMENT}  localhost



${MyStoreURL}  http://automationpractice.com/

  
${BROWSER}			chrome
#${BROWSER}			IE

${DELAY}			0.5
${FIREFOXPROFILE}	
${REMOTEURL}		${False}
${CAPABILITIES}		${NONE}

${DownloadDir}  C:/Users/aaron.masters/Downloads/

${BLANK}
${SPACE}  concat(' ')	
#@{VTMONTHS}  NULL  January  February  March  April  May  June  July  August  September  October  November  December
#@{MONTHS}  NULL  JANUARY  FEBRUARY  MARCH  APRIL  MAY  JUNE  JULY  AUGUST  SEPTEMBER  OCTOBER  NOVEMBER  DECEMBER

${VISIBLE}	not(ancestor::*[contains(@style,'display:none')]) and not(ancestor::*[contains(@style,'display: none')])
${NOTVISIBLE}	(ancestor::*[contains(@style,'display:none')] or ancestor::*[contains(@style,'display: none')] or contains(@style,'display:none') or contains(@style,'display: none'))
${CONTEXTAREA}


@{3CharacterMONTHS}  NULL  Jan  Feb  Mar  Apr  May  Jun  Jul  Aug  Sep  Oct  Nov  Dec

	