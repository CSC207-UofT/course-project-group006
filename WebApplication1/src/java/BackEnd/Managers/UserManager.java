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


    public String createTeacher(String name, String password, String email) {
        //TODO
        Teacher t1 = new Teacher(name, password, email);
        userList.add(t1);
        return name;
    }

    public void resetPassword(User u1, String newPassword) {
        //TODO
        u1.setPassword(newPassword);
    }

    public void resetUsername(User u1, String newName) {
        //TODO
        u1.setUsername(newName);
    }

    public void resetEmail(User u1, String newEmail) {
        //TODO
        u1.setEmail(newEmail);
    }

    public User getUser(String name) {
        //TODO
        for (User a : userList) {
            if (a.getUsername().equals(name)) {
                return a;
            }
        }

        return null;
    }

    public User getUser(int ID) {
        //TODO
        for (User a : userList) {
            if (a.getID() == (ID)) {
                return a;
            }
        }

        return null;
    }

    public int loginWithUsername(String name, String password) {
        //TODO
        System.out.print(userList.get(0).getID());
        for (User a : userList) {
            if (a.getUsername().equals(name) && a.getPassword().equals(password)) {
                return a.getID();
            }
        }
        return -1;
    }

    //id会重复
//    public String getUserType(int ID) {
//        //TODO
//        for (User a : userList) {
//            if (a.getID() == (ID)) {
//                if (a instanceof Teacher) {
//                    return "T";
//                } else if (a instanceof Student) {
//                    return "S";
//                }
//            }
//        }
//
//        return "Fail";
//    }
//
//    public String getName(int id) {
//        //TODO
//        return getUser(id).getUsername();
//    }
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
        if (allGroups != null) {
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
        if (result != null) {
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
}
