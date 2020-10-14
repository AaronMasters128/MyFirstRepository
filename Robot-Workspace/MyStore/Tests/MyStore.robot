*** Settings ***
Documentation    Suite description
Resource    ../Resources/Resources.robot

*** Test Cases ***
Scenario 1 - My First Test
    [Tags]    DEBUG
    Open Mystore In Browser

*** Keywords ***
Provided precondition
    Setup system under test