# Design Document
## Your updated specification.
Briefly highlight any additional functionality that you have implemented between phase 0 and the end of phase 1.

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
## Diagram
Optionally, include a well-formatted diagram of your code. This is called a class diagram (Links to an external site.), and there is an international standard called the Unified Modelling Language (Links to an external site.) (UML) that describes them in detail. However! Please don't worry about the details, just use boxes and arrows like we do in the slides. If you're curious, you can create a full UML class diagram for all or part (at your group's discretion) of your program.
NOTE 1: This can really help your TA understand what you did, but if presented poorly it won't be of much use.
NOTE 2: If you have access to the Pro version of IntelliJ, it can generate class diagrams for you, but make sure it is well formatted if you hand one in!
## Design Decision
A description of any major design decisions your group has made (along with brief explanations of why you made them).
We designed to let our users respond in the front end, and then the data is sent to the database. After processing functions, the response data will be sent back to users. We designed to ensure that our website can hold enough users at the same time, which avoids that when several users are using at the same time, the program will be confused which to process.
We satisfy the following principles of Universal Design
Tolerance of error:
When the user forgets his/her password, the website allows him/her to press forget password and look back for his/her password.
Pop up warning for important actions: To avoid some actions by mistakes, our website allows double checking for users in the important moves such as deleting students from group and quitting from group.
Simple and Intuitive Use:
Different clarified buttons control specific functionality, all buttons have distinct functionalities, all buttons are labelled with clear meaning about what the button leads to, which helps users to quickly identify where they are towarding.
Low Physical Effort:
It does not require repeatable actions to use the website. There are no functionalities that requires multiple taps to be accessed
Perceptible Information:
Clear instruction to functions with concise and precise descriptions. For example, the login page only has two buttons, the login and the register buttons.

## Clean Architecture
A brief description of how your project adheres to Clean Architecture (if you notice a violation and aren't sure how to fix it, talk about that too!)
Referring to Clean Architecture, I think our group did a great job. Firstly, we have divided our code into four different parts as the Clean Architecture says. For entities, we have User class, Test class, and Group class that represent the most basic variable in our project. For Use cases, we have UserManager class and GroupManager class that provide functions for those entities to work. For Controllers, . For the outermost layer, .
Besides, every piece of our code follows the dependency rule. Throughout our project, we need to use our entities frequently. If users want to change their password, the Command controller will call the reset password method in UserManager. And finally this method will reach the innermost layer User to achieve this command. 

## SOLID
A brief description of how your project is consistent with the SOLID design principles (if you notice a violation and aren't sure how to fix it, talk about that too!)

Single responsibility principle(SRP) Each class relate to one functionality 
Each page has its own corresponding servlet.
Open/closed principle(OCP) Available to add extensions without editing the origin
Liskov substitution principle(LSP) We haven’t used the principle
Interface segregation principle(ISP) 
Dependency inversion principle(DIP)

## Packaging Strategy
A brief description of which packaging strategies you considered, which you decided to use, and why. (see slide 7 from the packages slides)

## Design Pattern
A summary of any design patterns your group has implemented (or plans to implement).
For design patterns, we plan to implement the Command Design Pattern. For this design pattern, sequences of command objects can be assembled into composite commands. In our project, we are making a website that provides for students and teachers to use together, so there will be plenty of commands or orders called by our users. In this case, a “Command” design pattern would be very useful.

For phase 0, we use use cases to handle all the commands in our project, including creating an account, login to the system, join a group and so on. After implementing the Command Design Pattern,  instead of packing all the user commands into a single class, we build a “Command” parent class and then make every action into a single class and then extend that parent class. For these Commands, we specified the receiver for each of them so that each command will work with the corresponding Use case correctly. But these procedures are not the ending. 

We need to construct a Command Controller that controls how these commands work. Besides, like the JShell example we did in class, we may need to build some CommandExecutor or other class that helps the Command classes and UI work correctly. 



## progress report
### open questions your group is struggling with

### what has worked well so far with your design
Everything has been working well according to our design. Our program satisfies 4 out of 7 principles of the Universal Design. Followed by satisfaction with 4 out of 5 SOLID principles and a clean architecture. We used the Command Design Pattern to improve our program.
### a summary of what each group member has been working on and plans to work on next
Darcy is responsible for the front end of the program. 
Gavin is responsible for the design document of the program.
Jacky is responsible for the back end of the program.
Jenny is responsible for the back end of the program.
Zi Hua is responsible for the design document of the program.
