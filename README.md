# VechicleReg

This application tested on Windows both chrome and firefox browser, the default browser configured as chrome on Inputs.BROWSER_NAME.

Run The Test :
mvn clean test

The above command executes all test cases and result reports are generated on the Reports(Reports) file and logs(/Logs), screenshots(/ScreeShot) are also created on the corresponding folders. The screenshots are opened and verifed on paint.

This test is implemented using Java, Selenium, Cucumber, and JUnit frameworks.

FileReaderService scans directory src/main/resources/data and filter csv files. 
CSVDataReaderService reads csv files and also verifies CSV extensions.

TestRunner.java executes cucumber test cases.
VehicleInformation contains all the step definitions. 
