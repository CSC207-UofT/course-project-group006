package BackEnd1;

import java.util.ArrayList;

public class Student extends User {

    //private ArrayList<String> joinedGroup;
    private int level;
    private ArrayList<Word> wordLearnt;

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
        this.wordLearnt = new ArrayList<>();
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
     * This student will get some word to learn
     *
     * @param w the word this student is going to learn
     */
    public boolean learnWord(Word w){
        wordLearnt.add(w);
        return true;
    }

    /**
     * This student forgot some word
     *
     * @param w the word this student has forgotten
     * @return the word this student forgot
     */

    public boolean frogetWord(Word w){
        return wordLearnt.remove(w);
    }

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