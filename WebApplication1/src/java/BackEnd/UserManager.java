package BackEnd;

import java.util.ArrayList;

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

    public String createStudent(String name, String password, String email) {
        Student s1 = new Student(name, password, email);
        userList.add(s1);
        return name;
    }


    public String createTeacher(String name, String password, String email) {
        Teacher t1 = new Teacher(name, password, email);
        userList.add(t1);
        return name;
    }

    public void resetPassword(User u1, String newPassword) {
        u1.setPassword(newPassword);
    }

    public void resetUsername(User u1, String newName) {
        u1.setUsername(newName);
    }

    public void resetEmail(User u1, String newEmail) {
        u1.setEmail(newEmail);
    }

    public User getUser(String name) {
        for (User a : userList) {
            if (a.getUsername().equals(name)) {
                return a;
            }
        }

        return null;
    }

    public User getUser(int ID) {
        for (User a : userList) {
            if (a.getID() == (ID)) {
                return a;
            }
        }

        return null;
    }

    public int loginWithUsername(String name, String password) {
        System.out.print(userList.get(0).getID());
        for (User a : userList) {
            if (a.getUsername().equals(name) && a.getPassword().equals(password)) {
                return a.getID();
            }
        }
        return -1;
    }


    public boolean loginWithEmail(String email, String password) {
        for (User a : userList) {
            if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public String getUserType(int ID) {
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
        return getUser(id).getUsername();
    }

}
