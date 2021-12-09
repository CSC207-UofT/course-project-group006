# Design Document -- Group 006

## Table of Contents
- [Specification](#specification)
  * [Introduction](#introduction)
  * [How to run our code](#how-to-run-our-code)
  * [Common errors](#common-errors)
  * [Functions](#functions)
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
  
## Specification
### Introduction
Our website is an educational website dedicated to creating an online study space for students. This platform is suitable for any kind of interactive learning! Teachers can form study groups, create questions and form tests that can be assigned to study groups. Once a question/test is designed, it becomes the property of the teacher. In the future, there will be ways for teachers to profit from these creations. Students may use the website to join study groups and learn with other students with the benefit of having a teacher to provide personalized tests. They will receive marks/feedback from the teacher once their answers are graded.

### How to run our code
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

### Functions
* Student Page:\
![Screen Shot 2021-12-08 at 12 26 42 PM](https://user-images.githubusercontent.com/90296783/145254954-4a75462b-6dfa-45d0-ac49-ee7c141a80e9.png)\
&nbsp;&nbsp;&nbsp;&nbsp;The website creates a webpage for each registered student. Student users can see all the groups they have joined and are able to join new groups on the page. They can access their study groups by entering the group detail page.

* Teacher Page:\
![image](https://user-images.githubusercontent.com/90296783/145256012-2cb198f1-c28b-4510-b943-17f9e7169989.png)\
&nbsp;&nbsp;&nbsp;&nbsp;The website creates a webpage for every teacher user. They can create tests as well as access all their study groups through the group detail page.

* Tests:\
![image](https://user-images.githubusercontent.com/90296783/145257365-04bce42d-55df-4d4e-b773-e9419f907bcd.png)\
![image](https://user-images.githubusercontent.com/90296783/145255926-d03fb062-69a7-4069-b041-50da1fce0a4f.png)\
&nbsp;&nbsp;&nbsp;&nbsp;Students can access tests posted by teachers. Teachers can create tests and add questions, answers, and marks to each test. Teachers are only able to see their own tests.

* Group Study:\
![image](https://user-images.githubusercontent.com/90296783/145255692-f409a92e-a1fe-4972-afa7-d86863c39267.png)\
![image](https://user-images.githubusercontent.com/90296783/145256506-db138777-c11f-4185-ba57-3cb0b087ee36.png)\
&nbsp;&nbsp;&nbsp;&nbsp;Users identified as students can choose to study in any group where they can access the materials such as student lists, announcements and tests. They are also able to see their mark once the teacher finishes grading. Users identified as teachers can see all students' answers and grade the test.

* User Files:\
![image](https://user-images.githubusercontent.com/90296783/145258014-229e7258-cc1f-4ca8-b77d-051c152ecfe2.png)\
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
&nbsp;&nbsp;&nbsp;&nbsp;We organized our files by clean architecture layers. The IDE automatically created a web page folder that contains all the UI pages when the project is created. In the backend, java folder, there is a servlet(controller) folder, as well as a backend folder that contains a gateway, a manager, an entity and an interface folder.  By doing so, we can local the files quickly and significantly increase the readability of our code("Ini.java" and "IniTest.java" are only for debugging purpose for us to run the main method, and will be deleted later for the final release version). Also, it is clear for everyone to work on a certain part of our project without violating the dependency rule.


## Design Pattern
* Dependency Injection:\
https://github.com/CSC207-UofT/course-project-group006/pull/32#issue-1067677324.
\
&nbsp;&nbsp;&nbsp;&nbsp;This design pattern is a technique that makes a class independent of its dependencies. It helps us to follow the dependency inversion principle and single responsibility in solid. We introduce interfaces to break the dependencies between higher and lower level classes so that both classes depend on the interface and no longer on each other. In our code, we create an interface called GeneralReadWrite, which is the interface that all of our gateways implement. For the use cases, all of our managers import the GeneralReadWrite interface. In this case, both the use cases and gateways depend on the interface instead of each other. 
* Strategy:\
https://github.com/CSC207-UofT/course-project-group006/pull/32#issue-1067677324.
\
&nbsp;&nbsp;&nbsp;&nbsp;The strategy design pattern allows us to do specific things in different ways. We encapsulate an interface as a base and then bury implementation details in derived classes. In our project, we build our gateways by implementing the interface called GeneralReadWrite. We make different gateways like Teacher Gateway, Student Gateway, for each table in the database. These gateways all have the same thing to do, which is to read/write but each corresponding table has a unique structure. Hence, by implementing the interface, we can write their getter and setter methods accordingly. By doing so, we avoid writing long if-else statements that are required to distinguish tables. For example, without this pattern, we will need to first get a parameter telling us which table we are looking at, and then read/write the table. This will create long methods and significantly decrease readability. Instead of setting the table type as a parameter, we could dynamically tell the code which read/write method we want to use on the spot. By using this design pattern, we also satisfied the open/ close principle. In the future development, we could add more different gateways without changing the older gateways. And the old ones are closed for modification.
* MVC Model:\
https://github.com/CSC207-UofT/course-project-group006/pull/32#issue-1067677324.
\
&nbsp;&nbsp;&nbsp;&nbsp;Since our project is a website, we also implemented the MVC model to follow the rules of clean architecture. MVC stands for model-view-controller and we have corresponding classes for each of them. It allows us to reduce coupling between front, back and data. Our entities such as students and groups are models. They are types of data structures and are the fundamental building block. Controllers are the servlet classes, they get information from the view classes and perform actions accordingly by manipulating models. Views are the GUI files such as login.html, they display information on the screen and don't have access to the data stored in the database.

## Use of Github Features
&nbsp;&nbsp;&nbsp;&nbsp;We frequently used the features in Github to help us work together in making this project.\
&nbsp;&nbsp;&nbsp;&nbsp;For commit and pull request, everyone has their own branch to work with. If we edit a new version, we will commit and push it to our own branch and create a pull request, waiting for our teammates to compare and merge if necessary. \
&nbsp;&nbsp;&nbsp;&nbsp;Based on the feedback of phase 1, we also discovered the advantage using issue in Github. Issue allows us to post the problems met in coding and wait for group members to solve them. We solved a few problems during our coding process using this feature. \
&nbsp;&nbsp;&nbsp;&nbsp;For actions, we have recently discovered the importance of using this feature in Github. it allows us to locate our processes and our full history. Because of this, I can revisit my original version to compare the difference.


## Code Style and Documentation
&nbsp;&nbsp;&nbsp;&nbsp;We have written Javadocs for all of our code, giving all our methods a clear description of functionality, parameter and return statement. Also, we did our best to resolve all of the warnings. Compared to phase1, we renamed the classes and functions that violating the naming conventions. For the newly added code, we make sure they were named in a proper format.


## Testing
&nbsp;&nbsp;&nbsp;&nbsp;In order to test our code, we create a new database and new ini only for testing purpose. In this way, we can test the main method of our project using a different connection to another database. In this case, we separate our main database with the test database so that keeps our main database not being affected when we run our test.\
&nbsp;&nbsp;&nbsp;&nbsp;In our design, most commands made by the users are written in our manager classes, which are our use cases. For this part, all of the methods in managers have been tested by at least one corresponding unit test and can work properly. Gateways are used to access to the database to read or write data, so we didn’t write separate unit test for this part. All of them worked correctly since we access them through interface to connect the test database when testing those managers. For our controllers, we write servlet to connect between UI and managers. It could work as we expected since we can connect our website and do the command we want correctly.



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
&nbsp;&nbsp;&nbsp;&nbsp;We are still not quite sure how to improve the connection speed.
* Worked Well:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO
* Member Progress:\
&nbsp;&nbsp;&nbsp;&nbsp;TODO\
&nbsp;&nbsp;&nbsp;&nbsp;Gavin Gao: https://github.com/CSC207-UofT/course-project-group006/pull/41#issue-1069040744. I have been working on backend by implementing the gateways and managers. This pull request changes the TestGateway and TestManager. Besides that, I also worked on document part with Jerry.\
&nbsp;&nbsp;&nbsp;&nbsp;Jacky Jiang: I have been working on implementing the gateways, managers and creating unit tests. https://github.com/CSC207-UofT/course-project-group006/pull/48#issue-1071496102. This pull request merged an important TestAnswerManager and TestAnswerGateway into main. https://github.com/CSC207-UofT/course-project-group006/pull/59#issue-1071620452. This one add most of the test cases into main.\
&nbsp;&nbsp;&nbsp;&nbsp;Jen Zhu: https://github.com/CSC207-UofT/course-project-group006/pull/32#issue-1067677324. I have been focusing on creating gateways and managers, setting up connections with the database as well as helping with the UI. This pull request changes the non-clean phase 1 structure into a clean structure by creating serval gateway classes and some new interfaces.\
&nbsp;&nbsp;&nbsp;&nbsp;Zi Hua Xu: I have been working on the written part, including the fixing warning, design document and the javadocs. https://github.com/CSC207-UofT/course-project-group006/pull/101 This is the finished version of the javadocs and the minor warnings version commit.
* Improvments From Phase 1:\
&nbsp;&nbsp;&nbsp;&nbsp;One of the greatest improvements is that we have fixed our structure according to the MVC model so that it could meet all the requirements and principles. We have also refactored a lot and significantly improves the readability of our code. We have had lots of problems and issues using GitHub and IntelliJ in phase 1 mainly because we need to include lots of dependencies, create new configure and artifacts, and many things we have not seen before, but we are happy to say that we can use GitHub and IntelliJ much better than phase 1 now. 
* In Future:\
&nbsp;&nbsp;&nbsp;&nbsp;We do have many ideas in mind, for example, the UI is no way close to elegant and there are many ways to make it look more professional. We plan to implement CSS and html5 into our front end. Another thing we could improve on is to migrate our source code to an online hosting server so that users can access the website directly using the URL, and don't need to download anything locally.
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


* Write a paragraph about who you would market your program towards, if you were to sell or license your program to customers. This could be a specific category such as "students" or more vague, such as "people who like games". Try to give a bit more detail along with the category.\
Our program is toward students who want to do ward studies and teachers who would like to have some extra income. As mentioned in the introduction paragraph, our website aims to provide a platform for educational services.

* Write a paragraph about whether or not your program is less likely to be used by certain demographics. For example, a program that converts txt files to files that can be printed by a braille printer are less likely to be used by people who do not read braille.\
Our website is theoretically fittable for everyone. For people having some visual disabilities, since our website provides all announcement and materials in String form, then it can be converted to txt file in some way. Also, even if he/she does not want to convert to txt file, a more convenient way will be opening a translate tool and screen shot our website, the clear and neat format will make it easy to identify words, the translate tool shall be able to read to our users.
