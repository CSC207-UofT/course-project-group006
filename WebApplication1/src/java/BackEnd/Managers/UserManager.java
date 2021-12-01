package BackEnd.Managers;

import BackEnd.Interfaces.GeneralReadWriter;
import BackEnd.Entities.Student;
import BackEnd.Entities.Teacher;
import BackEnd.Entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private final int STUDENT = 500;
    private final int TEACHER = 600;

    private static ArrayList<User> userList;
    private GeneralReadWriter userGate;

    public UserManager(GeneralReadWriter userGate) {
        this.userGate = userGate;
    }


    public UserManager(ArrayList<User> userList1) {
        userList = userList1;
        if (userList == null) {
            userList = new ArrayList<User>();
            userList.add(new Teacher("a", "a", "a"));

            userList.add(new Student("b", "b", "b"));
        }
    }


    public boolean resetPassword(int userID, String newPass) {
        List<String> info = new ArrayList<>();
        info.add(userID + "");
        info.add(newPass);
        List<String> result = userGate.write(3, info);
        return result != null && result.size() != 0;
    }

    public boolean resetUsername(int userID, String newName) {
        List<String> info = new ArrayList<>();
        info.add(userID + "");
        info.add(newName);
        List<String> result = userGate.write(2, info);
        return result != null && result.size() != 0;
    }

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

        String pass = gate.readByID(222, 3, userID).get(0);

        if (pass.equals(password)) {
            return userID;
        }
        return -1;
    }

    public int getUserType(String userName) {
        if (userGate.hasDuplicateNames("STUDENT", userName)) {
            return STUDENT;
        }
        if (userGate.hasDuplicateNames("TEACHER", userName)) {
            return TEACHER;
        }
        return -1;
    }

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

    public int getID(String userName) {
        List<String> result = userGate.readIntByName(1, userName);
        if (result != null) {
            return Integer.parseInt(result.get(0));
        }
        return -1;
    }
}
