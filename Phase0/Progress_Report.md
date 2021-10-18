# Progress Report

## Specification

A platform that helps students to learn words in different languages and introduces students to teachers, while people can also use it as a dictionary. Student users can join groups at their level of learning and can be assigned to teachers. Teacher users can upload tests to students to help them learn, the students are assigned by our platform. Guest users do not need to register, while they can still use the platform as an original dictionary. As of Phase 0, the program is running through the terminal, while in future, we planned to implement it onto a web page as a UI.

This is an educational dictionary platform that provides various services based on users' different demands. It is designed to help everyone to learn English in an easy yet efficient approach while also giving opportunities to the educators to link and connect to some students.

Three roles are carefully designed to capture all the potential needs: student, teacher, and unregistered guest. Student users can either learn vocabulary in different languages individually or collaboratively joining study groups at their level of learning. Each study group is created by a teacher user who is responsible for communicating and uploading tests to help the group members learn. All the learning progress of a student will be tracked and saved to their profile for them to analyze and reflect on. The platform will use this information to generate personal reminders to motivate the students. All types of users, including guests, can use this platform as a dictionary.

## CRC model

In total, we have 19 CRC cards and all of them are implemented into our skeleton program.

The classes are:

Entity: There are 11 entity classes in total but changes will be made along the way. Some examples of entity classes are Word, Question, and Student. Each entity class holds specific attributes for their designated purposes, for instance, Word stores the meanings, spellings and difficulty level of a word. Since many distinct features are to be implemented, we create a variety of entity classes to make sure we have enough foundation blocks to build upon. We plan to add more entity classes in the future, for example, to expand on the question types. However, we may also combine or remove some classes if they are redundant or useless.

Use Case: There are currently 7 use case classes such as UserManager, WordManager and TestManager. Most use case classes are directly associated with an entity class but there are also some use case classes designed only to imply certain rules. For example, WordManager is a class that handles all the operations related to a Word object. It generates a list of word objects for a given difficulty level. On the other hand, IDCreator is a use case class that generates an ID for anything no matter what entity it is. There will be changes to use case classes in the future as any changes happen to entity classes.

Controller: There are 2 controller classes that are used to interact with users. The classes MainController and FileManager deal with inputs from users. The MainController class takes input with the scanner method (for now inputs are only strings) since we are still using the terminal to do the interaction. The FileManager reads the user’s files and shows errors if the file is not readable.

UI: The UI class is used to interact with users. For now, we are still using the terminal as our UI, but in the future, we plan to use GUI to interact with our users.

Question Class: The parent class for all question types (we will add them later), stores what the question is (a string), the correct answer for the question (a string) and the mark it is worth (an int). It also contains a method to calculate the mark that will be given if a particular string is provided as the answer, and this function might be overridden by its child class. This class will not use any other classes and will be used in Test class and all its children. 

Answer Class: The class representing the answer to a particular test provided by a particular student, it stores what the test is, who the student is and what is the answers provided by the student 

Constant Class: A class that is used for the storage of constants that are used in the program. 

Exam Class: The class is a subclass of Test class. 

FileManager Class: The class is used to generate user lists, words and questions from a file. 

Group Class: The group class is used to store teachers, students, number of students in the group and tests the teacher assigns that maps to the answer students submitted. 

Guest Class: The class is a subclass of the User class and is used to store words learnt for the guest users. 

IDCreater Class: The class is used to generate ID for each individual item. 

Quiz Class: The class is a subclass of the Test class and generates autoGenerateTranslationQuestion. 

Student Class: The class is a subclass of the User class, it stores what group the student joined, which level the student is in, the word the student has learned and the balance remaining in the student account (student account has not yet on the platform and still need to work on it) 

Teacher Class: The class is a subclass of the User class. 

Test Class: The class represents a test made by a teacher. It stores the information about the test and as well as the teacher who made it and its sale price. It is used in TestManager Class. 

TestManager Class: Store and process change on test entity, it uses test classes 

User Class: Store information about users, including username, password, email and ID 

UserGroupManager Class: Store and process change on group entities and related information, it uses Group classes and all the subclasses of User. 

UserManager Class: The class has child classes StudentManager, TeacherManager and GuestManager. The class aims to manage the user functions and it stores password, username, email and balance. 

Word Class: The class is used to store meanings of words, level of different words and spelling of words 

WordManager Class: The class is a class to manage wordListSet and is used to store filename, words and levels.

## Scenario 
This first scenario walk-through describes how the platform works for a new student to register and to log in for the first time. The student enters the system and is asked to choose if they are a student, teacher or guest. Then the student is asked to register their username and password which are stored in their profile once they confirm. When they log in to the account for the first time, they will be asked to do a diagnostic for measuring the level of knowledge for future uses. After that, the student can study individually or can join a group. If they join a group, they will be able to have access to the resources provided by the assigned teacher. Our second scenario walk-through describes the situation of how a teacher can use our platform. Similar to the student login steps, after the teacher logins to the system, they can create groups. Once a group is created, the teacher is allowed to upload tests and decide whether they want to mark them manually or automatically. The choice of the teacher determines if the score of each test is shown automatically or needed to be uploaded by the teacher.

## Skeleton 
Our skeleton program is designed to have the basic structure of our platform. It contains 15 classes and 1 interface, including all the classes mentioned in CRC cards and an interface named Posts. Those classes satisfy the functions designed to be in our platform and are introduced above in the CRC model section. The users can choose their identity and set their username and password to log in. Students can do diagnostics to measure their level and then join groups, teachers can upload tests and materials, and guests can search for word meanings with the skeleton through the terminal.

## Individual Contributions

Darcy was responsible for the Test, Answer, Question and Test manager classes, which contribute to the part to create, fix, delete and authorize the tests, and as well as storing and marking the answers submitted by the students. The next step would be making Question an abstract class and implementing multiple subclasses to represent different types of questions (multiple-choice, short answer,.etc), and also introduce the trading of tests.

Zi Hua was responsible for the Group class, GroupManager class, specification, scenario walk-through and progress report. The future steps are extending the functions such as giving back students’ test results in groups and adding a restriction that students’ can only add to groups with the teacher’s permission.

Jacky Jiang was responsible for the CRC model and the specification, as well as creating unit tests to make sure the skeleton code reflects the scenario walkthrough. The future step is to expand the number of unit tests and cover as many situations as possible. The unit test is also used to assist the development of the skeleton program.

Shiqi Gao was responsible for the User and UserManager classes, their corresponding subclasses and the CRC model. The purpose of user and user manager is to create accounts and manage user’s behaviours. The next step would be completing the StudentManager and TeacherManager, as well as learning about databases and storing data online.

Jenny was responsible for Word, Word Manager, File Manager as well as helping with the skeleton code UI, specification and the report. The planning for the future would be to continue developing File Manager and upgrading the basic command line UI to the desired format. 

Lana Zhang was responsible for the Main class extending from the User, considering the Scenario Walk-Through and other classes and methods and making the class follow the Scenario Walk-Through. The next step would be to continue implementing the class and consider the suitable design pattern.


## Open Questions 
How to accomplish real time update?

How to store constants without using static variables?

How to verify that our code follows SOLID and clean architecture?

How to implement membership system/ads that actually benefits teacher users?

How to store a general method that doesn’t belong to any object classes?

## Work well done
The design and brainstorm have been done completely and neatly. 

All of our codes are written according to our design. 

The Skeleton program perfectly performs scenario walkthroughs. 

All of our group members work well together and developed a great friendship. 

Our classes are identified in the correct layer of the business rules. 

We developed an active discussion with our TA.

