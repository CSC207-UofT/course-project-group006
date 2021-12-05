package BackEnd.Gateways;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerGateway extends Gateway{
    private final int QuestionID = 2;
    private final int ANSWER = 3;
    private final int MARK = 4;
    private final int StudentID = 5;
    private final int GroupID = 6;

    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        String sql = "select * from QUESTIONANSWER where id = " + targetID;
        return new ArrayList<>(read(sql, type, elementStructure));
    }
    // no name in QUESTIONANSWER table
    @Override
    public List<String> readIntByName(int type, String targetName) {
//        String sql = "select * from QUESTIONANSWER where questionID = '" + targetName + "'";
//        return new ArrayList<>(read(sql, type, INT));
        return null;
    }
    @Override
    public List<String> readByIDName(int elementStructure,int studentID, int type, int targetID) {
        String sql = "select * from QUESTIONANSWER where questionID = ' " + targetID + " ' and studentID = '" + studentID + " '";
        return new ArrayList<>(read(sql, type, elementStructure));
    }
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
        if (type == QuestionID) {
            String newQuestionID = info.get(1);
            sql = "update QUESTIONANSWER set questionID = '" + newQuestionID + "' where id = " + QuestionAnswerID;
        } else if (type == ANSWER) {
            String newAnswer = info.get(1);
            sql = "update QUESTIONANSWER set answer = '" + newAnswer+ "' where id = " + QuestionAnswerID;
        } else if (type == MARK) {
            String newMark = info.get(1);
            sql = "update QUESTIONANSWER set mark = '" + newMark + "' where id = " + QuestionAnswerID;
        } else if (type == GroupID) {
            String newGroupID = info.get(1);
            sql = "update QUESTIONANSWER set groupID = '" + newGroupID + "' where id = " + QuestionAnswerID;
        } else if (type == StudentID) {
            String newStudentID = info.get(1);
            sql = "update QUESTIONANSWER set studentID = '" + newStudentID + "' where id = " + QuestionAnswerID;

        }
        if (rewrite(sql).equals(SUCCESS)) {
            result.add(QuestionAnswerID + "");
        }

        return result;
    }

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
