# mmcrawl
A Simple Web Crawler Implementation

## Description
Given a starting URL (i.e. https://wiprodigital.com) the application will visit all pages within this domain, but will not follow links to external sites (i.e. Google/Twitter).

I have created my solution using the Eclipse (Mars) IDE in conjunction with 
version 8 (u65) of the Oracle JDK and the Mars built in m2eclipse plugin
for JUnit 4.12.0.

Follow the below instructions for installing the project and running the tests.

## Prerequisites
You will need to install the following inorder to work with the project:

	Oracle JDK 8 (update 65 or above) - see http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
	Eclipse 4.5 (Mars) - see https://wiki.eclipse.org/Eclipse/Installation
  
## Project Installation

Once the prerequisite software above has been installed you should be able to import the given project into the
Eclipse IDE, steps to follow are:

1. Download project archive / zip file into your workspace working area. 

    This should give you the project directory structure mmcrawl/src/...

2. Import project into eclipse using menu option
	
	File > Import > Maven > Existing maven projects

    Select directory containing project pom.xml file as the Root directory during import.

## Building Project

You can build the sources for the project by using by right clicking on the 'mmcrawl' project
in the eclipse project explorer and from the context menu selecting 

	Run As > Maven clean
	Run As > Maven install

## Running Unit Tests

I have created a TestSuite 'AllTests' which you can use to run the full suite of unit tests I have created.
To run the suite Right Click on the directory/Class src\test\java\com\wiprodigital\mmcrawl\AllTests.java
and from the context menu select menu item

	Run As > JUnit Test

## Running The Application

The application takes a single parameter , specifying the name of the starting URL as input:

i.e. 
	java -classpath ... com.wiprodigital.mmcrawl.AppJava https://wiprodigital.com

## Trades Off / Future Development

### Reporting of results
The current report produced by the crawler produces a very simple text listing of the sites visited and key attributes 
(i.e. are they internal vs external). Given more time I would extend this to produce a more standard xml sitemap / report.

### HTML Link Recognition
Due to limited time I have not implemented a solution for the detection of all external means of defining links
to resource supported by the HTML standard. Images source detection is currently incomplete. More work is required 
on the JSoup image src detection criteria, currently not included.

### Robot.txt File Support
The current implementation does not process site "robot.txt" information. The application needs to be extended
to support the reading of this file to support the correct security/access granted by site owners.

### Crawl Depth
The current application does not limit the number of levels of site traversal made for a particular site.
This means that given an extremely large site this process could take several minutes to run.
An improvement would be to allow the depth to be submitted as a runtime parameter the application in future.


