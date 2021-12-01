package BackEnd.Gateways;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Student gateway.
 */
public class StudentGateway extends Gateway {

    private final int PASS = 3;
    private final int DATE = 4;
    private final int EMAIL = 5;
    private final int WORDS = 6;
    private final int GROUPS = 7;
    private final int TESTS = 8;
    private final int ANSWERS = 9;
    private final int LEVEL = 10;

    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        String sql = "select * from STUDENT where id = " + targetID;
        return new ArrayList<>(read(sql, type, elementStructure));
    }

    @Override
    public List<String> readIntByName(int type, String targetName) {
        String sql = "select * from STUDENT where name = '" + targetName + "'";
        return new ArrayList<>(read(sql, type, INT));
    }

    @Override
    public List<String> readRow(int targetID) {
        try {
            ArrayList<String> result = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from STUDENT where id = " + targetID;
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                statement.close();
                connection.close();
                return result;
            }
            result.add(resultSet.getInt(1) + "");
            for (int i = 2; i < 11; i++) {
                String raw = resultSet.getString(i);
                result.add(raw.startsWith(",") ? raw.substring(1) : raw);
            }
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> write(int type, List<String> info) {

        List<String> result = new ArrayList<>();

        //add a new student: info:{name, pass, email} --> {studentID}/null
        if (type == ID) {
            String studentName = info.get(0);
            String studentPass = info.get(1);
            String studentEmail = info.get(2);
            String temp = newStudent(studentName, studentPass, studentEmail);
            if (!temp.equals(FAILED)) {
                result.add(temp);
            }
            return result;
        }


        //change one element: info:{studentID, newInfo} --> {studentID}/null
        int studentID = Integer.parseInt(info.get(0));
        String sql = null;
        if (type == NAME) {
            String newName = info.get(1);
            sql = "update STUDENT set name = '" + newName + "' where id = " + studentID;
        } else if (type == PASS) {
            String newStudentIDs = info.get(1);
            sql = "update STUDENT set pass = '" + newStudentIDs + "' where id = " + studentID;
        } else if (type == EMAIL) {
            String newEmail = info.get(1);
            sql = "update STUDENT set email = '" + newEmail + "' where id = " + studentID;
        } else if (type == GROUPS) {
            String newGroups = info.get(1);
            sql = "update STUDENT set groupID =  CONCAT_WS(',',groupID, '" + newGroups + "') where id = " + studentID;
        } else if (type == TESTS) {
            String newTests = info.get(1);
            sql = "update STUDENT set testID =  CONCAT_WS(',',testID, '" + newTests + "') where id = " + studentID;
        } else if (type == ANSWERS) {
            String newAnswers = info.get(1);
            sql = "update STUDENT set answerID =  CONCAT_WS(',',answerID, '" + newAnswers + "') where id = " + studentID;

        } else if (type == 22) {
            String newGroups = info.get(1);
            sql = "update STUDENT set groupID = '" + newGroups + "' where id = " + studentID;
        }
        if (rewrite(sql).equals(SUCCESS)) {
            result.add(studentID + "");
        }

        return result;
    }


    private String newStudent(String name, String pass, String email) {

        if (hasDuplicateNames("STUDENT", name)) {
            return FAILED;
        }
        if (hasDuplicateNames("TEACHER", name)) {
            return FAILED;
        }

        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());//get date


        //register as a student
        try {
            Connection connection = getConnection();
            String sql = "insert into STUDENT (name,pass,date,email,words,groupID,testID,answerID,level) VALUE (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, "");
            preparedStatement.setString(6, "");
            preparedStatement.setString(7, "");
            preparedStatement.setString(8, "");
            preparedStatement.setInt(9, 1);
            String result = createGetID(preparedStatement);
            preparedStatement.close();
            connection.close();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }

    }
}

