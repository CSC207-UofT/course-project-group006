import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testnewstudent() {
        Student student1 = new Student("Gavin", "123456", "123456@gmail.com");
        Word easyword = new Word(0, "apple");
        assertEquals(student1.getLevel(), 0);
        assertEquals(student1.learnWord(easyword), true);
    }

    @Test(timeout = 50)
    public void testnewteacher() {
        Teacher teacher1 = new Teacher("Jenny", "111111", "1111111@gmail.com");
        ArrayList<String> group = new ArrayList<String>();
        ArrayList<String> test = new ArrayList<String>();
        assertEquals(teacher1.getGroupCreated(), group);
        assertEquals(teacher1.getOwnedTest(), test);
    }

    @Test(timeout = 50)
    public void testmanager(){
        Student student = new Student("Gavin", "123456", "123456@gmail.com");
        Teacher teacher = new Teacher("Jenny", "111111", "1111111@gmail.com");
        ArrayList<User> userlist = new ArrayList<User>();
        UserManager user = new UserManager(userlist);
        assertEquals(user.createStudent("Gavin", "123456", "123456@gmail.com"), "Gavin");
        assertEquals(user.createTeacher("Jenny", "111111", "1111111@gmail.com"), "Jenny");
        assertEquals(user.getUser("John"), "There is no user with name John");
        assertEquals(user.loginWithUsername("Gavin", "123456"), true);
        assertEquals(user.loginWithUsername("Jenny", "123456"), false);
    }
}