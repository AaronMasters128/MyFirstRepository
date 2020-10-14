*** Settings ***
Documentation    Suite description
Resource    ../Resources/Resources.robot

*** Test Cases ***
Scenario 1 - Open Browser
    [Tags]    DEBUG
    Open Mystore In Browser

Scenario 2 - Click on Sign In Screen
    Click on item with class [login]

Scenario 3 - Sign Into MyStore
    I login to MyStore as [aaron_masters_128@yahoo.com] with password [Automation123] and name [Aaron Masters]

Scenario 4 - Search for item - Blouse
    I search for the item [blouse]