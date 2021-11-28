package Write.Test;

import Read.Test.ReadID;
import Read.Test.TestReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WriteNewTest extends TestWriter {
    private final String name;
    private final int author;
    private final int price;
    private final java.util.Date date;

    public WriteNewTest(String name, int author, int price, java.util.Date date) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.date = date;
    }

    @Override
    public Object set() {
        try {
            Connection connection = getConnection();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String questions = "";
            String sql = "insert into " + TABLE + " (name,author,date,price,questions) VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, author);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setInt(4, price);
            preparedStatement.setString(5, questions);
            preparedStatement.executeUpdate();
            connection.close();

            //get test ID
            TestReader testReader = new ReadID(name, author, price);
            return testReader.read();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }
    }
}
