package BackEnd.Gateways;

import BackEnd.Interfaces.ReadIDName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerGateway extends Gateway implements ReadIDName {
    /**
     * Read information by ID
     * @param elementStructure the element structure
     * @param type             the type
     * @param targetID         the target id
     * @return A list of information read by ID
     */
    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        String sql = "select * from QUESTIONANSWER where id = " + targetID;
        return new ArrayList<>(read(sql, type, elementStructure));
    }

    /**
     * Read information by ID name
     * @param elementStructure the element structure
     * @param studentID the student ID
     * @param type the type
     * @param targetID the target ID
     * @return A list of information read by ID name
     */
    @Override
    public List<String> readByIDName(int elementStructure,int studentID, int type, int targetID) {
        String sql = "select * from QUESTIONANSWER where questionID = ' " + targetID + " ' and studentID = '" + studentID + " '";
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
            String sql = "select * from QUESTIONANSWER where id = " + targetID;
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

    /**
     * Write information
     * @param type the type
     * @param info the info
     * @return A list of info
     */
    @Override
    public List<String> write(int type, List<String> info) {
        List<String> result = new ArrayList<>();

        //add a new questionAnswer: info:{questionID, answer, studentID, groupID} --> {studentID}/null
        if (type == ID) {
            int questionID = Integer.parseInt(info.get(0));
            String answer = info.get(1);
            int studentID = Integer.parseInt(info.get(2));
            int groupID = Integer.parseInt(info.get(3));
            String temp = newQuestionAnswer(questionID, answer, studentID, groupID);
            if (!temp.equals(FAILED)) {
                result.add(temp);
            }
            return result;
        }


        //change one element: info:{questionAnswerID, newInfo} --> {studentID}/null
        int QuestionAnswerID = Integer.parseInt(info.get(0));
        String sql = null;
        int studentID1 = 5;
        int questionID1 = 2;
        int ANSWER = 3;
        int MARK = 4;
        int groupID1 = 6;
        if (type == questionID1) {
            String newQuestionID = info.get(1);
            sql = "update QUESTIONANSWER set questionID = '" + newQuestionID + "' where id = " + QuestionAnswerID;
        } else if (type == ANSWER) {
            String newAnswer = info.get(1);
            sql = "update QUESTIONANSWER set answer = '" + newAnswer+ "' where id = " + QuestionAnswerID;
        } else if (type == MARK) {
            String newMark = info.get(1);
            sql = "update QUESTIONANSWER set mark = '" + newMark + "' where id = " + QuestionAnswerID;
        } else if (type == groupID1) {
            String newGroupID = info.get(1);
            sql = "update QUESTIONANSWER set groupID = '" + newGroupID + "' where id = " + QuestionAnswerID;
        } else if (type == studentID1) {
            String newStudentID = info.get(1);
            sql = "update QUESTIONANSWER set studentID = '" + newStudentID + "' where id = " + QuestionAnswerID;

        }
        if (rewrite(sql).equals(SUCCESS)) {
            result.add(QuestionAnswerID + "");
        }

        return result;
    }

    /**
     * Insert a new question answer in database
     * @param questionID the question ID
     * @param answer the answer
     * @param studentID the student ID
     * @param groupID the group ID
     * @return a question answer
     */
    private String newQuestionAnswer(int questionID, String answer, int studentID, int groupID) {

        int mark = -1;

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "insert into QUESTIONANSWER (questionID,answer,mark,studentID,groupID) VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, questionID);
            preparedStatement.setString(2, answer);
            preparedStatement.setInt(3, mark);
            preparedStatement.setInt(4, studentID);
            preparedStatement.setInt(5, groupID);
//            preparedStatement.executeUpdate();
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
