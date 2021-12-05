import BackEnd.Gateways.*;
import BackEnd.Interfaces.ReadIDName;
import BackEnd.Managers.GroupManager;
import BackEnd.Managers.QuestionAnswerManager;
import BackEnd.Interfaces.GeneralReadWriter;
import BackEnd.Managers.TestAnswerManager;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Ini {
    public static Connection getConnection() {
        Connection conn = null;
        String driver = "com.mysql.cj.jdbc.Driver";//驱动程序名
        String url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5454663";//url指向要访问的数据库study
        String user = "sql5454663";//MySQL配置时的用户名
        String password = "SinqmMLSgB";//MySQL配置时的密码
        try {
            // 加载驱动类
            Class.forName(Gateway.driver);
            // 建立连接
            conn = DriverManager.getConnection(Gateway.url,
                    Gateway.user, Gateway.password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean ini() {
        return iniGroupTable() && iniTestTable() && iniQuestionTable() && iniStudentTable() &&
                iniTeacherTable() && iniQAnswerTable() && iniTAnswerTable();

    }

    public static boolean iniStudentTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS STUDENT " +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    " name VARCHAR(16), " +
                    " pass VARCHAR(16), " +
                    " date Date, " +
                    " email VARCHAR(255), " +
                    " words VARCHAR(1000), " +
                    " groupID VARCHAR(1000), " +
                    " testID VARCHAR(1000), " +
                    " answerID VARCHAR(1000), " +
                    " level INT, " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql);
            System.out.println("Created student table in given database...");
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean iniTeacherTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS TEACHER " +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    " name VARCHAR(16), " +
                    " pass VARCHAR(16), " +
                    " date Date, " +
                    " email VARCHAR(255), " +
                    " groupID VARCHAR(1000), " +
                    " testID VARCHAR(1000), " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql);
            System.out.println("Created teacher table in given database...");
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean iniGroupTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS STUDYGROUP " +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    " name VARCHAR(16), " +
                    " creator VARCHAR(16), " +
                    " students VARCHAR(1000), " +
                    " posts VARCHAR(1000), " +
                    " testID VARCHAR(1000) , " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql);
            System.out.println("Created group table in given database...");
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean iniWordTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS WORD " +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    " spelling VARCHAR(255), " +
                    " meaning VARCHAR(255), " +
                    " level INT, " +
                    " PRIMARY KEY ( id )) CHARACTER SET = utf8";
            statement.executeUpdate(sql);
            System.out.println("Created student table in given database...");
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean iniTestTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS TEST " +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    " name VARCHAR(16), " +
                    " author INT, " +
                    " date DATE, " +
                    " price INT, " +
                    " questions VARCHAR(1000), " +
                    " PRIMARY KEY ( id )) CHARACTER SET = utf8";
            statement.executeUpdate(sql);
            System.out.println("Created test table in given database...");
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean iniQuestionTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS QUESTION " +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    " name VARCHAR(16), " +
                    " question VARCHAR(1000), " +
                    " answer VARCHAR(1000), " +
                    " mark INT," +
                    " PRIMARY KEY ( id )) CHARACTER SET = utf8";
            statement.executeUpdate(sql);
            System.out.println("Created question table in given database...");
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean iniQAnswerTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS QUESTIONANSWER " +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    " questionID INT, " +
                    " answer VARCHAR(1000), " +
                    " mark INT , " +
                    " studentID INT, " +
                    " groupID INT, " +
                    " PRIMARY KEY ( id )) CHARACTER SET = utf8";
            statement.executeUpdate(sql);
            System.out.println("Created answer table in given database...");
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean iniTAnswerTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS TESTANSWER " +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    " testID INT, " +
                    " studentID INT, " +
                    " mark INT, " +
                    " PRIMARY KEY ( id )) CHARACTER SET = utf8";
            statement.executeUpdate(sql);
            System.out.println("Created answer table in given database...");
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void main(String[] args) {
////////////////
//       System.out.println(new GroupManager(new GroupGateway()).createGroup(1,"group:teacher1"));
////        System.out.println(new GroupManager(new GroupGateway()).createGroup(2,"group:teacher2"));
        //System.out.println(new GroupManager(new GroupGateway()).createGroup(3,"group:teacher3"));
        //       System.out.println(new GroupManager(new GroupGateway()).createGroup(6,"group:teacher6"));
        //  System.out.println(new GroupManager(new GroupGateway()).getAllGroup());
        // System.out.println(new GroupGateway().readRow(11));
//        System.out.println(new StudentGateway().readByID(222,5,1));
//        List<String> info = new ArrayList<>();
//        info.add("11");
//        info.add("999");
//        List<String> aa = new ArrayList<>();
//        aa.add("12");
//        aa.add("999");
////        System.out.println(new GroupGateway().write(4, info));
//        System.out.println(new GroupGateway().write(4, aa));
//        System.out.println(new StudentGateway().readIntByName(1,"student1"));
//        List<String> info = new ArrayList<>();
//        info.add(4 + "");
//
//        info.add("student1150");
//        info.add("1150pass");
//        info.add("1150email");
//        System.out.println(new UserManager(new StudentGateway()).createStudent("1150name","1150pass","1150mail"));
//        System.out.println(new UserManager(new StudentGateway()).removeGroupFromStudent(6,14));
//        System.out.println(new UserManager(new StudentGateway()).removeGroupFromStudent(6,2));
//        System.out.println(new UserManager(new StudentGateway()).removeGroupFromStudent(6,2));
//        List<String> list = new ArrayList<>();
//        List<String> list = new ArrayList<>();
//        list.add(4 + "");
//        list.add("3,4,5");
//
//        System.out.println(new StudentGateway().write(7, list));
//        ReadAll aa = new GroupGateway();
//        GeneralReadWriter bb = new StudentGateway();
//
//        GroupManager groupManager = new GroupManager(aa);
//        System.out.println(groupManager.getJoinedGroup(5,bb));
//      System.out.println(new GroupManager(new GroupGateway()).deleteGroup(16, new StudentGateway()));
//    iniQAnswerTable();
//        int questionID = 3;
//        int studentID = 4;
//        int groupID = 5;
//        List<String> info = new ArrayList<>();
//        info.add(questionID + "");
//        info.add("苹果");
//        info.add(studentID + "");
//        info.add(groupID + "");
//        System.out.println(new QuestionAnswerGateway().write(1,info));
//        int questionID = 1;
//        String answer = "香蕉";
//        List<String> info = new ArrayList<>();
//        info.add(questionID + "");
//        info.add(answer + "");
//        System.out.println(new QuestionGateway().write(4,info));
//        String  name = "name2";
//        String question = "apple";
//        String answer = "苹果";
//        int mark = 5;
//        List<String> info = new ArrayList<>();
//        info.add(name);
//        info.add(question);
//        info.add(answer);
//        info.add(mark + "");
//        System.out.println(new QuestionGateway().write(1,info));
//        int testID = 10;
//        int studentID = 1;
//        List<String> info = new ArrayList<>();
//        info.add(testID + "");
//        info.add(studentID + "");
//        System.out.println(new TestAnswerGateway().write(1,info));
//        int testID = 1;
//        int mark = 10;
//        List<String> info = new ArrayList<>();
//        info.add(testID + "");
//        info.add(mark + "");
//        System.out.println(new TestAnswerGateway().write(4,info));
//        GeneralReadWriter bb = new QuestionAnswerGateway();
//        QuestionAnswerManager questionAnswerManager = new QuestionAnswerManager(bb);
//        questionAnswerManager.createQuestionAnswer(7, "苹果", 1, 5, bb);
//        List<String> info = new ArrayList<>();
//        info.add(Integer.toString(17));
//        info.add("5");
//        bb.write(4, info);
//        questionAnswerManager.gradeQuestion(1, "苹果", 16);
//        questionAnswerManager.createQuestionAnswer(1, "10", 3, 5, bb);
//        System.out.println(new QuestionGateway().readRow(1).get(4));
//        ReadIDName bb = new QuestionAnswerGateway();
//        GeneralReadWriter aa = new TestAnswerGateway();
//        GeneralReadWriter cc = new TestGateway();
//        GeneralReadWriter dd = new QuestionAnswerGateway();
//        QuestionAnswerManager questionAnswerManager = new QuestionAnswerManager(dd);
//        int i = questionAnswerManager.createQuestionAnswer(3, "香蕉", 1, 1, dd);
//        int k = questionAnswerManager.createQuestionAnswer(3, "香蕉", 2, 1, dd);
//        int j = questionAnswerManager.createQuestionAnswer(3, "苹果", 1, 1, dd);
//        TestAnswerManager testAnswerManager = new TestAnswerManager(aa, cc, bb);
//        int n = testAnswerManager.createTestAnswer(1, 1, aa, cc, bb);
//        System.out.println(n);
//        List<String> list = new ArrayList<>();
//        list.add(1 + "");
//        list.add(2 + "");
//        List<String> result = aa.write(1, list);
//        List<String> questions = cc.readByID(111, 6, Integer.parseInt(result.get(0)));
//        System.out.println(result);
//        System.out.println(questions);
//        List<String> questions = cc.readByID(222, 6, 1);
//        System.out.println(questions);
//        int total_mark = 0;
//        for (String i: questions) {
//            List<String> mark = bb.readByIDName(111, 1, 4, Integer.parseInt(i));
////            total_mark += Integer.parseInt(mark.get(0));
//            System.out.println(total_mark);
//        }

//        List<String> list = new ArrayList<>();
//        list.add(1 + "");
//        list.add(2 + "");
//        List<String> result = aa.write(1, list);
//        System.out.println(result);
//        List<String> mark = bb.readByIDName(111, 1, 4, 3);
////        List<String> mark = bb.readByID(111, 4, 1);
//        System.out.println(mark.get(0));
//        List<String> result = bb.readByID(111, 4,1);
//        System.out.println(result);
//        List<String> list = new ArrayList<>();
//        list.add(1 + "");
//        list.add("10");
//        list.add(1 + "");
//        list.add(1 + "");
//        List<String> result = bb.write(1, list);
//        System.out.println(result);

    }
}
