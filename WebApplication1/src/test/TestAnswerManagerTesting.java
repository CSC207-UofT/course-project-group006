import BackEnd.Gateways.*;
import BackEnd.Interfaces.ReadIDName;
import BackEnd.Managers.*;
import org.junit.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

public class TestAnswerManagerTesting {
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
        String username = "sql5456611";
        String password = "9BF66dT8y5";
        String url =  "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5456611";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();
        String sql = "DROP TABLE TEST";
        stmt.executeUpdate(sql);
        sql = "DROP TABLE QUESTION";
        stmt.executeUpdate(sql);
        sql = "DROP TABLE TESTANSWER";
        stmt.executeUpdate(sql);
        sql = "DROP TABLE QUESTIONANSWER";
        stmt.executeUpdate(sql);
        Ini.ini();
    }
    @After
    public void tearDown() throws SQLException {
        Gateway.driver = "com.mysql.cj.jdbc.Driver";
        Gateway.user = "sql5456611";
        Gateway.password = "9BF66dT8y5";
        Gateway.url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5456611";
//        String username = "sql5456611";
//        String password = "9BF66dT8y5";
//        String url =  "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5456611";
//        Connection connection = DriverManager.getConnection(url, username, password);
//        Statement stmt = connection.createStatement();
//        String sql = "DROP TABLE TESTANSWER";
//        stmt.executeUpdate(sql);

        Ini.ini();
    }
    @Test (timeout = 50000)
    public void testTestAnswer() {
        TestAnswerGateway testAnswerGateway = new TestAnswerGateway();
        TestGateway testGateway = new TestGateway();
        QuestionAnswerGateway questionAnswerGateway = new QuestionAnswerGateway();
        QuestionGateway questionGateway = new QuestionGateway();
        ReadIDName bb = new QuestionAnswerGateway();
        int questionID = new QuestionManager(questionGateway).addQuestion("test", "apple", "pingguo", 5);
        int questionID1 = new QuestionManager(questionGateway).addQuestion("test2", "banana", "xiangjiao", 4);
        int testID = new TestManager(testGateway).creatTest("test", 1, 10);
        new TestManager(testGateway).addQuestionToTest(1, 1);
        new TestManager(testGateway).addQuestionToTest(1, 2);
        int questionAnswerID = new QuestionAnswerManager(questionAnswerGateway).createQuestionAnswer(questionID, "pingguo", 1, 1, bb);
        int questionAnswerID2 = new QuestionAnswerManager(questionAnswerGateway).createQuestionAnswer(questionID, "ping", 2, 1, bb);
        int questionAnswerID3 = new QuestionAnswerManager(questionAnswerGateway).createQuestionAnswer(2, "xiangjiao", 1, 1, bb);
        int questionAnswerID4 = new QuestionAnswerManager(questionAnswerGateway).createQuestionAnswer(2, "xiang", 2, 1, bb);
        int i = new TestAnswerManager(testAnswerGateway, testGateway, questionAnswerGateway).createTestAnswer(1, 1, testAnswerGateway, testGateway, bb);
        int j = new TestAnswerManager(testAnswerGateway, testGateway, questionAnswerGateway).createTestAnswer(1, 2, testAnswerGateway, testGateway, bb);
        String k = testAnswerGateway.readByID(111, 4, 1).get(0);
        String m = testAnswerGateway.readByID(111, 4, 2).get(0);
        assertEquals(9, Integer.parseInt(k));
        assertEquals(0, Integer.parseInt(m));
    }
}
