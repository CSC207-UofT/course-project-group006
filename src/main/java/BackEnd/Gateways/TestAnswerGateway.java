package BackEnd.Gateways;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Test answer gateway.
 */
public class TestAnswerGateway extends Gateway{
    private final int testID = 2;
    private final int studentID = 3;
    private final int MARK = 4;

    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        String sql = "select * from TESTANSWER where id = " + targetID;
        return new ArrayList<>(read(sql, type, elementStructure));
    }
    // no name in TESTANSWER table

    @Override
    public List<String> readRow(int targetID) {
        try {
            ArrayList<String> result = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from TESTANSWER where id = " + targetID;
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                statement.close();
                connection.close();
                return result;
            }
            result.add(resultSet.getInt(1) + "");
            for (int i = 2; i < 5; i++) {
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

        if (type == ID) {
            int testID = Integer.parseInt(info.get(0));
            int studentID = Integer.parseInt(info.get(1));
            String temp = newTestAnswer(testID, studentID);
            if (!temp.equals(FAILED)) {
                result.add(temp);
            }
            return result;
        }


        //change one element: info:{studentID, newInfo} --> {studentID}/null
        int TestAnswerID = Integer.parseInt(info.get(0));
        String sql = null;
        if (type == testID) {
            String newTestID = info.get(1);
            sql = "update TESTANSWER set testID = '" + newTestID + "' where id = " + TestAnswerID;
        } else if (type == MARK) {
            String newMark = info.get(1);
            sql = "update TESTANSWER set mark = '" + newMark + "' where id = " + TestAnswerID;
        } else if (type == studentID) {
            String newStudentID = info.get(1);
            sql = "update TESTANSWER set studentID = '" + newStudentID + "' where id = " + TestAnswerID;

        }
        if (rewrite(sql).equals(SUCCESS)) {
            result.add(TestAnswerID + "");
        }

        return result;
    }

    private String newTestAnswer(int testID, int studentID) {
        int mark = -1;

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "insert into TESTANSWER (testID,studentID,mark) VALUE (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, testID);
            preparedStatement.setInt(2, studentID);
            preparedStatement.setInt(3, mark);

            String result = createGetID(preparedStatement);
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }


    }
}
