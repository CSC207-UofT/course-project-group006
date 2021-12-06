# Design Document
## Updated specification
### Introduction

We have jumped out the box the the directory and adopted a more general platform suitable for any kind of interactive learning! Teachers can form study groups, create questions and form tests that can be assigned to study groups. Once a question/test is completed, it becomes the property of the teacher. In the future, there will be ways for teachers to profit from these creations. In the future, there will be ways for teachers to profit from these creations. Students may use the website to join study groups and learn with other students with the benefit of having a teacher to provide personalized tests.


### Functions
#### Individual Study (TBC)
Users identified as students can choose to study individually, he/she will be provided services such as wordlists and personal tests. The website will automatically generate quizzes at the student’s level.
#### Group Study
Users identified as students can also choose to study in groups, he/she will be assigned to a group and a teacher at a specific level. The website will provide a platform for the necessary connections between students and teachers.
#### Tests
Users identified as students can access tests posted by teachers if he/she joins groups or use auto-created quizzes as progress measurement if he/she chooses to study individually.
Users identified as teachers can upload tests and get auto-graded scores from the website after students finish the test.
#### Give Recommendation (TBC)
The website offers groups and teachers to students, once the student users finish a diagnostics quiz to evaluate level.
#### User Files
The website will stores all he information online without using any local storage.
#### Profit (TBC)
Since student users need to buy a membership in order to access tests on the website. Half of the profit will be given to the teacher users so teachers can gain profit that encourages him/her to create more excellent tests for students and earn for living at the same time. (The other half will be used to run the website)
#### Student Page
The website creates a webpage for each registered student. Student users can see all the groups they have joined and are able to join new groups on the page.
#### Teacher Page
The website creates a webpage for every teacher user. Teacher users can see all the groups they have and are able to delete students from groups. They can also access their tests on the page by pressing the tests button.


## Functionality
For now, our program can do most parts said by our specification such as creating groups and tests. We even implemented an online database and web ui to our program. However, since this is our first time coding a web program, we have faced many difficulties along the way. Our front end and back end code can work perfectly seperateingly, but we want to challenge ourselves more by connecting them together for phase 1. This process created lots of issues and we have tried our best to overcome them. We understand that our program right now is no way close to being professional, but we will absolutely move towards that direction in the future.

Our program is able to store and load state through accessing an online database. All the information is organized and stored in the remote rather than local. All states of the users will be saved, for example, if a teacher creates a question, it will be permanently stored in the database unless they decide to delete it. This is not affected by time and location since everything is stored real-time.

We think we are ambitious enough for our group size. We unfortunately lost one member along the way, and everyone immediately felt the weight added to us. We are challenging ourselves by doing web developing and online databases since these two are completely new to all the group members. We are also planning to upgrade from localhost to online hosting in the future, which is another big challenge for us.


## Design Decision
We designed to let our users respond in the front end, and then the data is sent to the database. After processing functions, the response data will be sent back to users. We designed to ensure that our website can hold enough users at the same time, which avoids that when several users are using at the same time, the program will be confused which to process.  

We satisfy the following principles of Universal Design:

Tolerance of error: Pop up warning for important actions: To avoid some actions by mistakes, our website allows double checking for users in the important moves such as deleting students from group and quitting from group. 

Simple and Intuitive Use: Different clarified buttons control specific functionality, all buttons have distinct functionalities, all buttons are labelled with clear meaning about what the button leads to, which helps users to quickly identify where they are towarding.  

Low Physical Effort: It does not require repeatable actions to use the website. There are no functionalities that requires multiple taps to be accessed  

Perceptible Information: clear instruction to functions with concise and precise descriptions. For example, the login page only has two buttons, the login and the register buttons.  


## Clean Architecture
Referring to Clean Architecture, we tried our best to follow the rules. However, since we are programming a website, there's some difference with normal Java Programming. 
Firstly, we have divided our code into different parts as the Clean Architecture says. In our project, we don't have a traditional sense of entity. We use the tables in database server to act like that. The use of entity is because it is convenient to encapsulate some information to an object and use that for further programming. Our tables in database did the very similar thing. For Use cases, we have the Command class that provide functions for those entities to work. Also, we got Read and Write package that act like use case to interact with the inner data. For Controllers, we have different kinds of servlets which sends users responses to backend and sends the backend responses to users. For the outermost layer, we built a website as the User Interface that our users could use.

Besides, every piece of our code follows the dependency rule. Throughout our project, the outer layers depend on inner layers and don’t have cross layer dependency. For example, if we got an instruction to login to the system from UI. The controller login page servlet will send the information to our backend. And the specific  command that corresponding to the login will receive the call and make some verification based on the given username and password. Then the response will be send back and the UI will show user the result. 



## SOLID

Single responsibility principle(SRP):In short, each class relates to one functionality and each page has its own corresponding servlet. This principle is relatively easy for us to follow because a large portion of our code is implemented through the command design pattern. This pattern ensures that each class only does one thing. For example, we have a command named “addQuestionToTest”. As its name suggests, the one and only one thing this class does is that it locates a question, and stores this question into a test in the database. It reads, modifies necessary fields in the database, but it never touches anything that is unrelated, for instance, any student information in the database. We don’t have any classes that violate this principle mainly thanks to the command pattern.

Open/closed principle(OCP): Once again, we effortlessly follow this principle because of the command pattern. If we need to use any other commands along the way, we could easily just create a new class that is a sub layer of command. This does not interact with other classes in any way, so there wouldn’t be any editing needed for the original code. For example, if we decide we need a method that counts the number of questions finished by a student, we only need to add a new class and write its inherited “execute” method. All the changes will be done within its class without affecting codes that are outside.

Liskov substitution principle(LSP): We haven’t used the principle in any obvious places mainly due to the fact that we are using a command design pattern. The structure of each subclass is mostly predefined before the inheritance. We will never use the parent command class directly, and its sub command classes all have the same method, “execute.” There wouldn’t be any exceptions for now because this principle mostly challenges the “is-a” relation, and our lack of entities prevents situations like this from happening. However, in the future, if we ever want to store anything locally, there is a greater chance that we need entities and thus need to carefully consider this principle in our design.

Interface segregation principle(ISP): We used two interfaces, a Readable and a Writable. These two interfaces are implemented in readers and writes, but they are never both implemented at the same time. This is because a reader will never be asked to write anything in the table, which makes implementing writable a waste. The write method will never be used so it is never implemented in any reader classes. This works the same way for writer classes. For example, a reader class, group reader, implements readable, but not writable.

Dependency inversion principle(DIP): Our original design did satisfy this principle as we planned to make entities and use cases depend on abstract interfaces readable and writable. However, when we were actually implementing our idea, it turned out that using entities could be redundant. Even though we don’t have actual entities in our code, the tables in the database serve in a similar way as the entities for storing information. Our reader and writer,which implement an abstract interface readable/writable, interact with the database in a way that use cases may do, so there still seems to be a bridge made by abstract interface between “entity” and “use case.” This is only our informal idea to visualize them in this way because we know that the tables are not entities, and readers/writers are not use cases.


## Packaging Strategy
We have considered to package by functions in the beginning. However, when we actually started implementing the website, we soon realized this was extremely difficult to achieve. First of all, a web application has a specific packaging strategy automatically generated by the IDE - front end and back end. Since the front end is the view and the back end is the three inner circles in the clean architecture layers, the overall strategy is by layers.

However, we used a different approach when organizing the files within the back end. Our program is mostly driven by communication between the user and the database, so we decided to organize them by functionality. The back end is responsible for the actual action of our website, so it contains a lot of functions. Each function is packaged into one package. We seperate the functions into four general categories - reader, writer, more complex command and servlet.Within each general package, we further break it down according to the entity each function is related to, for example, reader contains teacher reader, student reader and other readers. 

These packages construct the major body of our project. Since each package consists of some specific functions, if there is an implementation error, it can be easily located. The command package follows the idea of using the Command design pattern which is shown below. The servlet package plays the role of the controller, which sends users responses to backend and sends the backend responses to users. We packed the classes in this way because it is easy to locate problems while running. If the files are not collected together, we might need to write similar codes all over the place, which makes debug extremely painful. It also significantly increases the readability of the entire project, and makes it easy for anyone to pick up in the mid way. We do not need to teach the new coder from the basics so we could help each other while coding. There isn’t any part that is understood by only one person. 

## Design Pattern
For design patterns, we plan to implement the Command Design Pattern. Command is a design pattern that an object is used to represent and encapsulate all the information needed, so that we can parametrize clients with different requests. In this case, the client will treat the object as black box and accomplish some goal by just invoking the method “execute”. For this design pattern, sequences of command objects can be assembled into composite commands.  

In our project, we are making a website that provides for students and teachers to use together, so there will be plenty of commands or orders called by our users. In this case, a “Command” design pattern would be very useful. For phase 0, we use different kinds of managers to handle all the commands in our project, including creating an account, login to the system, joining a group and so on. After implementing the Command Design Pattern,  instead of packing all the user commands into a single class, we build a “Command” parent class and then make every action into a single class and then extend that parent class. For these Commands, we specified the receiver for each of them so that each command will work with the corresponding entities correctly. 

## The Use of Github Features
We used the pull requests frequently. In the beginning, every one of us creates a new branch and does all the editing on the branch. Every time we commit the newest version of our project, we create pull requests and wait after the discussion in the group. After that we merge to the main group and continue programming. As we met a lot offline, we need not use too much github. Most of the edit has been done over in-person discussion. Although most of the edit were done before the commits, we still put effort in learning about different github operations. Also, since we used netbeans to run the simulations. It cannot fully achieve all the functions in Github as it is not intelligent enough.

## Code Style and Documentation
We have written Javadocs for the necessary functions in the back end. Even though not all of the functions have commented with Javadocs, the main part of them are commented with precise summary and description. Also, we did our best to avoid all of the warnings, but there exists some warnings that cannot be resolved.For example, some functions have warnings that says they haven’t been used. In fact, they are used in the connection to our website. Those warnings will not be fully resolved, since we are programming a website which is not really compatible with intelliJ. For our project, we used netbeans to run the simulations, so there might be errors occurring in intelliJ but will not show up in Netbeans.

## Testing
For the testing of our project, we tried our best to test the existing functions. For the front end, we built a website for user interface, so it is hard to write unit test in Java. The major part of our testing is on the back end. Based on the ideal scenario we come up with, we write unit test which could check whether account creation and login of the account worked. 

For teachers, we tested the function of creating questions, creating tests, adding questions to a test, the auto grade of a test and deleting group or group members. For students, we tested the function of joining a group and quitting a group.
Our testing is not quite perfect, but we will keep updating them as the project keeps growing. And in phase 2, we will try to cover all of our code.

## Refactoring
Our project implements most of its functions through manipulating data in the data set, so there would be plenty of duplicated codes corresponding to the access, update, or manipulate of data base. In this case, it is a problem that modifying one portion of certain code will cause many changes in the whole project. Besides duplications, the process of communicating with the data base is kind of long. When a method is long, the chance of making mistakes significantly increases. Except what just mentioned, we think there's no other code smells.

In order to handle the situation mentioned above, we use extract method to refactor our code. Specifically, we move a code that grouped with others to a new and separated function. After the implementation of this feature, we could avoid the long method problem, and we also collect some similar codes together so that minimize duplications.

The other refactoring method is using design pattern. We change our order operation from different managers into Command classes. By extending from parent Command class, our use cases, which are the child classes of Command, are organized clearly. It is also easier for the front end to interact and use these command.




## Progress Report
### Open Question
How do we incorporate entity and real-time database?
### Worked Well
Everything has been working well according to our design. Our program satisfies 4 out of 7 principles of the Universal Design. Followed by satisfaction with 3/4 out of 5 SOLID principles and a clean architecture. We used the Command Design Pattern to improve our program.
### Member Progress
Darcy is responsible for the front end of the program. During the working period, Darcy actively learned knowledge related to website building. He made the most significant effort on our User Interface part and as well as the controllers.   
Gavin is responsible for the design document of the program. Besides, he works with Zi Hua on solving warnings and completing Javadocs of the program. He’s in charge of the text work of the group. Also, he and Zi Hua worked together on drawing the class diagram of the project.  
Jacky is responsible for the back end of the program. He learned to implement the Command design pattern into our project. Also, he is the major person to write tests in our group, which is the crucial part to make sure the code works.  
Jenny is responsible for the back end of the program. She cooperates with Jacky on the implementation of Command design pattern. She learned about databases to create our own database to save all the information for our system.  
Zi Hua is also responsible for the design document of the program. He and Gavin wrote the required documentation listed on the phase 1 page. Also, he worked on solving the warnings and code style with Gavin. A precise diagram is also done under the corporation of Gavin and Zi Hua.   

