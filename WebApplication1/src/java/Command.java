import java.sql.*;
//import java.util.Properties;
//import java.util.Random;
//import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;

public class Command {
    public final int SUCCESS = 0;
    public final int FAILED = -1;
    public final int USERNAMEALREADYUSED = -2;
    public final int GROUPALREADYJOINED = -3;
    //    public final int NOTREGISTERED = -4;
//    public final int TESTNAMEALREADYUSED = -5;
//    public final int QUESTIONNAMEALREADYUSED = -6;
    public final int TEACHER = 11;
    public final int STUDENT = 12;


    public final int GETGROUPNAME = 21;
    public final int GETGROUPTEACHER = 22;
    public final int GETGROUPSTUDENT = 23;
    public final int GETGROUPANNOUNCEMENT = 24;
//    public final int GETGROUPTEST = 25;

//    public final int GETSTUDENTFINSHEDTEST = 31;
//    public final int GETSTUDENTTODOTEST = 32;

    public final String STUDENTTABLENAME = "STUDENT";
    public final String TEACHERTABLENAME = "TEACHER";
    public final String GROUPTABLENAME = "STUDYGROUP";
    public final String QUESTIONTABLENAME = "QUESTION";
    public final String QUESTIONANSWERTABLENAME = "QUESTIONANSWER";
//    public final String TESTTABLENAME = "TEST";


    public final int NAMECOLINGROUP = 2;
    public final int TEACHERIDCOLINGROUP = 3;
    public final int STUDENTIDCOLINGROUP = 4;
    public final int ANNOUNCEMENTCOLINGROUP = 5;
    public final int TESTIDCOLINGROUP = 6;

    public final int GROUPIDCOLINTEACHER = 6;
    public final int GROUPIDCOLINSTUDENT = 7;
//    public final int EMAILINSTUDENT = 5;
//    public final int EMAILINTEACHER = 5;

    public final int ANSWERCOLINQUESTION = 4;
    public final int ANSWERCOLINQUESTIONANSWER = 3;
    //    public final int MARKCOLINQUESTIONANSWER = 3;
    public final int QUESTIONIDCOLINQUESTIONANSWER = 2;


    public String driver = "com.mysql.cj.jdbc.Driver";//驱动程序名
    public String url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5449780";//url指向要访问的数据库study
    public String user = "sql5449780";//MySQL配置时的用户名
    public String password = "HNzHR6WEhn";//MySQL配置时的密码
    public Connection connection = null;

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

    public int removeGroupFromUser(int userID, int groupID, int userType, Connection connection, Statement statement) {
        String table;
        int col;
        String updateSql;
        if (userType == STUDENT) {
            table = STUDENTTABLENAME;
            col = GROUPIDCOLINSTUDENT;
            updateSql = "update STUDENT set groupID = ? where id = ?";

        } else {
            table = TEACHERTABLENAME;
            col = GROUPIDCOLINTEACHER;
            updateSql = "update TEACHER set groupID = ? where id = ?";
        }
        try {
            ////get all the groups creates by the user
            String sql = "select * from " + table + " where id='" + userID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            ////get all ids of groups created by the user
            boolean hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String allGroups = resultSet.getString(col);//in the form of "21,20,23,4"
            ////remove groupID from the string
            if (!isInString(allGroups, groupID, ",")) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String newGroups = removeIDFromString(allGroups, groupID, ",");
            ////rewrite the new string to the database
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, newGroups);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
//            statement.close();
//            connection.close();
            return SUCCESS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }
    }

    public int removeUserFromGroup(int userID, int groupID, Connection connection, Statement statement) {
        String table = "STUDYGROUP";
        int col = 4;
        String updateSql = "update STUDYGROUP set students = ? where id = ?";
        try {
            ////get all the users in this group
            String sql = "select * from " + table + " where id='" + groupID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            ////get all ids of users in this group
            boolean hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String allusers = resultSet.getString(col);//in the form of "21,20,23,4"
            ////remove groupID from the string
            //////if group not found, return failed
            if (!isInString(allusers, userID, ",")) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String newusers = removeIDFromString(allusers, userID, ",");
            ////rewrite the new string to the database
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, newusers);
            preparedStatement.setInt(2, groupID);
            preparedStatement.executeUpdate();
//            statement.close();
//            connection.close();
            return SUCCESS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }
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
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(id + "")) {
                result = array[i] + ",";
            }
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

//    public int setRandomPass(int userType, String username, int pass, Statement statement, Connection connection) {
//        try {
//            String table;
//            int col;
//            String updateSql;
//            if (userType == STUDENT) {
//                table = STUDENTTABLENAME;
//                col = GROUPIDCOLINSTUDENT;
//                updateSql = "update STUDENT set pass = ? where name = ?";
//
//            } else {
//                table = TEACHERTABLENAME;
//                col = GROUPIDCOLINTEACHER;
//                updateSql = "update TEACHER set pass = ? where name = ?";
//            }
//
//
//            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
//            preparedStatement.setString(1, pass + "");
//            preparedStatement.setString(2, username);
//            preparedStatement.executeUpdate();
//            statement.close();
//            connection.close();
//            return SUCCESS;
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return FAILED;
//        }
//
//
//    }
//
//    public void sendMail(String email, String pass) {
//
//        // 收件人电子邮箱
//        String to = email;
//
//        // 发件人电子邮箱
//        String from = "zhanni2020@gmail.com";
//
//        // 指定发送邮件的主机为 localhost
//        String host = "localhost";
//
//        // 获取系统属性
//        Properties properties = System.getProperties();
//
//
//        // 设置邮件服务器
//        properties.setProperty("mail.smtp.host", host);
//
//        // 获取默认session对象
//        Session session = Session.getDefaultInstance(properties);
//
//        try {
//            // 创建默认的 MimeMessage 对象
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: 头部头字段
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: 头部头字段
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(to));
//
//            // Set Subject: 头部头字段
//            message.setSubject("Password Changed");
//
//            // 设置消息体
//            message.setText("Your password is now: " + pass + ".");
//
//            // 发送消息
//            Transport.send(message);
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//
//
//    }

    public Object execute() {
        return -1;
    }
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
        try {
            getConnection();
            //check if the user is a student
            Statement statement = connection.createStatement();
            String sql = "select * from " + STUDENTTABLENAME + " where name='" + name + "' and pass='" + pass + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            ////check if there is a match
            boolean returnValue = resultSet.next();
            if (returnValue) {
                int studentID = resultSet.getInt(1);
                statement.close();
                connection.close();
                return studentID;
            }

            //check if the user is a teacher
            statement = connection.createStatement();
            sql = "select * from " + TEACHERTABLENAME + " where name='" + name + "' and pass='" + pass + "'";
            resultSet = statement.executeQuery(sql);
            ////check if there is a match
            returnValue = resultSet.next();
            if (returnValue) {
                int teacherID = resultSet.getInt(1);
                statement.close();
                connection.close();
                return teacherID;
            }
            //if not both, return FAILED
            statement.close();
            connection.close();
            return FAILED;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
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
        try {
            getConnection();
            //check if id appears in student table
            Statement statement = connection.createStatement();
            String sql = "select * from STUDENT where id='" + id + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            //////check if there is a match
            boolean returnValue = resultSet.next();
            if (returnValue) {
                statement.close();
                connection.close();
                return STUDENT;
            }
            ////check if id appears in teacher table
            sql = "select * from TEACHER where id='" + id + "'";
            resultSet = statement.executeQuery(sql);
            //////check if there is a match
            returnValue = resultSet.next();
            if (returnValue) {
                statement.close();
                connection.close();
                return TEACHER;
            }
            return FAILED;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }
}

//String name, String pass, String email, int type(teacher11, student12)-> USERNAMEALREADYUSED/SUCCESS/FAILED
class registerCommand extends Command {
    private final String name;
    private final String pass;
    private final String email;
    private final int type;

    public registerCommand(String name, String pass, String email, int type) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.type = type;
    }

    @Override
    public Object execute() {
        try {
            getConnection();
            //check if username already exists
            ////check if name is used by a student
            Statement statement = connection.createStatement();
            String sql = "select * from STUDENT where name='" + name + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            //////check if there is a match
            boolean returnValue = resultSet.next();
            if (returnValue) {
                statement.close();
                connection.close();
                return USERNAMEALREADYUSED;
            }
            ////check if name is used by a teacher
            sql = "select * from TEACHER where name='" + name + "'";
            resultSet = statement.executeQuery(sql);
            //////check if there is a match
            returnValue = resultSet.next();
            if (returnValue) {
                statement.close();
                connection.close();
                return USERNAMEALREADYUSED;
            }

            //register
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());//get date
            String groupID = "";//set groupID to []
            String testID = "";// set testID to 0 refers no test
            String answerID = "";
            ////register as a student:id(auto-generated)|name|pass|date|email|words|groupID|level
            if (type == STUDENT) {
                String words = "";//set words to []
                int level = 1;//set level to 1
                sql = "insert into STUDENT (name,pass,date,email,words,groupID,testID,answerID,level) VALUE (?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, pass);
                preparedStatement.setDate(3, sqlDate);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, words);
                preparedStatement.setString(6, groupID);
                preparedStatement.setString(7, testID);
                preparedStatement.setString(8, answerID);
                preparedStatement.setInt(9, level);
                preparedStatement.executeUpdate();
                statement.close();
                connection.close();
            }
            ////register as a teacher:id|name|pass|date|email|groupID
            else {
                sql = "insert into TEACHER (name,pass,date,email,groupID,testID) VALUE (?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, pass);
                preparedStatement.setDate(3, sqlDate);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, groupID);
                preparedStatement.setString(6, testID);
                preparedStatement.executeUpdate();
                statement.close();
                connection.close();
            }
            return SUCCESS;


        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
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
        try {
            getConnection();
            Statement statement = connection.createStatement();
            //remove group from student
            int result = removeGroupFromUser(studentID, groupID, STUDENT, connection, statement);
            if (result != SUCCESS) {
                return FAILED;
            }
            //remove student from group
            ////get all the students for the group
            String sql = "select * from " + GROUPTABLENAME + " where id='" + groupID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            ////get all ids of groups joined by the student
            boolean hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String allStudents = resultSet.getString(STUDENTIDCOLINGROUP);//in the form of "21,20,23,4"
            ////remove studentID from the string
            //////if student not found, return failed
            if (!isInString(allStudents, studentID, ",")) {
                statement.close();
                connection.close();
                return FAILED;
            }
            ////remove studentID from the group
            String newStudents = removeIDFromString(allStudents, studentID, ",");
            ////rewrite the new string to the database
            sql = "update STUDYGROUP set students = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newStudents);
            preparedStatement.setInt(2, groupID);
            preparedStatement.executeUpdate();

            statement.close();
            connection.close();
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
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
        try {
            getConnection();
            //add group to student
            ////get all the groups joined by the student
            Statement statement = connection.createStatement();
            String sql = "select * from " + STUDENTTABLENAME + " where id='" + studentID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            ////get all ids of groups joined by the student
            resultSet.next();
            String allGroups = resultSet.getString(GROUPIDCOLINSTUDENT);//in the form of "1,2,3,4"
            ////add groupID to the string
            if (isInString(allGroups, groupID, ",")) {
                statement.close();
                connection.close();
                return GROUPALREADYJOINED;
            }
            if (allGroups.length() == 0) {
                allGroups = groupID + "";
            } else {
                allGroups = allGroups + "," + groupID;
            }
            ////rewrite the new string to the database
            sql = "update STUDENT set groupID = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, allGroups);
            preparedStatement.setInt(2, studentID);
            preparedStatement.executeUpdate();


            //add student to group
            ////get all the students form the group
            sql = "select * from " + GROUPTABLENAME + " where id='" + groupID + "'";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            String allStudents = resultSet.getString(STUDENTIDCOLINGROUP);
            ////add studentID to the group
            if (allStudents.length() == 0) {
                allStudents = studentID + "";
            } else {
                allStudents = studentID + "," + allStudents;
            }
            ////rewrite the new string to the database
            sql = "update STUDYGROUP set students = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, allStudents);
            preparedStatement.setInt(2, groupID);
            preparedStatement.executeUpdate();
            statement.close();
            connection.close();
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }


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
        try {
            getConnection();
            //check if group already exists
            Statement statement = connection.createStatement();
            String sql = "select * from STUDYGROUP where name='" + groupName + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            //////check if there is a match
            boolean hasMatch = resultSet.next();
            if (hasMatch) {
                statement.close();
                connection.close();
                return USERNAMEALREADYUSED;
            }

            //create
            String students = "";
            String posts = "";
            String testID = "";
            ////register a group:id(auto-generated)|name|creator|students|posts
            sql = "insert into STUDYGROUP (name,creator,students,posts,testID) VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, groupName);
            preparedStatement.setInt(2, teacherID);
            preparedStatement.setString(3, students);
            preparedStatement.setString(4, posts);
            preparedStatement.setString(5, testID);
            preparedStatement.executeUpdate();
            //return SUCCESS;
            //get group id
            sql = "select * from " + GROUPTABLENAME + " where name='" + groupName + "'";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int groupID = resultSet.getInt(1);
            //add group to teacher
            sql = "select * from " + TEACHERTABLENAME + " where id='" + teacherID + "'";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            String allGroups = resultSet.getString(6);
            if (allGroups.length() == 0) {
                allGroups = groupID + "";
            } else {
                allGroups = groupID + "," + allGroups;
            }
            sql = "update TEACHER set groupID = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, allGroups);
            preparedStatement.setInt(2, teacherID);
            preparedStatement.executeUpdate();

            statement.close();
            connection.close();
            return groupID;

        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
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
        try {
            getConnection();
            Statement statement = connection.createStatement();
            //remove group from teacher
            int result = removeGroupFromUser(teacherID, groupID, TEACHER, connection, statement);
            if (result != SUCCESS) {
                statement.close();
                connection.close();
                return FAILED;
            }
            //remove group from students
            String sql = "select * from " + GROUPTABLENAME + " where id='" + groupID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            boolean hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String allStudents = resultSet.getString(STUDENTIDCOLINGROUP);//in the form of "21,20,23,4 "

            String[] students = allStudents.trim().split(",");
            for (String student : students) {
                removeGroupFromUser(Integer.parseInt(student), groupID, STUDENT, connection, statement);
            }
            //delete the group from the group table
            sql = "delete from STUDYGROUP where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, groupID);
            preparedStatement.executeUpdate();
            statement.close();
            connection.close();
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }

    }
}

////String name -> SUCCESS/FAILED/NOTREGISTERED
//class forgetPassCommand extends Command {
//    String name;
//
//    public forgetPassCommand(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public Object execute() {
//        try {
//            int type;
//            String email = null;
//            getConnection();
//            //check if the email is registered as student
//            Statement statement = connection.createStatement();
//            String sql = "select * from " + STUDENTTABLENAME + " where name='" + name + "'";
//            ResultSet resultSet = statement.executeQuery(sql);
//            ////check if there is a match
//            boolean returnValue = resultSet.next();
//            if (returnValue) {
//                email = resultSet.getString(EMAILINSTUDENT);
//                type = STUDENT;
//                //set password to a random int
//            } else {
//                //check if the user is a teacher
//                statement = connection.createStatement();
//                sql = "select * from " + TEACHERTABLENAME + " where name='" + name + "'";
//                resultSet = statement.executeQuery(sql);
//                ////check if there is a match
//                returnValue = resultSet.next();
//                if (returnValue) {
//                    email = resultSet.getString(EMAILINTEACHER);
//                    type = TEACHER;
//                } else {
//                    statement.close();
//                    connection.close();
//                    return NOTREGISTERED;
//                }
//            }
//            //if not both, return Not registered
//            if (email == null) {
//                statement.close();
//                connection.close();
//                return NOTREGISTERED;
//            } else {
//                Random rand = new Random();
//                int upperbound = 20020313;
//                int int_random = rand.nextInt(upperbound);
//                int result = setRandomPass(type, name, int_random, statement, connection);
//                statement.close();
//                connection.close();
//                if (result != SUCCESS) {
//                    return FAILED;
//                } else {
//                    sendMail(email, int_random + "");
//                    return SUCCESS;
//                }
//            }
//
//        } catch (
//                SQLException e) {
//            e.printStackTrace();
//            return FAILED;
//        }
//
//    }
//}

//String test name, String author, int price -> SUCCESS/FAILED
class createTestCommand extends Command {
    private final String name;
    private final int author;
    private final int price;

    public createTestCommand(String name, int author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public Object execute() {
        try {
            getConnection();
            //check if test name already exists
            Statement statement = connection.createStatement();
//            String sql = "select * from TEST where name='" + name + "'";
//            ResultSet resultSet = statement.executeQuery(sql);
            //check if there is a match
//            boolean returnValue = resultSet.next();
//            if (returnValue) {
//                statement.close();
//                connection.close();
//                return TESTNAMEALREADYUSED;
//            }
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());//get date
            String questions = "";// set questionsID to 0 refers no test
            String sql = "insert into TEST (name,author,date,price,questions) VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, author);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setInt(4, price);
            preparedStatement.setString(5, questions);
            preparedStatement.executeUpdate();
            statement.close();
            connection.close();
            return SUCCESS;


        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }
}

//string question name, string question, string answer -> SUCCESS/FAILED
class addQuestationCommand extends Command {
    private final String name;
    private final String question;
    private final String answer;

    public addQuestationCommand(String name, String question, String answer) {
        this.name = name;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public Object execute() {
        try {
            getConnection();
            //check if test name already exists
            Statement statement = connection.createStatement();
            String sql;
//            ResultSet resultSet = statement.executeQuery(sql);
//            //////check if there is a match
//            boolean returnValue = resultSet.next();
//            if (returnValue) {
//                statement.close();
//                connection.close();
//                return QUESTIONNAMEALREADYUSED;
//            }
            sql = "insert into QUESTION (name,question,answer) VALUE (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, question);
            preparedStatement.setString(3, answer);
            preparedStatement.executeUpdate();
            statement.close();
            connection.close();
            return SUCCESS;


        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }
}

//class releaseMarkCommand extends Command {
//
//    @Override
//    public int execute() {
//
//    }
//}
//
//class inviteCommand extends Command {
//
//    @Override
//    public int execute() {
//
//    }
//}
//
//int teacherID, int studentID, int groupID ->Success/failed
class deleteMemberCommand extends Command {
    int teacherID;
    int studentID;
    int groupID;

    public deleteMemberCommand(int teacherID, int studentID, int groupID) {
        this.studentID = studentID;
        this.groupID = groupID;
        this.teacherID = teacherID;
    }

    @Override
    public Object execute() {
        try {
            getConnection();
            Statement statement = connection.createStatement();
            //remove group from students
            String sql = "select * from " + GROUPTABLENAME + " where id='" + groupID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            boolean hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return FAILED;
            }
            removeGroupFromUser(studentID, groupID, STUDENT, connection, statement);
//            getConnection();
//            Statement statement1 = connection.createStatement();

            removeUserFromGroup(studentID, groupID, connection, statement);

            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }

    }

}

//String content -> SUCCESS/FAILED
class createAnnouncementCommand extends Command {
    private final String announcement;

    public createAnnouncementCommand(String announcement) {
        this.announcement = announcement;
    }

    @Override
    public Object execute() {
        try {
            getConnection();
            String query = "update STUDYGROUP set posts = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, announcement);
            preparedStatement.executeUpdate();
            connection.close();
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
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

    @Override
    public Object execute() {
        try {
            getConnection();
            Statement statement = connection.createStatement();

            //remove group from students
            String sql = "select * from " + "TEST" + " where id='" + testID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            boolean hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String allTests = resultSet.getString(6);
            if (allTests.length() == 0) {
                allTests = "" + questionID;
            } else {
                allTests = allTests + "," + questionID;
            }
            //delete the group from the group table
            sql = "update TEST set questions = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, allTests);
            preparedStatement.setInt(2, testID);
            preparedStatement.executeUpdate();
            statement.close();
            connection.close();
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }

    }
}

//int student id, string answer, int question id -> SUCCESS/FAILED
class submitCommand extends Command {
    private final String answer;
    private final int questionID;
    private final int studentID;

    public submitCommand(int studentID, String answer, int questionID) {
        this.answer = answer;
        this.questionID = questionID;
        this.studentID = studentID;
    }

    @Override
    public Object execute() {
        try {
            getConnection();
            //check if test name already exists
            Statement statement = connection.createStatement();
            String sql = "insert into QUESTIONANSWER (questionID,answer,mark,studentID) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, questionID);
            preparedStatement.setString(2, answer);
            preparedStatement.setInt(3, FAILED);
            preparedStatement.setInt(4, studentID);
            preparedStatement.executeUpdate();
            statement.close();
            connection.close();
            Command c = new gradeQuestion();
            c.execute();
            return SUCCESS;


        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }
}


//int GroupID,int type-> String group name/teacher/student ids/announcement/test ids
class getGroupInfo extends Command {
    private final int groupID;
    private final int infoCol;


    public getGroupInfo(int id, int type) {
        this.groupID = id;
        if (type == GETGROUPNAME) {
            this.infoCol = NAMECOLINGROUP;
        } else if (type == GETGROUPTEACHER) {
            this.infoCol = TEACHERIDCOLINGROUP;
        } else if (type == GETGROUPSTUDENT) {
            this.infoCol = STUDENTIDCOLINGROUP;
        } else if (type == GETGROUPANNOUNCEMENT) {
            this.infoCol = ANNOUNCEMENTCOLINGROUP;
        } else { //if (type == GETGROUPTEST) {
            this.infoCol = TESTIDCOLINGROUP;
        }

    }

    @Override
    public Object execute() {
        try {
            getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from " + GROUPTABLENAME + " where id='" + groupID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            boolean hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String result = resultSet.getString(infoCol);
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }

    }
}

//int studentID, int question answer ID --> mark/FAILED
class gradeQuestion extends Command {

    public gradeQuestion() {

    }

    @Override
    public Object execute() {

        try {
            getConnection();
            Statement statement = connection.createStatement();

            //get student answer
            String sql = "select * from " + QUESTIONANSWERTABLENAME + " where mark='" + FAILED + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            boolean hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return FAILED;
            }
            int id = resultSet.getInt(1);
            String studentAnswer = resultSet.getString(ANSWERCOLINQUESTIONANSWER);
            String questionID = resultSet.getString(QUESTIONIDCOLINQUESTIONANSWER);
            //get correct answer
            sql = "select * from " + QUESTIONTABLENAME + " where id='" + questionID + "'";
            resultSet = statement.executeQuery(sql);
            hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String correctAnswer = resultSet.getString(ANSWERCOLINQUESTION);

            //give student a mark of 0/1
            int studentMark;
            if (correctAnswer.equals(studentAnswer)) {
                studentMark = 1;
            } else {
                studentMark = 0;
            }

            //write the mark into database
            String sql1 = "update QUESTIONANSWER set mark = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, studentMark);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();


            statement.close();
            connection.close();

            return studentMark;

        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }

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
        try {
            getConnection();
            Statement statement = connection.createStatement();

            //get student answer
            String sql = "select * from " + "TEST" + " where id='" + testID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            boolean hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String questions = resultSet.getString(6);
            String[] question = questions.trim().split(",");
            int sum = 0;
            for (String q : question) {
                sql = "select * from " + "QUESTIONANSWER" + " where questionID='" + q + "' and studentID='" + studentID + "'";
                resultSet = statement.executeQuery(sql);
                hasMatch = resultSet.next();
                if (!hasMatch) {
                    statement.close();
                    connection.close();
                    return FAILED;
                }
                int marks = resultSet.getInt(4);
                sum += marks;
            }
            sql = "insert into TESTANSWER (testID,studentID,mark) VALUE (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, testID);
            preparedStatement.setInt(2, studentID);
            preparedStatement.setInt(3, sum);
            preparedStatement.executeUpdate();
            sql = "select * from " + STUDENTTABLENAME + " where id='" + studentID + "'";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            String alltests = resultSet.getString(8);//in the form of "1,2,3,4"
            ////add groupID to the string
            if (isInString(alltests, testID, ",")) {
                statement.close();
                connection.close();
                return GROUPALREADYJOINED;
            }
            if (alltests.length() == 0) {
                alltests = testID + " ";
            } else {
                alltests = alltests + "," + testID;
            }
            ////rewrite the new string to the database
            sql = "update STUDENT set testID = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alltests);
            preparedStatement.setInt(2, studentID);
            preparedStatement.executeUpdate();

            statement.close();
            connection.close();
            Command c = new gradeQuestion();
            c.execute();
            return SUCCESS;

        } catch (SQLException e) {
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
            String sql = "select * from " + "TESTANSWER" + " where studentID='" + studentID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            boolean hasMatch = resultSet.next();
            if (!hasMatch) {
                statement.close();
                connection.close();
                return 0;
            }
            int time = 1;
            int total = resultSet.getInt(4);
            while (resultSet.next()) {
                total += resultSet.getInt(4);
                time += 1;
            }
            return total * 1.0 / time;

        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }
}
//int studentID, int groupID, int type --> String toodo/finished test ids
//class getStudentTest extends Command {
//
//}
//getWords
