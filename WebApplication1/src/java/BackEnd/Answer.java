package BackEnd1;

import QuestionTypes.Question;
import QuestionTypes.QuestionInterface;
import java.util.ArrayList;


public class Answer {
    private String student;
    private int about;
    private String[] answer;
    public Answer(int about, String student, String[] answer){
        this.about=about;
        this.student=student;
        this.answer=answer;
    }
    
    public int getAbout(){
        return about;
    }

}
