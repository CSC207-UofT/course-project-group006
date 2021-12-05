package BackEnd.Entities;


import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    private ArrayList<Integer> joinedGroup;
    private int level;


    /**
     * Construct a student giving the username, password and email
     *
     * @param username the username of this student account
     * @param password the password of this student account
     * @param email the email of this student account
     */

    public Student(String username, String password, String email) {
        super(username, password, email);
        this.joinedGroup  = new ArrayList<>();
        this.level = 0;

    }

    public Student(String username, String password, String email,int level, ArrayList<Integer> joinedGroup) {
        super(username, password, email);
        this.joinedGroup  = joinedGroup;
        this.level = level;

    }

    /**
     * Get the level of this student
     *
     * @return the level of the student
     */

    public int getLevel() {
        return level;
    }


    /**
     *
     * @return this student's username with level in string form
     */

    public String toString(){
        return this.getUsername()+this.level;
    }

    /**
     * Getter method of joined groups
     * @return The group a student joined
     */
    public ArrayList<Integer> getJoinedGroup() {
        return joinedGroup;
    }


}