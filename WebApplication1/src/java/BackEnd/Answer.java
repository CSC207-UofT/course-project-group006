package BackEnd;

import QuestionTypes.Question;

import java.util.ArrayList;


public class Answer {
    private String student;
    private Test about;
    private String[] answer;

    /**
     * Constructor for Answer
     * @param about States what the test answer is about
     * @param student States who the student is
     * @param answer The answer for a question
     */
    public Answer(Test about, String student, String[] answer){
        this.about=about;
        this.student=student;
        this.answer=answer;
    }

    /**
     * Grades the questions automatically
     * @return return the result of the test
     */
    public int[] Autograde(){
        ArrayList<Question> q = about.getQuestions();
        int[] result = new int[q.size()];
        for(int i=0;i<q.size();i++){
            result[i]=q.get(i).score(answer[i]);
        }
        return result;

    }

    /**
     * Getter method for the about
     * @return return the about
     */
    public Test getAbout(){
        return about;
    }

}
