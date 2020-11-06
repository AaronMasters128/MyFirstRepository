*** Settings ***
Documentation    Suite description

Suite Setup       connect to database using custom params  dbapiModuleName=pyodbc  db_connect_string='Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:/Users/aaron.masters/Aarons-Workspaces/Robot-Workspace/MyStore/Data/MyStoreRobot.accdb'
Suite Teardown    Disconnect From Database

Resource    ../Resources/Resources.robot

*** Test Cases ***


001 - Click on Sign In Screen
    Click on item with class [login]

002 - Sign Into MyStore
    I login to MyStore as [aaron_masters_128@yahoo.com] with password [Automation123] and name [Aaron Masters]

003a - Manually Search for item - Blouse
    I search for the item [blouse]

003b - Search Method 1 - Check each search item appears individually
    Run keyword and continue on failure   I confirm my item named [Blouse] appears in the Search Results
    Run keyword and continue on failure   I confirm my item Described as [Short sleeved blouse with feminine draped sleeve detail.] appears in the Search Results
    Run keyword and continue on failure   I confirm my item priced at [$27.00] appears in the Search Results

003c - Search Method 2 - Check each search item appears within the same combined Xpath statement.
    I confirm an item named [Blouse] described as [Short sleeved blouse with feminine draped sleeve detail.] and costing [$27.00] appears in the Search Results

004 - Search from the DB
    I search for the item from the DB

005 - Search using the CSV file
    I search for the item from the CSV