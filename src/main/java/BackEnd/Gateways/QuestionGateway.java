package BackEnd.Gateways;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Question gateway.
 */
public class QuestionGateway extends Gateway {
    /**
     * Read information from database by ID
     * @param elementStructure the element structure
     * @param type             the type
     * @param targetID         the target id
     * @return A list of information
     */
    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        String sql = "select * from QUESTION where id = " + targetID;
        return new ArrayList<>(read(sql, type, elementStructure));
    }

    /**
     * Read rows
     * @param targetID the target ID
     * @return A list of strings containing information in database in rows
     */
    @Override
    public List<String> readRow(int targetID) {
        try {
            ArrayList<String> result = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from QUESTION where id = " + targetID;
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                statement.close();
                connection.close();
                return result;
            }
            result.add(resultSet.getInt(1) + "");
            for (int i = 2; i < 6; i++) {
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
     * @return A list of info
     */
    @Override
    public List<String> write(int type, List<String> info) {

        List<String> result = new ArrayList<>();

        //add a new question: info:{name, question, answer, mark} --> {questionID}/null
        if (type == ID) {
            String questionName = info.get(0);
            String questionQuestion = info.get(1);
            String questionAnswer = info.get(2);
            int questionMark = Integer.parseInt(info.get(3));
            String temp = newQuestion(questionName, questionQuestion, questionAnswer, questionMark);
            if (!temp.equals(FAILED)) {
                result.add(temp);
            }
            return result;
        }


        //change one element: info:{questionID, newInfo} --> {studentID}/null
        int questionID = Integer.parseInt(info.get(0));
        String sql = null;
        int NAME = 2;
        int QUESTION = 3;
        int ANSWER = 4;
        int MARK = 5;
        if (type == NAME) {
            String newName = info.get(1);
            sql = "update QUESTION set name = '" + newName + "' where id = " + questionID;
        } else if (type == QUESTION) {
            String newQuestion = info.get(1);
            sql = "update QUESTION set question = '" + newQuestion + "' where id = " + questionID;
        } else if (type == ANSWER) {
            String newAnswer = info.get(1);
            sql = "update QUESTION set answer = '" + newAnswer + "' where id = " + questionID;
        } else if (type == MARK) {
            String newMark = info.get(1);
            sql = "update QUESTION set mark = '" + newMark + "' where id = " + questionID;
        }

        if (rewrite(sql).equals(SUCCESS)) {
            result.add(questionID + "");
        }

        return result;
    }

    /**
     * New question string.
     *
     * @param name     the name
     * @param question the question
     * @param answer   the answer
     * @param mark     the mark
     * @return the string
     */
    public String newQuestion(String name, String question, String answer, int mark) {

        try {
            Connection connection = getConnection();
            String sql = "insert into QUESTION (name,question,answer,mark) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, question);
            preparedStatement.setString(3, answer);
            preparedStatement.setInt(4, mark);
            String result = createGetID(preparedStatement);
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }


}
