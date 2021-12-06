package BackEnd.Gateways;

import BackEnd.Interfaces.ReadNameID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Teacher gateway.
 */
public class TeacherGateway extends Gateway implements ReadNameID {


    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        String sql = "select * from TEACHER where id = " + targetID;
        return new ArrayList<>(read(sql, type, elementStructure));
    }

    @Override
    public List<String> readIntByName(int type, String targetName) {
        String sql = "select * from TEACHER where name = '" + targetName + "'";
        return new ArrayList<>(read(sql, type, INT));
    }

    @Override
    public List<String> readRow(int targetID) {
        try {
            ArrayList<String> result = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from TEACHER where id = " + targetID;
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                statement.close();
                connection.close();
                return result;
            }
            result.add(resultSet.getInt(1) + "");
            for (int i = 2; i < 8; i++) {
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

        //add a new teacher: info:{name, pass, email} --> {teacherID}/null
        if (type == ID) {
            String teacherName = info.get(0);
            String teacherPass = info.get(1);
            String teacherEmail = info.get(2);
            String temp = newTeacher(teacherName, teacherPass, teacherEmail);
            if (!temp.equals(FAILED)) {
                result.add(temp);
            }
            return result;
        }


        //change one element: info:{teacherID, newInfo} --> {teacherID}/null
        int teacherID = Integer.parseInt(info.get(0));
        String sql = null;
        int EMAIL = 5;
        int PASS = 3;
        int GROUPS = 6;
        int TESTS = 7;
        if (type == NAME) {
            String newName = info.get(1);
            sql = "update TEACHER set name = '" + newName + "' where id = " + teacherID;
        } else if (type == PASS) {
            String newStudentIDs = info.get(1);
            sql = "update TEACHER set pass = '" + newStudentIDs + "' where id = " + teacherID;
        } else if (type == EMAIL) {
            String newEmail = info.get(1);
            sql = "update TEACHER set email = '" + newEmail + "' where id = " + teacherID;
        } else if (type == GROUPS) {
            String newGroups = info.get(1);
            sql = "update TEACHER set groupID =  CONCAT_WS(',',groupID, '" + newGroups + "') where id = " + teacherID;
        } else if (type == TESTS) {
            String newTests = info.get(1);
            sql = "update TEACHER set testID =  CONCAT_WS(',',testID, '" + newTests + "') where id = " + teacherID;
        } else if (type == 22) {
            String newGroups = info.get(1);
            sql = "update TEACHER set groupID = '" + newGroups + "' where id = " + teacherID;
        } else if (type == 33) {
            String newTests = info.get(1);
            sql = "update TEACHER set testID = '" + newTests + "' where id = " + teacherID;
        }
        if (rewrite(sql).equals(SUCCESS)) {
            result.add(teacherID + "");
        }

        return result;
    }

    private String newTeacher(String name, String pass, String email) {

        if (hasDuplicateNames("STUDENT", name)) {
            return FAILED;
        }
        if (hasDuplicateNames("TEACHER", name)) {
            return FAILED;
        }

        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());//get date
        String groupID = "";
        String testID = "";

        try {
            Connection connection = getConnection();

            //register teacher
            String sql = "insert into TEACHER (name,pass,date,email,groupID,testID) VALUE (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, groupID);
            preparedStatement.setString(6, testID);
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
