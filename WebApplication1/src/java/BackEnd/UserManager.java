package BackEnd1;

import java.util.ArrayList;

public class UserManager {

    private static ArrayList<User> userList;

    /**
     * Construct a User manager giving a list of users
     * @param userList1 the list of users
     */

    public UserManager(ArrayList<User> userList1){
        userList = userList1;
        if(userList == null){
        userList=new ArrayList<User>();
        userList.add(new Teacher("a","a","a"));
        
        userList.add(new Student("b","b","b"));
    }
    }

    /**
     * Create a new student account and add to the manager
     * @param name the name of the student
     * @param password the password of the student
     * @param email the email of the student
     * @return the name of the student
     */

    public String createStudent(String name, String password, String email){
        Student s1 = new Student(name, password, email);
        userList.add(s1);
        return name;
    }

    /**
     * Create a new teacher account and add to the manager
     * @param name the name of the teacher
     * @param password the password of the teacher
     * @param email the email of the teacher
     * @return the name of the teacher
     */


    public String createTeacher(String name, String password, String email){
        Teacher t1 = new Teacher(name, password, email);
        userList.add(t1);
        return name;
    }

    /**
     * reset the password of one account
     * @param u1 the user who wants to change password
     * @param newPassword the new reseted password
     */

    public void resetPassword(User u1, String newPassword){
        u1.setPassword(newPassword);
    }

    /**
     * reset the username of one account
     * @param u1 the user who wants to change username
     * @param newName the new username
     */

    public void resetUsername(User u1, String newName){
        u1.setUsername(newName);
    }

    /**
     * reset the email of one account
     * @param u1 the user who wants to change email
     * @param newEmail the new email
     */

    public void resetEmail(User u1, String newEmail){
        u1.setEmail(newEmail);
    }

    /**
     * Get a certain user from the manager by name
     * @param name the name of the user that want to search
     * @return the User or null if not found
     */

    public User getUser(String name){
        for (User a: userList){
            if(a.getUsername().equals(name)){
                return a;
            }
        }

        return null;
    }

    /**
     * Get a certain user from the manager by ID
     * @param ID the ID of the user that want to search
     * @return the User or null if not found
     */

    public User getUser(int ID){
        for (User a: userList){
            if(a.getID()==(ID)){
                return a;
            }
        }

        return null;
    }

    /**
     * login to the system using username and password
     * @param name the username of your account
     * @param password the password your account
     * @return the user ID or -1 if failed
     */

    public int loginWithUsername(String name, String password){
        System.out.print(userList.get(0).getID());
        for (User a: userList){
            if(a.getUsername().equals(name) && a.getPassword().equals(password)){
               return a.getID();
            }
        }
        return -1;
    }


    /**
     * login to the system using email and password
     * @param email the username of your account
     * @param password the password your account
     * @return true if succeed or false if failed
     */

    public boolean loginWithEmail(String email, String password){
        for (User a: userList){
            if(a.getEmail().equals(email) && a.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    /**
     * Get user's identity
     * @param ID the ID of this user
     * @return teacher or student according to the user. return false if not found
     */
    public String getUserType(int ID){
        for (User a: userList){
            if(a.getID()==(ID)){
                if(a instanceof Teacher){
                    return "T";
                }else if (a instanceof Student){
                    return "S";
                }
            }
        }

        return "Fail";
    }

    /**
     * Get the name of user by ID
     * @param id the ID of user
     * @return the username of this user
     */
    public String getName(int id){
        return getUser(id).getUsername();
    }

}
