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
- [Accessibility Report](#accessibility-report)
  

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
* Storing Information:\
&nbsp;&nbsp;&nbsp;&nbsp;When storing information in the database, we implemented the serializable pattern so that we could store complex objects such as a hashmap as a string. This allows us to minimize the number of tables within the database and simplify the model structures.\
&nbsp;&nbsp;&nbsp;&nbsp;We chose MySQL for the database because it is very light to use, and there are plenty of free hosting resources online compared to others.
* Structure:\
&nbsp;&nbsp;&nbsp;&nbsp;After phase 1, we realized that we can't just have commands in the backend, so we study the provided feedback and upgraded our structure to MVC.
* Interfaces:\
&nbsp;&nbsp;&nbsp;&nbsp;This is the most important design decision we made for the project. By using an interface to act as a bridge between gateways and use cases, it perfectly meets the requirements for clean architecture while fulfilling our needs.\

## Clean Architecture
* UML Diagram:\
![uml](https://user-images.githubusercontent.com/90296783/145091064-d58bd9eb-88b7-471c-9541-ceb35b70e3a8.png)\
&nbsp;&nbsp;&nbsp;&nbsp;Our code follows clean architecture. Referring to the UML diagram, we have all the layers. For the innermost layer, we create different types of entities, such as Users, Groups and Tests, to encapsulate the information to an object and simplify further programming. For use cases, we write different managers to achieve the actions or commands of the corresponding entity. For the Interface Adapters, we design several gateways to connect the database. We also build multiple servlet classes to work as controllers that communicate between GUI and use case classes. For the outermost layer, frameworks and drivers, we build a website as our user interface by using html and jsp. We also use MySQL as our online database to store all the user information.\
&nbsp;&nbsp;&nbsp;&nbsp;Here is a simplified code walk-through for joining a group to demonstrate clean architecture:\
&nbsp;&nbsp;&nbsp;&nbsp;1. UI gets and sends information to Servlet\
&nbsp;&nbsp;&nbsp;&nbsp;2. Servlet receives the information, creates a gateway as an interface, and send the interface and the information to manager\
&nbsp;&nbsp;&nbsp;&nbsp;3. Manager receives the information and creates a group entity using the data read in by the interface\
&nbsp;&nbsp;&nbsp;&nbsp;4. Perform the joining action onto the group entity\
&nbsp;&nbsp;&nbsp;&nbsp;5. Manager uses the altered group entity and the interface to rewrite data\
&nbsp;&nbsp;&nbsp;&nbsp;6. Servlet, UI continues performing the rest of the method

&nbsp;&nbsp;&nbsp;&nbsp;To conclude, every piece of our code follows the dependency rule. Throughout our project, the outer layers depend on inner layers and don’t have a cross-layer dependency. By implementing the rules of Clean Architecture, we keep our project organized and simple in terms of the code structure, it helps us to debug and increases readability for others.



## SOLID
* Single responsibility principle(SRP):\
&nbsp;&nbsp;&nbsp;&nbsp;In short, each class relates to one functionality and each page in our website has its own corresponding servlet. For example, the groupgateway is only responsible for read/write group data and it will not have access to other data.
* Open/closed principle(OCP):\
&nbsp;&nbsp;&nbsp;&nbsp; We implement the strategy design pattern. By using this design pattern, our gateways are open for extensions, which means we could add more things into it and close for modification. For example, if we want to add\delete a column in the Group table, we only need to change the group gateway class. Adding tables is also easy to achieve since we just need to create a new gateway class for the new table and override the read and write methods. None of the above action effect other parts of the code.
* Liskov substitution principle(LSP):\
&nbsp;&nbsp;&nbsp;&nbsp;We have an interface called GeneralReadWriter, which is responsible for reading and writing information into the database. It has multiple children such as ReadNameID or ReadAll that perform some other extra actions. If we replace an object that implemented GeneralReadWriter with any of its children, it will not break the program since all the methods are overridden.
* Interface segregation principle(ISP):\
&nbsp;&nbsp;&nbsp;&nbsp;Some gateway classes don't need a method that uses a name to find an id but some others do. Hence, we created another interface that specifically has this method and only let the gateways that need this method implement it. By doing so, we don't need to put this method in all gateway classes so those who don’t need this method will not need to override it. This avoids implementing extra methods.
* Dependency inversion principle(DIP):\
&nbsp;&nbsp;&nbsp;&nbsp;We used the dependency inversion design pattern for accessing the gateway feature in the use case classes through an interface. Hence, they both depend on the interface and adhere to clean architecture. For example, group manager imports the interface GeneralReadWriter instead of importing the actual gateway itself, even though the manager needs the help of the gateway to access the database.


## Packaging Strategy
TODO

## Design Pattern
* Dependency Injection:\
&nbsp;&nbsp;&nbsp;&nbsp;This design pattern is a technique that makes a class independent of its dependencies. It helps us to follow the dependency inversion principle and single responsibility in solid. We introduce interfaces to break the dependencies between higher and lower level classes so that both classes depend on the interface and no longer on each other. In our code, we create an interface called GeneralReadWrite, which is the interface that all of our gateways implement. For the use cases, all of our managers import the GeneralReadWrite interface. In this case, both the use cases and gateways depend on the interface instead of each other. 
* Strategy:\
&nbsp;&nbsp;&nbsp;&nbsp;The strategy design pattern allows us to do specific things in different ways. We encapsulate an interface as a base and then bury implementation details in derived classes. In our project, we build our gateways by implementing the interface called GeneralReadWrite. We make different gateways like Teacher Gateway, Student Gateway, for each table in the database. These gateways all have the same thing to do, which is to read/write but each corresponding table has a unique structure. Hence, by implementing the interface, we can write their getter and setter methods accordingly. By doing so, we avoid writing long if-else statements that are required to distinguish tables. For example, without this pattern, we will need to first get a parameter telling us which table we are looking at, and then read/write the table. This will create long methods and significantly decrease readability. Instead of setting the table type as a parameter, we could dynamically tell the code which read/write method we want to use on the spot. By using this design pattern, we also satisfied the open/ close principle. In the future development, we could add more different gateways without changing the older gateways. And the old ones are closed for modification.


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
* In Future:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO

## Accessibility Report
Universal Design:
* Equitable Use:\
&nbsp;&nbsp;&nbsp;&nbsp;The login page we have has large texts and obvious instructions, which is friendly to people with visual disabilities.
* Tolerance of error:\
&nbsp;&nbsp;&nbsp;&nbsp;Pop up warning for important actions: To avoid some actions by mistakes, our website allows double-checking for users in the important moves such as deleting students from the group and quitting from the group.
* Simple and Intuitive Use:\
&nbsp;&nbsp;&nbsp;&nbsp;Different clarified buttons control specific functionality, all buttons have distinct functionalities and are labelled with clear meaning indicating what the button leads to. This helps users to quickly identify where they are toward. 
* Low Physical Effort:\
&nbsp;&nbsp;&nbsp;&nbsp;It does not require repeatable actions to use the website. There are no functionalities that require multiple taps to be accessed. For example, when doing a test, students can submit all the questions at once. 
* Perceptible Information:\
&nbsp;&nbsp;&nbsp;&nbsp;Clear instruction to functions with concise and precise descriptions. For example, the login page only has two buttons, the login and the register buttons.


Write a paragraph about who you would market your program towards, if you were to sell or license your program to customers. This could be a specific category such as "students" or more vague, such as "people who like games". Try to give a bit more detail along with the category.\
TODO

Write a paragraph about whether or not your program is less likely to be used by certain demographics. For example, a program that converts txt files to files that can be printed by a braille printer are less likely to be used by people who do not read braille.\
TODO
