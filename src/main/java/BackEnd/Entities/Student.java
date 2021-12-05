package BackEnd.Entities;


import java.util.ArrayList;

public class Student extends User {

    //private ArrayList<String> joinedGroup;
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
        //this.joinedGroup  = new ArrayList<>();
        this.level = 0;

    }

    public Student(String username, String password, String email,int level) {
        super(username, password, email);
        //this.joinedGroup  = new ArrayList<>();
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

    //public ArrayList<String> getJoinedGroup() {
    //    return joinedGroup;
    //}



    /**
     *
     * @return this student's username with level in string form
     */

    public String toString(){
        return this.getUsername()+this.level;
    }



    //public ArrayList<String> getWordLearnt() {
       // return wordLearnt;
    //}


}