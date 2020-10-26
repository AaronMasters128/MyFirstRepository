*** Settings ***

Documentation  Stores a list of the resource files
#Default Tags	critical

Resource	../keywords/Settings.robot
Resource	../keywords/General.robot


Library		../Libraries/querycsv.py
#Library     openpyxl
#Library     openpyxl.reader.excel
#Library     ExcelLibrary
#Library     AngularJS
#Library     OperatingSystem
Library     DatabaseLibrary
#Library     PyPyODBC
#Library     ExcelRobot
Library     Selenium2Library

