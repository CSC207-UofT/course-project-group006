import BackEnd.Gateways.Gateway;
import BackEnd.Gateways.QuestionGateway;
import BackEnd.Gateways.StudentGateway;
import BackEnd.Gateways.TeacherGateway;
import BackEnd.Managers.QuestionManager;
import BackEnd.Managers.UserManager;
import org.junit.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;


public class QuestionManagerTesting {
    @Before
    public void setUp() throws SQLException {
//        Server: sql5.freemysqlhosting.net
//        Name: sql5456611
//        Username: sql5456611
//        Password: 9BF66dT8y5
//        Port number: 3306
        Gateway.driver = "com.mysql.cj.jdbc.Driver";
        Gateway.user = "sql5456611";
        Gateway.password = "9BF66dT8y5";
        Gateway.url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5456611";

        IniTest.ini();
    }
    @After
    public void tearDown() throws SQLException {
        Gateway.driver = "com.mysql.cj.jdbc.Driver";
        Gateway.user = "sql5456611";
        Gateway.password = "9BF66dT8y5";
        Gateway.url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5456611";
        String username = "sql5456611";
        String password = "9BF66dT8y5";
        String url =  "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5456611";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();
        String sql = "DROP TABLE QUESTION";
        stmt.executeUpdate(sql);

        IniTest.ini();
    }

    @Test (timeout = 500000)
    public void testAddQuestion() {
            QuestionGateway questionGateway = new QuestionGateway();
            int i = new QuestionManager(questionGateway).addQuestion("test", "apple", "pingguo", 5);
            String a = questionGateway.readByID(222, 2, i).get(0);
            String b = questionGateway.readByID(222, 3, i).get(0);
            String c = questionGateway.readByID(222, 4, i).get(0);
            String d = questionGateway.readByID(111, 5, i).get(0);
            assertEquals("test", a);
            assertEquals("apple", b);
            assertEquals("pingguo", c);
            assertEquals(5, Integer.parseInt(d));

    }
}
