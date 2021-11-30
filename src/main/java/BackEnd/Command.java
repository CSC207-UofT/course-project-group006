package BackEnd;

import BackEnd.Read.Answer.AnswerReader;
import BackEnd.Read.Answer.ReadMark;
import BackEnd.Read.Answer.ReadAnswerByMore;
import BackEnd.Read.Group.GroupReader;
import BackEnd.Read.Group.ReadStudents;
import BackEnd.Read.Question.QuestionReader;
import BackEnd.Read.Question.ReadID;
import BackEnd.Read.Question.ReadQuestion;
import BackEnd.Read.Student.StudentReader;
import BackEnd.Read.Teacher.ReadTests;
import BackEnd.Read.Teacher.TeacherReader;
import BackEnd.Read.Test.*;
import BackEnd.Write.Answer.AnswerWriter;
import BackEnd.Write.Answer.AutoGrade;
import BackEnd.Write.Answer.WriteMark;
import BackEnd.Write.Answer.WriteNewAnswer;
import BackEnd.Write.Group.*;
import BackEnd.Write.Question.QuestionWriter;
import BackEnd.Write.Question.WriteNewQuestion;
import BackEnd.Write.Student.StudentWriter;
import BackEnd.Write.Student.WriteGroups;
import BackEnd.Write.Student.WriteNewStudent;
import BackEnd.Write.Teacher.TeacherWriter;
import BackEnd.Write.Teacher.WriteNewTeacher;
import BackEnd.Write.Teacher.WriteTests;
import BackEnd.Write.Test.TestWriter;
import BackEnd.Write.Test.*;
import BackEnd.Write.Test.WriteNewTest;
import BackEnd.Write.Test.WriteQuestions;


import java.sql.*;


public abstract class Command {
    public final int SUCCESS = 0;
    public final int FAILED = -1;
    public final int USERNAMEALREADYUSED = -2;
    public final int GROUPALREADYJOINED = -3;
    public final int TEACHER = 11;
    public final int STUDENT = 12;

    public final String STUDENTTABLENAME = "STUDENT";


    public Connection connection = null;

    public String driver = "com.mysql.cj.jdbc.Driver";//驱动程序名
    public String url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5449780";//url指向要访问的数据库study
    public String user = "sql5449780";//MySQL配置时的用户名
    public String password = "HNzHR6WEhn";//MySQL配置时的密码

    public Command() {
        getConnection();
    }

    public void getConnection() {
        try {
            // 加载驱动类
            Class.forName(driver);
            // 建立连接
            this.connection = DriverManager.getConnection(url,
                    user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int removeGroupFromUser(int userID, int groupID, int userType) {

        if (userType == STUDENT) {
            //get all group ids
            StudentReader reader = new BackEnd.Read.Student.ReadGroups(userID);
            String allGroups = (String) reader.read();
            if (allGroups.equals(FAILED + "")) {
                return FAILED;
            }
            //check if group id is in the string
            if (!isInString(allGroups, groupID, ",")) {
                return FAILED;
            }
            //remove id from string
            String newGroups = removeIDFromString(allGroups, groupID, ",");
            //reset the new string
            StudentWriter writer = new BackEnd.Write.Student.WriteGroups(userID, newGroups);
            return (int) writer.set();

        } else {
            TeacherReader reader = new BackEnd.Read.Teacher.ReadGroups(userID);
            String allGroups = (String) reader.read();
            if (allGroups.equals(FAILED + "")) {
                return FAILED;
            }
            if (!isInString(allGroups, groupID, ",")) {
                return FAILED;
            }
            String newGroups = removeIDFromString(allGroups, groupID, ",");
            TeacherWriter writer = new BackEnd.Write.Teacher.WriteGroups(userID, newGroups);
            return (int) writer.set();
        }
    }

    public int removeStudentFromGroup(int userID, int groupID) {

        //get all students
        GroupReader groupReader = new ReadStudents(groupID);
        String allStudents = (String) groupReader.read();
        if (allStudents.equals(FAILED + "")) {
            return FAILED;
        }
        //check if student is in the group
        if (!isInString(allStudents, groupID, ",")) {
            return FAILED;
        }
        //remove student from the group
        String newStudents = removeIDFromString(allStudents, userID, ",");
        //reset the new string
        GroupWriter writer = new BackEnd.Write.Group.WriteStudents(newStudents, groupID);
        return (int) writer.set();
    }

    public boolean isInString(String string, int id, String split) {
        String[] array = string.split(split);
        for (String s : array) {
            if (s.trim().equals(id + "")) {
                return true;
            }
        }
        return false;
    }

    public String removeIDFromString(String string, int id, String split) {
        if (string.trim().length() == (id + "").length()) {
            return "";
        }
        String[] array = string.split(split);
        String result = "";
        for (String s : array) {
            if (!s.equals(id + "")) {
                result = s + ",";
            }
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

    public abstract Object execute();
}

//String name,String pass -> an id/FAILED
class loginCommand extends Command {
    private final String name;
    private final String pass;

    public loginCommand(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    @Override
    public Object execute() {

        StudentReader studentReader = new BackEnd.Read.Student.ReadNameAndPass(name, pass);
        int id = (int) studentReader.read();
        if (id != FAILED) {
            return id;
        }

        TeacherReader teacherReader = new BackEnd.Read.Teacher.ReadNameAndPass(name, pass);
        id = (int) teacherReader.read();
        return id;

    }
}

//int id -> STUDENT/TEACHER/FAILED
class checkIdentity extends Command {
    int id;

    public checkIdentity(int id) {
        this.id = id;
    }

    @Override
    public Object execute() {

        TeacherReader teacherReader = new BackEnd.Read.Teacher.ReadName(id);
        String name = (String) teacherReader.read();
        if (!name.equals(FAILED + "")) {
            return TEACHER;
        }
        StudentReader studentReader = new BackEnd.Read.Student.ReadName(id);
        name = (String) studentReader.read();
        if (!name.equals(FAILED + "")) {
            return STUDENT;
        }

        return FAILED;

    }
}

//String name, String pass, String email, int type(teacher11, student12)-> USERNAMEALREADYUSED/SUCCESS/FAILED
class registerTeacher extends Command {
    private final String name;
    private final String pass;
    private final String email;

    public registerTeacher(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;

    }

    @Override
    public Object execute() {

        TeacherWriter teacherWriter = new WriteNewTeacher(name, pass, email, TEACHER);
        return teacherWriter.set();


    }
}

//String name, String pass, String email, int type(teacher11, student12)-> USERNAMEALREADYUSED/SUCCESS/FAILED
class registerStudent extends Command {
    private final String name;
    private final String pass;
    private final String email;


    public registerStudent(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;

    }

    @Override
    public Object execute() {

        StudentWriter studentWriter = new WriteNewStudent(name, pass, email, STUDENT);
        return studentWriter.set();


    }
}

//int studentID, int groupID -> SUCCESS/FAILED
class quitGroupCommand extends Command {
    int studentID;
    int groupID;

    public quitGroupCommand(int studentID, int groupID) {
        this.studentID = studentID;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {

        //remove group from student
        int result = removeGroupFromUser(studentID, groupID, STUDENT);
        if (result != SUCCESS) {
            return FAILED;
        }
        //remove student from group
        result = removeStudentFromGroup(studentID, groupID);
        if (result != SUCCESS) {
            return FAILED;
        }
        return SUCCESS;
    }
}

//int studentID, int groupID -> SUCCESS/FAILED
class joinGroupCommand extends Command {
    int studentID;
    int groupID;

    public joinGroupCommand(int studentID, int groupID) {
        this.studentID = studentID;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {

        //add group to student
        ////get all the groups joined by the student
        StudentReader studentReader = new BackEnd.Read.Student.ReadGroups(studentID);
        String allGroups = (String) studentReader.read();
        ////add groupID to the string
        if (isInString(allGroups, groupID, ",")) {
            return GROUPALREADYJOINED;
        }
        if (allGroups.length() == 0) {
            allGroups = groupID + "";
        } else {
            allGroups = allGroups + "," + groupID;
        }
        ////rewrite the new string to the database
        StudentWriter studentWriter = new WriteGroups(studentID, allGroups);
        int result = (int) studentWriter.set();
        if (result == FAILED) {
            return FAILED;
        }


        //add student to group
        ////get all the students form the group
        GroupReader groupReader = new ReadStudents(groupID);
        String allStudents = (String) groupReader.read();

        ////add studentID to the group
        if (allStudents.length() == 0) {
            allStudents = studentID + "";
        } else {
            allStudents = studentID + "," + allStudents;
        }
        ////rewrite the new string to the database
        GroupWriter groupWriter = new WriteStudents(allStudents, groupID);
        return groupWriter.set();

    }
}

//int teacherID, String groupName -> GroupID/FAILED
class createGroupCommand extends Command {
    int teacherID;
    String groupName;

    public createGroupCommand(int teacherID, String groupName) {
        this.teacherID = teacherID;
        this.groupName = groupName;
    }

    @Override
    public Object execute() {
        GroupWriter groupWriter = new WriteNewGroup(teacherID, groupName);
        return groupWriter.set();
    }
}

//int teacherID, int groupID -> SUCCESS/FAILED
class deleteGroupCommand extends Command {
    int teacherID;
    int groupID;

    public deleteGroupCommand(int teacherID, int groupID) {
        this.teacherID = teacherID;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {

        //remove group from teacher
        int result = removeGroupFromUser(teacherID, groupID, TEACHER);
        if (result != SUCCESS) {
            return FAILED;
        }
        //remove group from students
        GroupReader groupReader = new ReadStudents(groupID);
        String allStudents = (String) groupReader.read();
        if (allStudents.trim().length() != 0) {
            String[] students = allStudents.trim().split(",");
            for (String student : students) {
                removeGroupFromUser(Integer.parseInt(student), groupID, STUDENT);
            }
        }

        //delete the group from the group table
        GroupWriter groupWriter = new DeleteGroup(groupID);
        return groupWriter.set();
    }

}

//String test name, String author, int price -> SUCCESS/FAILED
class createTestCommand extends Command {
    private final String name;
    private final int author;
    private final int price;
    private final java.util.Date date;

    public createTestCommand(String name, int author, java.util.Date date, int price) {
        this.name = name;
        this.author = author;
        this.date = date;
        this.price = price;
    }

    @Override
    public Object execute() {
        TestWriter testWriter = new WriteNewTest(name, author, price, date);
        int id = (int) testWriter.set();
        if (id == FAILED) {
            return FAILED;
        }

        TeacherReader teacherReader = new ReadTests(author);
        String allTest = (String) teacherReader.read();
        if (allTest.length() == 0) {
            allTest = "" + id;
        } else {
            allTest = allTest + "," + id;
        }

        TeacherWriter teacherWriter = new WriteTests(author, allTest);
        return teacherWriter.set();
    }
}

//string question name, string question, string answer -> SUCCESS/FAILED
class createQuestationCommand extends Command {
    private final String name;
    private final String question;
    private final String answer;
    private final int mark;

    public createQuestationCommand(String name, String question, String answer, int mark) {
        this.name = name;
        this.question = question;
        this.answer = answer;
        this.mark = mark;
    }

    @Override
    public Object execute() {
        QuestionWriter questionWriter = new WriteNewQuestion(name, question, answer, mark);
        return questionWriter.set();
    }
}

//int studentID, int groupID ->Success/failed
class deleteMemberCommand extends Command {
    int studentID;
    int groupID;

    public deleteMemberCommand(int studentID, int groupID) {
        this.studentID = studentID;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {

        int result1 = removeGroupFromUser(studentID, groupID, STUDENT);
        int result3 = removeStudentFromGroup(studentID, groupID);
        if (result1 == SUCCESS && result3 == SUCCESS) {
            return SUCCESS;
        }
        return FAILED;
    }

}

//String content, int group id -> SUCCESS/FAILED
class createAnnouncementCommand extends Command {
    private final int groupID;
    private final String announcement;

    public createAnnouncementCommand(String announcement, int groupID) {
        this.announcement = announcement;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {
        GroupWriter groupWriter = new WriteAnnouncement(groupID, announcement);
        return groupWriter.set();
    }
}

//int question id, int test id -> FAILED/SUCCESS
class addQuestionToTestCommand extends Command {
    private final int questionID;
    private final int testID;

    public addQuestionToTestCommand(int questionID, int testID) {
        this.questionID = questionID;
        this.testID = testID;
    }

    public addQuestionToTestCommand(int testID, String question, String answer, int mark) {
        this.testID = testID;
        this.questionID = (int) new ReadID(question, answer).read();
    }

    @Override
    public Object execute() {

        TestReader testReader = new ReadQuestions(testID);
        String allQuestions = (String) testReader.read();
        if (allQuestions.length() == 0) {
            allQuestions = "" + questionID;
        } else {
            allQuestions = allQuestions + "," + questionID;
        }

        TestWriter testWriter = new WriteQuestions(allQuestions, testID);
        return testWriter.set();


    }
}

//int student id, string answer, int question id -> SUCCESS/FAILED
class submitAnswerCommand extends Command {
    private final String[] answer;
    private final int testID;
    private final int studentID;
    private final int groupID;

    public submitAnswerCommand(int studentID, String[] answer, int testID, int groupID) {
        this.answer = answer;
        this.testID = testID;
        this.studentID = studentID;
        this.groupID = groupID;
    }

    @Override
    public Object execute() {
        TestReader testReader = new ReadQuestions(testID);
        String question = (String) testReader.read();
        if (question.equals(FAILED + "")) {
            return FAILED;
        }
        try {
            String[] questions = question.trim().split(",");
            for (int i = 0; i < answer.length; i++) {
                AnswerWriter answerWriter = new WriteNewAnswer(studentID, answer[i], Integer.parseInt(questions[i]), groupID);
                int result = (int) answerWriter.set();
                if (result == FAILED) {
                    return FAILED;
                }
                Command c = new autoGrade();
                c.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED;
        }

        return SUCCESS;
    }
}

//void --> mark/FAILED
class autoGrade extends Command {

    public autoGrade() {
    }

    @Override
    public Object execute() {
        AnswerWriter answerWriter = new AutoGrade();
        return answerWriter.set();

    }
}

//int answer id, int mark --> SUCCESS/FAILED
class gradeQuestion extends Command {
    private final int answerID;
    private final int mark;

    public gradeQuestion(int answerID, int mark) {
        this.answerID = answerID;
        this.mark = mark;
    }

    @Override
    public Object execute() {
        AnswerWriter answerWriter = new WriteMark(answerID, mark);
        return answerWriter.set();
    }
}

//int studentID, int testID --> int mark/FAILED
class gradeTest extends Command {
    private final int studentID;
    private final int testID;

    public gradeTest(int studentID, int testID) {
        this.studentID = studentID;
        this.testID = testID;
    }

    @Override
    public Object execute() {
        TestReader testReader = new ReadQuestions(testID);
        String question = (String) testReader.read();
        try {
            String[] questions = question.trim().split(",");
            int sum = 0;
            for (int i = 0; i < questions.length; i++) {
                AnswerReader answerReader = new ReadMark(studentID, Integer.parseInt(questions[i]));
                int mark = (int) answerReader.read();
                sum += mark;
            }
            TestWriter testWriter = new BackEnd.Write.Test.WriteMark(testID, sum);
            return testWriter.set();
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED;

        }

    }
}

//int studentID, int groupID --> int student average
class getStudentAve extends Command {
    private final int studentID;

    public getStudentAve(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public Object execute() {
        try {
            getConnection();
            Statement statement = connection.createStatement();

            //get student answer

            TestReader testReader = new ReadAve(studentID);
            double ave = (double) testReader.read();

            return ave;

        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }
}