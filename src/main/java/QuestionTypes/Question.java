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

    /**
     * Getter method of the question
     * @return Question in the test
     */
    public String getQuestion(){
        return this.question;
    }

    /**
     * Score student gets in each question
     * @param ans Answer to the question
     * @return the result of what a student get by answering the question
     */
    public int score(String ans){
        if(!ans.equals("") &answer.contains(ans)){
            return marks;
        }
        return 0;
    }

    /**
     * Getter method of the answer of question
     * @return Answer to question in the test
     */
    public String getAnswer(){
        return this.answer;
    }

    /**
     * Getter method of the mark of question
     * @return Mark to question in the test
     */
    public int getMarks(){
        return this.marks;
    }
}
