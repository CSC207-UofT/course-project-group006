package BackEnd.Managers;

import BackEnd.Entities.Student;
import BackEnd.Interfaces.ReadNameID;

import java.util.ArrayList;
import java.util.List;

/**
 * The Student manager
 */
public class StudentManager extends UserManager{
    /**
     * The constructor of StudentManager
     * @param userGate the user gate
     */
    public StudentManager(ReadNameID userGate) {
        super(userGate);
    }

    /**
     * Read student user
     * @param id the user ID
     * @return the student
     */
    @Override
    protected Student readUser(int id){
        //return new Teacher("placeholder","aaa","a",new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<>());
    List<String> info = this.userGate.readRow(id);
    if(info.get(0).equals(""+id)){
        String userName = info.get(1);
        String password = info.get(2);
        String level = info.get(9);
        String email = info.get(4);
        String[] groups = info.get(6).split(",");
        ArrayList<Integer> gId = new ArrayList<>();
        for(String g:groups){
            try {
                gId.add(Integer.parseInt(g));
            }catch (NumberFormatException e){
                //do nothing
            }
        }
        return new Student(userName,password,email,Integer.parseInt(level),gId);
    }
    return null;
    }

    /**
     * Identify if the user is a student user
     * @param id the user ID
     * @return The boolean representing if the user is a student user
     */
    public boolean IsStudent(int id){
        return this.userGate.readRow(id).size()>0;
    }

    /**
     * Identify if the user log in successfully
     * @param userName the username
     * @param password the password
     * @return The Integer representing if the login is valid
     */
    public int LogIn(String userName,String password){
        int userType;
        userType = getUserType(userName);
        int userID = getID(userName,userGate);
        if(userType==STUDENT){
            String pass = userGate.readByID(222, 3, userID).get(0);
            if (pass.equals(password)) {
                return userID;
            }
        }

        return -1;
    }

    /**
     * Get joined group
     * @param id the user ID
     * @return The list of joined groups
     */
    public List<Integer> getJoinedGroup(int id){
        return readUser(id).getJoinedGroup();
    }
}
