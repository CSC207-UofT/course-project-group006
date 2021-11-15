package Write.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WriteNewQuestion extends QuestionWriter {
    private final String name;
    private final String question;
    private final String answer;
    private final int mark;

    public WriteNewQuestion(String name, String question, String answer, int mark) {
        this.name = name;
        this.question = question;
        this.answer = answer;
        this.mark = mark;
    }

    @Override
    public Object set() {
        try {
            Connection connection = getConnection();
            String sql = "insert into " + TABLE + " (name,question,answer,mark) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, question);
            preparedStatement.setString(3, answer);
            preparedStatement.setInt(4, mark);
            preparedStatement.executeUpdate();
            connection.close();
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }
}
