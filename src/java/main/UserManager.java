import java.util.ArrayList;

public class UserManager {

    private static ArrayList<User> userList;

    public UserManager(ArrayList<User> userList1){
        userList = userList1;
    }

    public void createStudent(String name, String password, String email){
        Student s1 = new Student(name, password, email);
        userList.add(s1);
    }

    public void createTeacher(String name, String password, String email){
        Student t1 = new Student(name, password, email);
        userList.add(t1);
    }

    public void resetPassword(User u1, String newPassword){
        u1.setPassword(newPassword);
    }

    public void resetUsername(User u1, String newName){
        u1.setUsername(newName);
    }

    public void resetEmail(User u1, String newEmail){
        u1.setEmail(newEmail);
    }

    public String getUser(String name){
        for (User a: userList){
            if(a.getUsername().equals(name)){
                return a.toString();
            }
        }
        return "There is no user with name" + name;
    }

    public boolean loginWithUsername(String name, String password){
        for (User a: userList){
            if(a.getUsername().equals(name) && a.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean loginWithEmail(String email, String password){
        for (User a: userList){
            if(a.getEmail().equals(email) && a.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


}