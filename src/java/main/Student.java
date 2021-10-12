import java.util.ArrayList;

public class Student extends User {

    private ArrayList<String> joinedGroup;
    private int level;
    private ArrayList<String> wordLearnt;

    public Student(String username, String password, String email) {
        super(username, password, email);
        this.joinedGroup  = new ArrayList<>();
        this.level = 0;
        this.wordLearnt = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<String> getJoinedGroup() {
        return joinedGroup;
    }

    public ArrayList<String> getWordLearnt() {
        return wordLearnt;
    }


}