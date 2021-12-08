package BackEnd.Managers;
import BackEnd.Entities.Teacher;
import BackEnd.Interfaces.ReadNameID;

import java.util.ArrayList;
import java.util.List;

/**
 * The Teacher manager
 */
public class TeacherManager extends UserManager {
    /**
     * The constructor of TeacherManager
     * @param userGate the user gate
     */
    public TeacherManager(ReadNameID userGate) {
        super(userGate);
    }

    /**
     * Read teacher user
     * @param id the user ID
     * @return The teacher user's info
     */
    @Override
    protected Teacher readUser(int id) {
        //return new Teacher("placeholder","aaa","a",new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<>());
        List<String> info = this.userGate.readRow(id);
        if (info != null && info.size() > 0 && info.get(0).equals("" + id)) {
            String userName = info.get(1);
            String password = info.get(2);
            String email = info.get(4);
            String[] groups = info.get(5).split(",");
            String[] tests = info.get(6).split(",");
            ArrayList<Integer> gIds = new ArrayList<>();
            for (String group : groups) {
                try {
                    gIds.add(Integer.parseInt(group));
                } catch (NumberFormatException e) {
                    //do nothing
                }
            }
            ArrayList<Integer> tIds = new ArrayList<>();
            for (String test : tests) {
                try {
                    tIds.add(Integer.parseInt(test));
                } catch (NumberFormatException e) {
                    //do nothing
                }
            }
            return new Teacher(userName, password, email, gIds, tIds);
        }
        return null;
    }

    /**
     * Identify if the user is a teacher user
     * @param id the user ID
     * @return The boolean showing if a user is a teacher
     */
    public boolean IsTeacher(int id) {
        return this.userGate.readRow(id).size() > 0;
    }

    /**
     * Login
     * @param userName the username
     * @param password the password
     * @return The integer showing if the login is valid
     */
    public int LogIn(String userName, String password) {
        int userType = getUserType(userName);
        int userID = getID(userName,userGate);
        if (userType == TEACHER) {
            String pass = userGate.readByID(222, 3, userID).get(0);
            if (pass.equals(password)) {
                return userID;
            }
        }

        return -1;
    }

    /**
     * Get groups
     * @param id the user ID
     * @return The list of groups contained the user
     */
    public List<Integer> groups(int id) {
        return readUser(id).getGroupCreated();
    }

    /**
     * Get tests
     * @param id the user ID
     * @return The list of tests accessable to the user
     */
    public List<Integer> tests(int id) {
        return readUser(id).getOwnedTest();
    }

    /**
     * If add test is done successfully
     * @param teacherID the teacher ID
     * @param testID the test ID
     * @return The boolean showing if add test is successful
     */
    public boolean addTest(int teacherID, int testID) {

        List<Integer> allTests = readUser(teacherID).getOwnedTest();
        if (allTests != null && allTests.size() != 0) {
            for (int id : allTests) {
                if (id == testID) {
                    return false;
                }
            }
        }
        List<String> list = new ArrayList<>();
        list.add(teacherID + "");
        list.add(testID + "");
        List<String> result = userGate.write(7, list);
        return result != null;

    }
}
