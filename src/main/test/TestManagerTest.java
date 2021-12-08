import BackEnd.Gateways.*;
import BackEnd.Managers.TestManager;
import org.junit.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

import static org.junit.Assert.assertEquals;

public class TestManagerTest {
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
        String sql = "DROP TABLE TEST";
        stmt.executeUpdate(sql);

        IniTest.ini();
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

    @Test (timeout = 50000)
    public void testSecondTest() {
        TestGateway testGateway = new TestGateway();
        int i = new TestManager(testGateway).creatTest("test2", 2, 5);
        String j = testGateway.readByID(222, 2, i).get(0);
        String k = testGateway.readByID(111, 3, i).get(0);
        String m = testGateway.readByID(111, 5, i).get(0);
        assertEquals("test2", j);
        assertEquals(2, Integer.parseInt(k));
        assertEquals(5, Integer.parseInt(m));
    }
}
