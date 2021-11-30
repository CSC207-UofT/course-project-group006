package BackEnd.Gateways;


import BackEnd.ReadAll;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupGateway extends Gateway implements ReadAll {

    private final int CREATOR = 3;
    private final int STUDENTS = 4;
    private final int POST = 5;
    private final int TESTS = 6;


    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        String sql = "select * from STUDYGROUP where id = " + targetID;
        return new ArrayList<>(read(sql, type, elementStructure));
    }

    @Override
    public List<String> readIntByName(int type, String targetName) {
        String sql = "select * from STUDYGROUP where name = '" + targetName + "'";
        return new ArrayList<>(read(sql, type, INT));
    }

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
            for (int i = 2; i < 7; i++) {
                result.add(resultSet.getString(i));
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
        if (type == NAME) {
            String newName = info.get(1);
            sql = "update STUDYGROUP set name = '" + newName + "' where id = " + groupID;
        } else if (type == STUDENTS) {
            String newStudentIDs = info.get(1);
            sql = "update STUDYGROUP set students = '" + newStudentIDs + "' where id = " + groupID;
        } else if (type == POST) {
            String newPost = info.get(1);
            sql = "update STUDYGROUP set posts = '" + newPost + "' where id = " + groupID;
        } else if (type == TESTS) {
            String newTest = info.get(1);
            sql = "update STUDYGROUP set testID = '" + newTest + "' where id = " + groupID;
        }
        if (rewrite(sql).equals(SUCCESS)) {
            result.add(groupID + "");
        }
        return result;

    }


    private String newGroup(int teacherID, String groupName) {

        String students = "";
        String posts = "Add a new Announcement :D";
        String testID = "";

        try {
            Connection connection = getConnection();
            String sql = "insert into STUDYGROUP (name,creator,students,posts,testID) VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, groupName);
            preparedStatement.setInt(2, teacherID);
            preparedStatement.setString(3, students);
            preparedStatement.setString(4, posts);
            preparedStatement.setString(5, testID);

            return createGetID(preparedStatement);


        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }

    }


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