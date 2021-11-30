package BackEnd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserManager {

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


    public String getUserType(int ID) {
        //TODO
        for (User a : userList) {
            if (a.getID() == (ID)) {
                if (a instanceof Teacher) {
                    return "T";
                } else if (a instanceof Student) {
                    return "S";
                }
            }
        }

        return "Fail";
    }

    public String getName(int id) {
        //TODO
        return getUser(id).getUsername();
    }

    public int[] getGroupsFromStudents(int studentID) {
        List<String> result = userGate.readByID(222, 7, studentID);
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


    public boolean addGroupToStudent(int studentID, Integer groupID) {
        int[] allGroups = getGroupsFromStudents(studentID);
        if (allGroups != null) {
            for (int id : allGroups) {
                if (id == groupID) {
                    return false;
                }
            }
        }
        List<String> list = new ArrayList<>();
        list.add(studentID + "");
        list.add(groupID + "");
        List<String> result = userGate.write(7, list);
        return result != null;
    }

    public int createStudent(String name, String password, String email) {
        Student s1 = new Student(name, password, email);
        List<String> info = new ArrayList<>();
        info.add(s1.getUsername());
        info.add(s1.getPassword());
        info.add(s1.getEmail());
        List<String> result = userGate.write(1, info);
        if (result != null) {
            return Integer.parseInt(result.get(0));
        }
        return -1;
    }

    public boolean removeGroupFromStudent(int studentID, Integer groupID) {
        List<String> result = userGate.readByID(222, 7, studentID);
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
        info.add(studentID + "");
        info.add(newString.toString());
        List<String> stringList = userGate.write(22, info);
        return !stringList.get(0).equals("FAILED");
    }
}
