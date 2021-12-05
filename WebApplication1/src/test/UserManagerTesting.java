import BackEnd.Gateways.Gateway;
import BackEnd.Gateways.StudentGateway;
import BackEnd.Gateways.TeacherGateway;
import BackEnd.Managers.UserManager;
import org.junit.*;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

public class UserManagerTesting {
    @Before
    public void setUp() {
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
    public void tearDown() {
        Gateway.driver = "com.mysql.cj.jdbc.Driver";
        Gateway.user = "sql5454663";
        Gateway.password = "SinqmMLSgB";
        Gateway.url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5454663";
    }

    @Test(timeout = 500000)
    public void testRegister() {
        int studentID = new UserManager(new StudentGateway()).createUser("StudentName", "StudentPass", "StudentEmail");
        int teacherID = new UserManager(new TeacherGateway()).createUser("TeacherName", "TeacherPass", "TeacherEmail");
        int studentNotSuccess = new UserManager(new StudentGateway()).createUser("StudentName", "StudentPass", "StudentEmail");
        int teacherNotSuccess = new UserManager(new TeacherGateway()).createUser("TeacherName", "TeacherPass", "TeacherEmail");
        assertNotEquals(-1, studentID);
        assertNotEquals(-1,teacherID);
        assertEquals(-1, studentNotSuccess);
        assertEquals(-1,teacherNotSuccess);
    }

}
