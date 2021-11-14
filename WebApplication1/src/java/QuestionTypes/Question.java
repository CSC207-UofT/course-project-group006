package QuestionTypes;

public class Question implements QuestionInterface{
    private String question;
    private String answer;
    private int marks;
    public Question(String question,String answer,int mark){
        this.question=question;
        this.answer=answer;
        this.marks = mark;
    }
    public String getQuestion(){
        return this.question;
    }
    public int score(String ans){
        if(!ans.equals("") &answer.contains(ans)){
            return marks;
        }
        return 0;
    }
    public String getAnswer(){
        return this.answer;
    }
    public int getMarks(){
        return this.marks;
    }
}
