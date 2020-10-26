*** Settings ***

Documentation  Tests the Trust Settings
#Default Tags	critical

Resource    ../Resources/Resources.robot
#Library  DatabaseLibrary

*** Keywords ***


*** Test Cases ***

#000-1: Connect To Database
#    connect to database using custom params  dbapiModuleName=pyodbc  db_connect_string='Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:/Users/aaron.masters/Aarons-Workspaces/Robot-Workspace/MyStore/Data/MyStoreRobot.accdb'
    #C:\\Users\\aaron.masters\\Aarons\-Workspaces\\Robot\-Workspace\\MyStore\\Data\\MyStoreRobot.accdb

#dbapiModuleName=pyodbc
#dbapiModuleName=psycopg2
Scenario 1 - Open Browser
    Open Mystore In Browser

