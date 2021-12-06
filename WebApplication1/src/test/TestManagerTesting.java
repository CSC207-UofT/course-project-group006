import BackEnd.Gateways.*;
import BackEnd.Managers.QuestionManager;
import BackEnd.Managers.TestManager;
import BackEnd.Managers.UserManager;
import org.junit.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

public class TestManagerTesting {
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

        Ini.ini();
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
        String sql = "DROP TABLE TEST";
        stmt.executeUpdate(sql);

        Ini.ini();
    }
    @Test (timeout = 50000)
    public void testTest() {
        TestGateway testGateway = new TestGateway();
        int i = new TestManager(testGateway).creatTest("test", 1, 10);
        String j = testGateway.readByID(222, 2, i).get(0);
        String k = testGateway.readByID(111, 3, i).get(0);
        String m = testGateway.readByID(111, 5, i).get(0);
        assertEquals("test", j);
        assertEquals(1, Integer.parseInt(k));
        assertEquals(10, Integer.parseInt(m));
    }
}
