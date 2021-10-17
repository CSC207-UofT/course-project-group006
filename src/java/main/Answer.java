import QuestionTypes.Question;

import java.util.ArrayList;
import java.util.List;

public class Answer {
    private String student;
    private Test about;
    private String[] answer;
    public Answer(Test about, String student, String[] answer){
        this.about=about;
        this.student=student;
        this.answer=answer;
    }
    public int[] Autograde(){
        ArrayList<Question> q = about.getQuestions();
        int[] result = new int[q.size()];
        for(int i=0;i<q.size();i++){
            result[i]=q.get(i).score(answer[i]);
        }
        return result;

    }
    public Test getAbout(){
        return about;
    }

}
