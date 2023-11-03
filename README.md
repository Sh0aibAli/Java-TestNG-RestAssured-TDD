## Prerequisite:
Java,
Eclipse/IntelliJ IDE
TestNG
Maven

## Installation/ Setup
To run this project, you need to have java, maven, eclipse/IntelliJ and testng installed on your machine

## Run the test:

First method:-
1. mvn clean
2. mvn test

Second method:-
1. Import the project in eclipse or open the project in IntelliJ
2. Open testng.xml file present in root folder, right click > Run As > Testng Test

## Framework Description
This framework consist of
1. logs package: This package consist of log updates captured during the execution of the test cases  
    1. Apache POI, Apache POI - Java API To Access Microsoft Format Files (poi)  
    2. Apache POI API Based On OPC and OOXML Schemas » 5.2.3, Apache POI - Java API To Access Microsoft Format Files (poi-ooxml)
       Using these above two dependencies in POM.xml file, this framework is able to capture and store the log updates in txt files.

2. reports package
   1. Consist of extent reports    

3. src/test package: This package consist of two nested packages
    1. Java: It consist of a nested package
        1. api package: This package consist of four nested packages i.e.
            1. endpoints: This package consist of two java classes
                1. Routes: This java class is used to adjoin the base url with the api request
                2. UserEndPoints: This java class consist of methods to execute Rest-assured api requests

            2. payloads: This package consist of one java class
                1. User: This java class is used as a getter and setter for the user data

            3. test: This package consist of two java classes
                1. DDTests: This java class consists of Data Driven Test cases which is coming from an excel sheet i.e. testData/Userdata.xlsx
                   This class consist of two test cases to execute POST,DELETE api requests

                2. UserTests: This java class consist of Test cases for a specific user coming from github.javafaker.
                   This class consist of four test cases to execute GET,POST,PUT,DELETE api requests

            4. utilities: This package consist of three java classes
                1. DataProviders: The DataProviders in TestNG are another way to pass the parameters in the test function,
                   the other one being TestNG parameters. DataProviders pass different values to the TestNG Test Case in a single execution
                   and in the form of TestNG Annotations. The TestNG DataProvider (the annotation part) contains only one single attribute,
                   which is its name. It is always a string type in nature. TestNG dataprovider returns a 2d list of objects.

                2. ExtentReportManager: Extent Reports in Selenium contain two major, frequently used classes
                    1. ExtentReports class: The ExtentReports class generates HTML reports based on a path specified by the tester.
                       Based on the Boolean flag, the existing report has to be overwritten or a new report must be generated.
                       ‘True’ is the default value, meaning that all existing data will be overwritten.

                    2. ExtentTest class: The ExtentTest class logs test steps onto the previously generated HTML report.

                    3. Both classes can be used with the following built-in methods:
                        1. startTest: Executes preconditions of a test case
                        2. endTest: Executes postconditions of a test case
                        3. Log: Logs the status of each test step onto the HTML report being generated
                        4. Flush: Erases any previous data on a relevant report and creates a whole new report

                    4. A Test Status can be indicated by the following values:
                        1. PASS
                        2. FAIL
                        3. SKIP
                        4. INFO

                3. XLUtility: This java class is used to handle the excel file, it consists of methods like
                    1. getRowCount: To get the count of rows on the sheet
                    2. getCellCount: To get the count of column on the sheet
                    3. getCellData: To get the data of the specific cell present on the sheet
                    4. setCellData: To set the data on the specific cell present on the sheet
                    5. fillGreenColor: To mark a specific cell with Green color
                    6. fillRedColor: To mark a specific cell with Red color

    2. Resources: This directory consists of
        1. log4j2.xml: Log4j 2 is new and improved version of the Log4j logging framework.
           The most compelling improvement is the possibility of asynchronous logging.

        2. routines.properties: This file consist of api request parameters for GET,POST,PUT,DELETE requests

4. target: This directory consists of
    1. generated-test-sources/test-annotations
    2. test-classes: This directory consist of faker_json_schema.json and json_schema.json to validate the json schema for the responses

5. test-output: It consists of test reports

6. testData: It consist of Userdata.xlsx file for testing data

7. pom.xml: Consists of all the required dependencies used in this framework
