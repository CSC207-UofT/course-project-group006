package BackEnd;

import java.util.ArrayList;

public class Student extends User {

    //private ArrayList<String> joinedGroup;
    private int level;
    private ArrayList<Word> wordLearnt;

    public Student(String username, String password, String email) {
        super(username, password, email);
        //this.joinedGroup  = new ArrayList<>();
        this.level = 0;
        this.wordLearnt = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    //public ArrayList<String> getJoinedGroup() {
    //    return joinedGroup;
    //}
    public boolean learnWord(Word w){
        wordLearnt.add(w);
        return true;
    }

    public boolean frogetWord(Word w){
        return wordLearnt.remove(w);
    }

    public String toString(){
        return this.getUsername()+this.level;
    }



    //public ArrayList<String> getWordLearnt() {
       // return wordLearnt;
    //}


}