package QuestionTypes;

public class Question {
    public String question;
    public String answer;
    public int marks;
    public Question(String question,String answer,int mark){
        this.question=question;
        this.answer=answer;
        this.marks = mark;
    }
    public int score(String ans){
        if(ans.equals(answer)){
            return marks;
        }
        return 0;
    }
}
