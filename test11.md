# Design Document -- Group 006

## Table of Contents
- [Introduction](#introduction)
- [How to run our code](#how-to-run-our-code)
- [Functions](#functions)
- [Functionality](#functionality)
- [Design Decision](#design-decision)
- [Clean Architecture](#clean-architecture)
- [SOLID](#solid)
- [Packaging Strategy](#packaging-strategy)
- [Design Pattern](#design-pattern)
- [Use of Github Features](#use-of-github-features)
- [Code Style and Documentation](#code-style-and-documentation)
- [Testing](#testing)
- [Refactoring](#refactoring)
- [Progress Report](#progress-report)
  

## Introduction
We have adopted a more general platform suitable for any kind of interactive learning! Teachers can form study groups, create questions and form tests that can be assigned to study groups. Once a question/test is completed, it becomes the property of the teacher. In the future, there will be ways for teachers to profit from these creations. Students may use the website to join study groups and learn with other students with the benefit of having a teacher to provide personalized tests.


## How to run our code
1. Clone the repo to local
2. Add a new local tomcat configuration\
  2.1. Download tomcat 8 and add it to the application server\
  2.2. Change URL to http://localhost:8080/unnamed/LogInPage.html
3. Fix the artifact by choosing unnamed\
  3.1. If artifact lib doesn't have MySQL connector, download MySQL connector and add it to the external library.\
	3.2. Import MySQL connector into artifact lib.
5. Click the run button :D

### Common errors
* If the error message is related to JDBC, make sure you have an MYSQL connector in both libraries.
* If the web page shows 404 after login/register, use tomcat version 8. Do not use tomcat 10.
* If tomcat access is denied and you are using a mac, check if you downloaded .zip instead of the .tar for the MYSQL connector.
* If related to the java version, set JDK to amazon corretto version 11.


## Functions
* Group Study:\
&nbsp;&nbsp;&nbsp;&nbsp;Users identified as students can choose to study in groups where he/she can access the materials provided in specific groups, he/she will be assigned to a group and a teacher at a specific level. The website will provide a platform for the necessary connections between students and teachers.
* Tests:\
&nbsp;&nbsp;&nbsp;&nbsp;Users identified as students can access tests posted by teachers if they join groups or use auto-created quizzes as progress measurement if they chooses to study individually.
Users identified as teachers can upload tests and get auto-graded scores from the website after students finish the test.
Every test question will be able to have a specific mark for our website to calculate students' scores and return to teachers.
* User Files:\
&nbsp;&nbsp;&nbsp;&nbsp;The website will stores all he information online using the MySQL database and without using any local storage.
* Student Page:\
&nbsp;&nbsp;&nbsp;&nbsp;The website creates a webpage for each registered student. Student users can see all the groups they have joined and are able to join new groups on the page. After the student access to the group detail page, he/she will be able to access the tests created by teachers.
* Teacher Page:\
&nbsp;&nbsp;&nbsp;&nbsp;The website creates a webpage for every teacher user. Teacher users can see all the groups they have and are able to delete students from groups. They can also access their tests on the page by pressing the tests button. Creating tests is also allowed to be accessed in the Teacher page.



## Functionality
* For now, our program can do most parts said by our specification such as creating groups and tests. We even implemented an online database and web ui to our program. However, since this is our first time coding a web program, we have faced many difficulties along the way. Our front end and back end code can work perfectly seperately, but we want to challenge ourselves more by connecting them together. This process created lots of issues and we have tried our best to overcome them. 
* Our program is able to store and load states through accessing an online database. All the information is organized and stored remotely rather than locally. All states of the users will be saved, for example, if a teacher creates a question, it will be permanently stored in the database unless they decide to delete it. This is not affected by time and location since everything is stored real-time.
* We think we are ambitious enough for our group size. We unfortunately lost one member along the way, and everyone immediately felt the weight added to us. We are challenging ourselves by doing web developing and online databases since these two are completely new to all the group members. We are also planning to upgrade from localhost to online hosting in the future, which is another big challenge for us.


## Design Decision
### Front End
&nbsp;&nbsp;&nbsp;&nbsp;We designed to let our users respond in the front end, and then the data is sent to the database. After processing functions, the response data will be sent back to users. We designed to ensure that our website can hold enough users at the same time, which avoids that when several users are using at the same time, the program will be confused which to process.  

&nbsp;&nbsp;&nbsp;&nbsp;Universal Design:
* Equitable Use:\
&nbsp;&nbsp;&nbsp;&nbsp;A login page with a large logo and obvious instruct is friendly to people with visual disabilities
* Tolerance of error:\
&nbsp;&nbsp;&nbsp;&nbsp;Pop up warning for important actions: To avoid some actions by mistakes, our website allows double-checking for users in the important moves such as deleting students from the group and quitting from the group. 
* Simple and Intuitive Use:\
&nbsp;&nbsp;&nbsp;&nbsp;Different clarified buttons control specific functionality, all buttons have distinct functionalities, all buttons are labelled with clear meaning about what the button leads to, which helps users to quickly identify where they are toward.  
* Low Physical Effort:\
&nbsp;&nbsp;&nbsp;&nbsp;It does not require repeatable actions to use the website. There are no functionalities that require multiple taps to be accessed  
* Perceptible Information:\
&nbsp;&nbsp;&nbsp;&nbsp;clear instruction to functions with concise and precise descriptions. For example, the login page only has two buttons, the login and the register buttons. 

### BackEnd

TODO

## Clean Architecture
* Layers:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* UML Diagram:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO



## SOLID
* Single responsibility principle(SRP):\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Open/closed principle(OCP):\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Liskov substitution principle(LSP):\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Interface segregation principle(ISP):\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Dependency inversion principle(DIP):\
&nbsp;&nbsp;&nbsp;&nbsp;TODO


## Packaging Strategy
TODO

## Design Pattern
* Dependency Injection:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Strategy:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO


## Use of Github Features
* Commit:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Pull Request:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Issue:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Action:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO

## Code Style and Documentation
TODO

## Testing
TODO


## Refactoring
* Replace magic number with symbolic constant:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Extract methods:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Inline Temp:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Extract Variable:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Rename Method:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Extract Interface:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO


## Progress Report
* Open Question:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Worked Well:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Member Progress:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Improvments From Phase 1:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Phase 2 Conclusion:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
