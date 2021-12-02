package BackEnd.Gateways;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestGateway extends Gateway{

    private final int AUTHOR = 10;
    private final int PRICE = 12;
    private final int QUESTIONS = 13;

    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        String sql = "select * from TEST where id = " + targetID;
        return new ArrayList<>(read(sql, type, elementStructure));
    }

    @Override
    public List<String> readIntByName(int type, String targetName) {
        String sql = "select * from TEST where name = '" + targetName + "'";
        return new ArrayList<>(read(sql, type, INT));
    }

    @Override
    public List<String> readRow(int targetID) {
        try {
            ArrayList<String> result = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from TEST where id = " + targetID;
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                statement.close();
                connection.close();
                return result;
            }
            result.add(resultSet.getInt(1) + "");
            for (int i = 2; i < 7; i++) {
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

        //add a new test: info:{name, author, price} --> {testID}/null
        if (type == ID) {
            String testName = info.get(0);
            int authorID = Integer.parseInt(info.get(1));
            int price = Integer.parseInt(info.get(2));
            String temp = newTest(testName, authorID, price);
            if (!temp.equals(FAILED)) {
                result.add(temp);
            }
            return result;
        }

        int testID = Integer.parseInt(info.get(0));
        String sql = null;
        if (type == NAME) {
            String newName = info.get(1);
            sql = "update TEST set name = '" + newName + "' where id = " + testID;
        } else if (type == AUTHOR) {
            String newAuthorID = info.get(1);
            sql = "update TEST set author =  '" + newAuthorID + "' where id = " + testID;
        } else if (type == PRICE) {
            String newPrice = info.get(1);
            sql = "update TEST set price = '" + newPrice + "' where id = " + testID;
        } else if (type == QUESTIONS) {
            String newQuestions = info.get(1);
            sql = "update TEST set questionIDs =  '" + newQuestions + "' where id = " + testID;
        } else if (type == 66) {
            String newQuestions = info.get(1);
            sql = "update TEST set questionIDs = CONCAT_WS(',',questionID, '" + newQuestions + "') where id = " + testID;
        }
        if (rewrite(sql).equals(SUCCESS)) {
            result.add(testID + "");
        }
        return result;
    }

    public String newTest(String testName, int authorID, int price){
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        String questions = "";

        try {
            Connection connection = getConnection();
            String sql = "insert into TEST (name,author,date,price,questions) VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, testName);
            preparedStatement.setInt(2, authorID);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setInt(4, price);
            preparedStatement.setString(5, questions);

            return createGetID(preparedStatement);


        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }

    }
}
