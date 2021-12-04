package BackEnd.Entities;

import BackEnd.Entities.Question;
import BackEnd.Entities.QuestionInterface;


public class Answer {
    private int student;
    private int about;
    private String[] answer;
    public Answer(int about, int student, String[] answer){
        this.about=about;
        this.student=student;
        this.answer=answer;
    }
    
    public int getAbout(){
        return about;
    }

}
