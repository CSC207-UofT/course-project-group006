package BackEnd.oldstuff.Write.Answer;

import BackEnd.oldstuff.Read.Answer.AnswerReader;
import BackEnd.oldstuff.Read.Answer.ReadAnswerByID;
import BackEnd.oldstuff.Read.Answer.ReadID;
import BackEnd.oldstuff.Read.Answer.ReadQuestionID;
import BackEnd.oldstuff.Read.Question.QuestionReader;
import BackEnd.oldstuff.Read.Question.ReadAnswer;
import BackEnd.oldstuff.Read.Question.ReadMark;

import java.sql.*;

public class AutoGrade extends AnswerWriter {
    public AutoGrade(){}
    @Override

    public Object set() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            AnswerReader answerReader = new ReadID(FAILED);
            int answerID = (int) answerReader.read();
            if (answerID == FAILED) {
                return FAILED;
            }

            String studentAnswer = (String) new ReadAnswerByID(answerID).read();
            int questionID = (int) new ReadQuestionID(answerID).read();


            QuestionReader questionReader = new ReadAnswer(questionID);
            String correctAnswer = (String) questionReader.read();
            QuestionReader q = new ReadMark(questionID);
            int mark = (int) q.read();
            int studentMark;
            if (correctAnswer.equals(studentAnswer)) {
                studentMark = mark;
            } else {
                studentMark = 0;
            }

            //write the mark into database
            String sql1 = "update " + TABLE + " set mark = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, studentMark);
            preparedStatement.setInt(2, answerID);
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


