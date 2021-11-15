# Design Document
## Updated specification
### Introduction

An educational website that provides a platform for teachers and students to collaboratively learn vocabularies in other languages. But being an unusual dictionary, this website helps to study the language by providing a word bank for the users to memorize. Teachers can form study groups and assign homework, quizzes and exams to the students within the group. Students may use the website to learn words by working on their own or join a study group and learn with other students.  

Other than learning the language through memorizing words. The website not only provides a great opportunity for students to find a teacher to help with their language learning studies but also gives the teachers the chance to meet and connect with some new potential students online. 

### Functions
#### Individual Study
Users identified as students can choose to study individually, he/she will be provided services such as wordlists and personal tests. The website will automatically generate quizzes at the student’s level.
#### Group Study
Users identified as students can also choose to study in groups, he/she will be assigned to a group and a teacher at a specific level. The website will provide a platform for the necessary connections between students and teachers.
#### Tests
Users identified as students can access tests posted by teachers if he/she joins groups or use auto-created quizzes as progress measurement if he/she chooses to study individually.
Users identified as teachers can upload tests and get auto-graded scores from the website after students finish the test.
#### Give Recommendation
The website offers groups and teachers to students, once the student users finish a diagnostics quiz to evaluate level.
#### User Files
The website will create a file containing users’ username, password, email and some other information once the user finishes registration.
#### Profit
Since student users need to buy a membership in order to access tests on the website. Half of the profit will be given to the teacher users so teachers can gain profit that encourages him/her to create more excellent tests for students and earn for living at the same time. (The other half will be used to run the website)
#### Student Page
The website creates a webpage for each registered student. Student users can see all the groups they have joined and are able to join new groups on the page.
#### Teacher Page
The website creates a webpage for every teacher user. Teacher users can see all the groups they have and are able to delete students from groups. They can also access their tests on the page by pressing the tests button.
## Design Decision
We designed to let our users respond in the front end, and then the data is sent to the database. After processing functions, the response data will be sent back to users. We designed to ensure that our website can hold enough users at the same time, which avoids that when several users are using at the same time, the program will be confused which to process.  
We satisfy the following principles of Universal Design  
Tolerance of error:  
Pop up warning for important actions: To avoid some actions by mistakes, our website allows double checking for users in the important moves such as deleting students from group and quitting from group.  
Simple and Intuitive Use:  
Different clarified buttons control specific functionality, all buttons have distinct functionalities, all buttons are labelled with clear meaning about what the button leads to, which helps users to quickly identify where they are towarding.  
Low Physical Effort:  
It does not require repeatable actions to use the website. There are no functionalities that requires multiple taps to be accessed  
Perceptible Information:  
Clear instruction to functions with concise and precise descriptions. For example, the login page only has two buttons, the login and the register buttons.  

## Clean Architecture
Referring to Clean Architecture, I think our group did a great job. Firstly, we have divided our code into four different parts as the Clean Architecture says. For entities, we have User class, Test class, and Group class that represent the most basic variable in our project. For Use cases, we have UserManager class, GroupManager class and the Command class that provide functions for those entities to work. For Controllers, we have different kinds of servlets that work with Use cases and the website.. For the outermost layer, we built a website as the User Interface that our users could use.  
Besides, every piece of our code follows the dependency rule. Throughout our project, the outer layers depend on inner layers and don’t have cross layer dependency. For example, if users want to login to the system, the UI will receive the instruction first. After the login servlet, the Command called login command will call the login method in Usermanager. And finally this method will reach the innermost layer User to achieve this command.  


## SOLID

Single responsibility principle(SRP) Each class relate to one functionality   
Each page has its own corresponding servlet.  
Open/closed principle(OCP) Available to add extensions without editing the origin  
Liskov substitution principle(LSP) We haven’t used the principle  
Interface segregation principle(ISP) We used two interfaces, a Readable and a Writable. The classes implementing abstract in Readable does not contain anything in Writable, while the classes implementing abstract in Writable also does not contain anything in Readable  
Dependency inversion principle(DIP) The gateway classes and entity classes both depend on some abstract interfaces, but not depend on each other.  


## Packaging Strategy
We packaged the classes by functionality. For the whole project, we have front end and back end to work together. Since we separate our work into three sections, the front end, the back end and the design document, then it is easy for us to trace where the problem is and who is responsible for it. The back end is responsible for the actual action of our website, so it contains a lot of functions. Each function is packaged into one package. For example, the Command package, Read Teacher, Write Teacher and so on. These packages construct the major body of our project. Since each package consists of some specific functions, if there is an implementation error, it can be easily located. The command package follows the idea of using the Command design pattern which is shown below. The servlet package plays the role of the controller, which sends users responses to backend and sends the backend responses to users. We packed the classes in this way because it is easy to locate problems while running and it is neat and clean. 


## Design Pattern
For design patterns, we plan to implement the Command Design Pattern. 	Command is a design pattern that an object is used to represent and encapsulate all the information needed, so that we can parametrize clients with different requests. In this case, the client will treat the object as black box and accomplish some goal by just invoking the method “execute”. For this design pattern, sequences of command objects can be assembled into composite commands.   
In our project, we are making a website that provides for students and teachers to use together, so there will be plenty of commands or orders called by our users. In this case, a “Command” design pattern would be very useful. For phase 0, we use different kinds of managers to handle all the commands in our project, including creating an account, login to the system, joining a group and so on. After implementing the Command Design Pattern,  instead of packing all the user commands into a single class, we build a “Command” parent class and then make every action into a single class and then extend that parent class. For these Commands, we specified the receiver for each of them so that each command will work with the corresponding entities correctly. 




## Progress Report
### Open Question
Why do we need entities?
### Worked Well
Everything has been working well according to our design. Our program satisfies 4 out of 7 principles of the Universal Design. Followed by satisfaction with 4 out of 5 SOLID principles and a clean architecture. We used the Command Design Pattern to improve our program.
### Member Progress
Darcy is responsible for the front end of the program. During the working period, Darcy actively learned knowledge related to website building. He made the most significant effort on our User Interface part and as well as the controllers.   
Gavin is responsible for the design document of the program. Besides, he works with Zi Hua on solving warnings and completing Javadocs of the program. He’s in charge of the text work of the group. Also, he and Zi Hua worked together on drawing the class diagram of the project.  
Jacky is responsible for the back end of the program. He learned to implement the Command design pattern into our project. Also, he is the major person to write tests in our group, which is the crucial part to make sure the code works.  
Jenny is responsible for the back end of the program. She cooperates with Jacky on the implementation of Command design pattern. She learned about databases to create our own database to save all the information for our system.  
Zi Hua is also responsible for the design document of the program. He and Gavin wrote the required documentation listed on the phase 1 page. Also, he worked on solving the warnings and code style with Gavin. A precise diagram is also done under the corporation of Gavin and Zi Hua.   

