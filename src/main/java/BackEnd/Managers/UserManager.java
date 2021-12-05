package BackEnd.Managers;

import BackEnd.Entities.Teacher;
import BackEnd.Entities.User;
import BackEnd.Interfaces.GeneralReadWriter;

import BackEnd.Gateways.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User Manager
 */
public class UserManager {

    protected final int STUDENT = 500;
    protected final int TEACHER = 600;

    protected final GeneralReadWriter userGate;
    private final GeneralReadWriter userGateForOtherType;

    /**
     * Instantiates a new User manager.
     *
     * @param userGate the user gate
     */
    public UserManager(GeneralReadWriter userGate) {
        this.userGate = userGate;
        this.userGateForOtherType = null;
    }
    /**
     * Instantiates a new User manager.
     *
     * @param userGate the user gate
     * @param userGateForOtherType the user gate fot the other user type
     */
    public UserManager(GeneralReadWriter userGate,GeneralReadWriter userGateForOtherType) {
        this.userGate = userGate;
        this.userGateForOtherType = userGateForOtherType;
    }

    /**
     * Reset password boolean.
     *
     * @param userID  the user id
     * @param newPass the new pass
     * @return success T, failed F
     */
    public boolean resetPassword(int userID, String newPass) {
        List<String> info = new ArrayList<>();
        info.add(userID + "");
        info.add(newPass);
        List<String> result = userGate.write(3, info);
        return result != null && result.size() != 0;
    }

    /**
     * Reset username boolean.
     *
     * @param userID  the user id
     * @param newName the new name
     * @return success T, failed F
     */
    public boolean resetUsername(int userID, String newName) {
        List<String> info = new ArrayList<>();
        info.add(userID + "");
        info.add(newName);
        List<String> result = userGate.write(2, info);
        return result != null && result.size() != 0;
    }

    /**
     * Login with username int.
     *
     * @param name        the name
     * @param password    the password
     * @param studentGate the student gate
     * @param teacherGate the teacher gate
     * @return int userID
     */
    public int loginWithUsername(String name, String password, GeneralReadWriter studentGate, GeneralReadWriter teacherGate) {

        int userType = getUserType(name);
        GeneralReadWriter gate;
        if (userType == STUDENT) {
            gate = studentGate;
        } else if (userType == TEACHER) {

            gate = teacherGate;
        } else {
            return -1;
        }
        int userID = getID(name);
        System.out.println(gate.getClass());
        String pass = gate.readByID(222, 3, userID).get(0);

        if (pass.equals(password)) {
            return userID;
        }
        return -1;
    }

    /**
     * Gets user type.
     *
     * @param userName the username
     * @return int user type(student:500, teacher:600)
     */
    public int getUserType(String userName) {
        if (userGate.hasDuplicateNames("STUDENT", userName)) {
            return STUDENT;
        }
        if (userGate.hasDuplicateNames("TEACHER", userName)) {
            return TEACHER;
        }
        return -1;
    }

    /**
     * Get groups from user int [ ].
     *
     * @param userID the user id
     * @param type   the user type(student:500, teacher:600)
     * @return the int [ ] with all the group ids
     */
    public int[] getGroupsFromUser(int userID, int type) {
        int col;
        if (type == STUDENT) {
            col = 7;
        } else {
            col = 6;
        }
        List<String> result = userGate.readByID(222, col, userID);
        if (result.get(0).equals("")) {
            return null;
        }
        String[] strings = result.get(0).split(",");
        int[] array = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
        }
        return array;

    }

    /**
     * Add group to user boolean.
     *
     * @param userID  the user id
     * @param groupID the group id
     * @param type    user type(student:500, teacher:600)
     * @return success T, failed F
     */
    public boolean addGroupToUser(int userID, Integer groupID, int type) {
        int col;
        if (type == STUDENT) {
            col = 7;
        } else {
            col = 6;
        }
        int[] allGroups = getGroupsFromUser(userID, type);
        if (allGroups != null && allGroups.length != 0) {
            for (int id : allGroups) {
                if (id == groupID) {
                    return false;
                }
            }
        }
        List<String> list = new ArrayList<>();
        list.add(userID + "");
        list.add(groupID + "");
        List<String> result = userGate.write(col, list);
        return result != null;
    }

    /**
     * Create user int.
     *
     * @param name     the name
     * @param password the password
     * @param email    the email
     * @return the int user id
     */
    public int createUser(String name, String password, String email) {
        List<String> info = new ArrayList<>();
        info.add(name);
        info.add(password);
        info.add(email);
        List<String> result = userGate.write(1, info);
        if (result != null && result.size() != 0) {
            return Integer.parseInt(result.get(0));
        }
        return -1;
    }

    /**
     * Remove group from user boolean.
     *
     * @param userID  the user id
     * @param groupID the group id
     * @param type    user type(student:500, teacher:600)
     * @return success T, failed F
     */
    public boolean removeGroupFromUser(int userID, Integer groupID, int type) {
        int col;
        if (type == STUDENT) {
            col = 7;
        } else {
            col = 6;
        }
        List<String> result = userGate.readByID(222, col, userID);
        if (result.get(0).equals("")) {
            return false;
        }
        String[] strings = result.get(0).split(",");
        int[] array = new int[strings.length];
        boolean inGroup = false;
        int index = 0;
        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
            if (array[i] == groupID) {
                inGroup = true;
                index = i;
            }
        }
        if (!inGroup) {
            return false;
        }

        StringBuilder newString = new StringBuilder();
        if (array.length != 1) {
            for (int i = 0; i < array.length; i++) {
                if (i != index) {
                    newString.append(",").append(array[i]);
                }
            }
        }
        List<String> info = new ArrayList<>();
        info.add(userID + "");
        info.add(newString.toString());
        List<String> stringList = userGate.write(22, info);
        return !stringList.get(0).equals("FAILED");
    }

    /**
     * Gets id.
     *
     * @param userName the user name
     * @return the id
     */
    public int getID(String userName) {
        List<String> result = userGate.readIntByName(1, userName);
        System.out.println(userGate.getClass());
        if (result != null&&result.size()>0) {
            return Integer.parseInt(result.get(0));
        }
        return -1;
    }
    protected User readUser(int id){
        return null;
    }
    public String getNameById(int id){
        return readUser(id).getUsername();
    }
    public String getUserType(int id){
        int userType= getUserType(getNameById(id));
        if(userType==TEACHER){
            return "T";
        }else if(userType==STUDENT){
            return "S";
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new UserManager(new StudentGateway()).loginWithUsername("a","a",new StudentGateway(),new TeacherGateway()));
    }

}
