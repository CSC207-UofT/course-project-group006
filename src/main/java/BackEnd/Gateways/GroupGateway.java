package BackEnd.Gateways;


import BackEnd.Interfaces.ReadAll;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Group gateway.
 */
public class GroupGateway extends Gateway implements ReadAll {

    //private final int CREATOR = 3;

    /**
     * Read by ID
     * @param elementStructure the element structure
     * @param type             the type
     * @param targetID         the target id
     * @return List in database
     */
    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        String sql = "select * from STUDYGROUP where id = " + targetID;
        return new ArrayList<>(read(sql, type, elementStructure));
    }


    /**
     * Read rows
     * @param targetID
     * @return A list with information read from rows in database
     */
    @Override
    public List<String> readRow(int targetID) {
        try {
            ArrayList<String> result = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from STUDYGROUP where id = " + targetID;
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                statement.close();
                connection.close();
                return result;
            }
            result.add(resultSet.getInt(1) + "");
            for (int i = 2; i < 9; i++) {
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

    /**
     * Write information
     * @param type the type
     * @param info the info
     * @return A list of groups or info
     */
    @Override
    public List<String> write(int type, List<String> info) {

        List<String> result = new ArrayList<>();

        //add a new group: info:{teacherID, groupName} --> {groupID}/null
        if (type == ID) {
            int teacherID = Integer.parseInt(info.get(0));
            String groupName = info.get(1);
            String temp = newGroup(teacherID, groupName);
            if (!temp.equals(FAILED)) {
                result.add(temp);
            }
            return result;
        }

        //change one element: info:{groupID, newInfo} --> {groupID}/null
        int groupID = Integer.parseInt(info.get(0));
        String sql = null;
        int STUDENTS = 4;
        int POST = 5;
        int TESTS = 6;
        if (type == NAME) {
            String newName = info.get(1);
            sql = "update STUDYGROUP set name = '" + newName + "' where id = " + groupID;
        } else if (type == STUDENTS) {
            String newStudentID = info.get(1);
            sql = "update STUDYGROUP set students =  CONCAT_WS(',',students, '" + newStudentID + "') where id = " + groupID;
        } else if (type == POST) {
            String newPost = info.get(1);
            sql = "update STUDYGROUP set posts = '" + newPost + "' where id = " + groupID;
        } else if (type == TESTS) {
            String newTest = info.get(1);
            sql = "update STUDYGROUP set testID =  CONCAT_WS(',',testID, '" + newTest + "') where id = " + groupID;
        }
        else if (type == 7) {
            String newInfo = info.get(1);
            sql = "update STUDYGROUP set answerString = '" + newInfo + "' where id = " + groupID;
        }
        else if (type == 8) {
            String newInfo = info.get(1);
            sql = "update STUDYGROUP set duedates = '" + newInfo + "' where id = " + groupID;
        } else if (type == 22) {
            String newStudents = info.get(1);
            sql = "update STUDYGROUP set students = '" + newStudents + "' where id = " + groupID;
        } else if (type == 33) {
            String newTests = info.get(1);
            sql = "update STUDYGROUP set testID = '" + newTests + "' where id = " + groupID;
        } else if (type == 44) {
            sql = "delete from STUDYGROUP where id = " + groupID;
        }
        if (rewrite(sql).equals(SUCCESS)) {
            result.add(groupID + "");
        }
        return result;

    }

    /**
     * Create a new group
     * @param teacherID Teacher's ID
     * @param groupName Name of the group
     * @return A created new group
     */
    private String newGroup(int teacherID, String groupName) {
        if (hasDuplicateNames("STUDYGROUP", groupName)) {
            return FAILED;
        }

        String students = "";
        String posts = "Add a new Announcement :D";
        String testID = "";

        try {
            Connection connection = getConnection();
            String sql = "insert into STUDYGROUP (name,creator,students,posts,testID,answerString,duedates) VALUE (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, groupName);
            preparedStatement.setInt(2, teacherID);
            preparedStatement.setString(3, students);
            preparedStatement.setString(4, posts);
            preparedStatement.setString(5, testID);
            preparedStatement.setString(6, "");
            preparedStatement.setString(7, "");


            String result = createGetID(preparedStatement);
            preparedStatement.close();
            connection.close();
            return result;


        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }

    }

    /**
     * Read everything
     * @return Info read
     */
    @Override
    public HashMap<Integer, String> readAll() {
        HashMap<Integer, String> result = new HashMap<>();
        String sql = "select * from STUDYGROUP";

        List<String> IDList = read(sql, ID, INT);
        List<String> nameList = read(sql, NAME, STRING);
        for (int i = 0; i < IDList.size(); i++) {
            result.put(Integer.parseInt(IDList.get(i)), nameList.get(i));
        }
        return result;

    }
}