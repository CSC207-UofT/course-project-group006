import BackEnd.Gateways.*;
import BackEnd.Managers.QuestionAnswerManager;
import BackEnd.Managers.QuestionManager;
import org.junit.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import static org.junit.Assert.assertEquals;



public class QuestionAnswerManagerTest {
    @Before
    public void setUp() {
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
        String sql = "DROP TABLE QUESTIONANSWER";
        stmt.executeUpdate(sql);
        String sql1 = "DROP TABLE QUESTION";
        stmt.executeUpdate(sql1);

        IniTest.ini();
    }
    @Test (timeout = 50000)
    public void testSubmit() {
        QuestionGateway questionGateway = new QuestionGateway();
        int n = new QuestionManager(questionGateway).addQuestion("test2", "banana", "xiangjiao", 7);
        QuestionAnswerGateway questionAnswerGateway = new QuestionAnswerGateway();
        new QuestionAnswerManager(questionAnswerGateway).createQuestionAnswer(n, "xiang", 1, 1, questionAnswerGateway);
        new QuestionAnswerManager(questionAnswerGateway).createQuestionAnswer(n, "xiangjiao", 2, 1, questionAnswerGateway);
        String k = questionAnswerGateway.readByID(111, 4, 1).get(0);
        String l = questionAnswerGateway.readByID(111, 4, 2).get(0);
        assertEquals(0, Integer.parseInt(k));
        assertEquals(7, Integer.parseInt(l));

    }

    @Test (timeout = 50000)
    public void testAnotherSubmit() {
        QuestionGateway questionGateway = new QuestionGateway();
        int n = new QuestionManager(questionGateway).addQuestion("test", "apple", "pingguo", 5);
        QuestionAnswerGateway questionAnswerGateway = new QuestionAnswerGateway();
        new QuestionAnswerManager(questionAnswerGateway).createQuestionAnswer(n, "pingguo", 1, 1, questionAnswerGateway);
        new QuestionAnswerManager(questionAnswerGateway).createQuestionAnswer(n, "ping", 2, 1, questionAnswerGateway);
        String k = questionAnswerGateway.readByID(111, 4, 1).get(0);
        String l = questionAnswerGateway.readByID(111, 4, 2).get(0);
        assertEquals(5, Integer.parseInt(k));
        assertEquals(0, Integer.parseInt(l));

    }

}
