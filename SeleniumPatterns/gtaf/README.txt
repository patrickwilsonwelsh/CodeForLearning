		======
		README
		======
		
This software constitutes of the Gold Test Autmation Framework - gtaf. The goal
of gtaf is to provide a clean Object Oriented Design of Domain Specific Language
for easy and readable automation of products based on Gold platform. To meet
this end, gtaf makes use of Selenium as the underlying test automation tool and
FitNesse/Fit as the underlying test description and management interface. 

Release v0.0.1 - 08/06/2009

Folder Structure
================
gtaf ---+
	|
	|- build +
	|	 |- lib
	|	 |- classes
	|
	|- doc
	|- src +
	       |- framework
	       |- tests

/gtaf/build/lib - contains the JARs that gtaf is dependent on
/gtaf/build/classes - contains compiled class files
/gtaf/doc - cotains the complete java doc
/gtaf/src/framework - contains the framework java code
/gtaf/src/tests - contains JUnit tests that make use of the framework

Importing Project into Eclipse
==============================
1. File > Import and select General > Existing Projects into Workspace
2. Choose the gtaf root directory to import the complete project into workspace

Description of Demo Test
========================
com.qait.gold.tests.powersearch.search.advanced.SimpleAuthorSearchTestCase is a 
simple *JUnit* test that demonstrates the use of the gtaf framework. Note that 
gtaf can be driven through JUnit or FitNesse.

This test case opens the power search default page
Navigates to the advanced search page
Searches on Author for author William
Verifies that search results are returned
Opens the first result in detail view
Verifies that William appears in author field


Steps to run the Demo Test
==========================
Software needed to run this demo:
	- jre 1.6
	- Selenium RC
	- Firefox 

1. Start the Selenium RC Server on localhost and default port 4444. Ensure that 
the server starts successfully
2. Open a command prompt and navigate to the gtaf project root
3. Run the testSimpleAuthorSearch.bat (present in root folder)

Note - The test by default runs on *Firefox* browser and would require one on your 
system. The test host, port, browser etc will be configurable once integrated with 
FitNesse
