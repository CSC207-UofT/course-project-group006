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
Our website is an educational website dedicated to creating an online study space for students. This platform is suitable for any kind of interactive learning! Teachers can form study groups, create questions and form tests that can be assigned to study groups. Once a question/test is designed, it becomes the property of the teacher. In the future, there will be ways for teachers to profit from these creations. Students may use the website to join study groups and learn with other students with the benefit of having a teacher to provide personalized tests. They will receive marks/feedback from the teacher once their answers are graded.

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
* Student Page:\
&nbsp;&nbsp;&nbsp;&nbsp;The website creates a webpage for each registered student. Student users can see all the groups they have joined and are able to join new groups on the page. They can access their study groups by entering the group detail page.
* Teacher Page:\
&nbsp;&nbsp;&nbsp;&nbsp;The website creates a webpage for every teacher user. They can create tests as well as access all their study groups through the group detail page.
* Tests:\
&nbsp;&nbsp;&nbsp;&nbsp;Students can access tests posted by teachers. Teachers can create tests and add questions, answers, and marks to each test. Teachers are only able to see their own tests.
* Group Study:\
&nbsp;&nbsp;&nbsp;&nbsp;Users identified as students can choose to study in any group where they can access the materials such as student lists, announcements and tests. They are also able to see their mark once the teacher finishes grading. Users identified as teachers can see all students' answers and grade the test.
* User Files:\
&nbsp;&nbsp;&nbsp;&nbsp;The website will store all the information online using the MySQL database without using any local storage.

## Functionality
* Our program fulfills the specifications in terms of study groups and tests.
* Our program can store and load states through accessing an online database. All the information is organized and stored remotely rather than locally on each user's computer. All states of the users can be saved, for example, if a teacher creates a study group, it will be permanently stored in the database unless they decide to delete it. This is not affected by time and location since everything is stored real-time.
* We think we are ambitious enough for our group size. None of us have any experience in developing a website or MySQL, but we managed to learn and use both of them in our project within a relatively short period. It was also very challenging for us to build a structure that adhered to clean architecture because the structure of a website is very different from the structure taught in class. We are happy that we were able to expand and build on top of the lecture materials.


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
![uml](https://user-images.githubusercontent.com/90296783/145091064-d58bd9eb-88b7-471c-9541-ceb35b70e3a8.png)\
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
* In Future:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
