import java.util.ArrayList;

public class Guest extends User{
    private ArrayList<String> wordLearnt;

    public Guest() {
        this.wordLearnt = new ArrayList<>();
    }

    public ArrayList<String> getWordLearnt() {
        return wordLearnt;
    }
}
