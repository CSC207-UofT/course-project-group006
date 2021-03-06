import BackEnd.Gateways.Gateway;
import BackEnd.Gateways.StudentGateway;
import BackEnd.Gateways.TeacherGateway;
import BackEnd.Managers.UserManager;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserManagerTest {
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
        String sql = "DROP TABLE STUDENT";
        stmt.executeUpdate(sql);
        sql = "DROP TABLE TEACHER";
        stmt.executeUpdate(sql);
        System.out.println("drop teacher");
    }

    @Test(timeout = 50000)
    public void testStudyGroup() {
        new UserManager(new StudentGateway()).createUser("StudentName", "StudentPass", "StudentEmail");
        new UserManager(new TeacherGateway()).createUser("TeacherName", "TeacherPass", "TeacherEmail");
        String studentID = new StudentGateway().readIntByName(1, "StudentName").get(0);
        String teacherID = new TeacherGateway().readIntByName(1, "TeacherName").get(0);
        int a = new UserManager(new StudentGateway()).loginWithUsername("StudentName", "StudentPass", new StudentGateway(), new TeacherGateway());
        boolean b = new UserManager(new StudentGateway()).resetPassword(Integer.parseInt(studentID), "12346");
        boolean c = new UserManager(new StudentGateway()).resetUsername(Integer.parseInt(studentID), "Gavin");
        boolean i = new UserManager(new StudentGateway()).addGroupToUser(Integer.parseInt(studentID), 1, 500);
        boolean j = new UserManager(new TeacherGateway()).addGroupToUser(Integer.parseInt(teacherID), 1, 600);
        boolean k = new UserManager(new StudentGateway()).removeGroupFromUser(Integer.parseInt(studentID),1, 500);
        boolean m = new UserManager(new TeacherGateway()).removeGroupFromUser(Integer.parseInt(teacherID), 1, 600);
        assertTrue(i);
        assertTrue(j);
        assertTrue(k);
        assertTrue(m);
        assertEquals(Integer.parseInt(studentID), a);
        assertTrue(b);
        assertTrue(c);
    }
    @Test(timeout = 50000)
    public void testRegister() {
        int studentID = new UserManager(new StudentGateway()).createUser("StudentName", "StudentPass", "StudentEmail");
        int teacherID = new UserManager(new TeacherGateway()).createUser("TeacherName", "TeacherPass", "TeacherEmail");
        int studentNotSuccess = new UserManager(new StudentGateway()).createUser("StudentName", "StudentPass", "StudentEmail");
        int teacherNotSuccess = new UserManager(new TeacherGateway()).createUser("TeacherName", "TeacherPass", "TeacherEmail");
        int type = new UserManager(new StudentGateway()).getUserType("StudentName");
        int type2 = new UserManager(new StudentGateway()).getUserType("TeacherName");
        //student = 500, teacher = 600
        assertEquals(1, studentID);
        assertEquals(1,teacherID);
        assertEquals(-1, studentNotSuccess);
        assertEquals(-1,teacherNotSuccess);
        assertEquals(500, type);
        assertEquals(600, type2);
    }

}
