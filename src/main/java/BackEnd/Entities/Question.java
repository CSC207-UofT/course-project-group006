package BackEnd.Entities;

import BackEnd.Interfaces.QuestionInterface;

public class Question implements QuestionInterface {
    private String name;
    private String question;
    private String answer;
    private int marks;
    private int id;

    /**
     * Constructor for Question
     * @param name question name
     * @param question question body
     * @param answer answer to question
     * @param mark mark of question
     */
    public Question(String name, String question, String answer, int mark){

        this.name = name;
        this.question=question;
        this.answer=answer;
        this.marks = mark;
    }
    /**
     * Constructor for Question
     * @param name question name
     * @param question question body
     * @param answer answer to question
     * @param mark mark of question
     * @param id question ID
     */
    public Question(String name, String question, String answer, int mark,int id){

        this.name = name;
        this.question=question;
        this.answer=answer;
        this.marks = mark;
        this.id=id;
    }
    /**
     * Getter method of question name
     * @return Question name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Getter method of ID
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Getter method of question
     * @return Question
     */
    public String getQuestion(){
        return this.question;
    }

    /**
     * Method to return the score of a student do right
     * @param ans Answer to the question
     * @return Mark gotten by student
     */
    public int score(String ans){
        if(!ans.equals("") &answer.contains(ans)){
            return marks;
        }
        return 0;
    }

    /**
     * Getter method of answer
     * @return Answer to the question
     */
    public String getAnswer(){
        return this.answer;
    }

    /**
     * Getter method of marks
     * @return Marks of the question
     */
    public int getMarks(){
        return this.marks;
    }
}
