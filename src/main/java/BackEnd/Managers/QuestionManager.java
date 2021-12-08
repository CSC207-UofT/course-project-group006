package BackEnd.Managers;

import BackEnd.Entities.Question;
import BackEnd.Interfaces.GeneralReadWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * The question manager
 */
public class QuestionManager {
    private final GeneralReadWriter questionGate;

    /**
     * Constructor of QuestionManager
     * @param questionGate the question gate
     */
    public QuestionManager(GeneralReadWriter questionGate) {
        this.questionGate = questionGate;
    }

    /**
     * Add question
     * @param name the Name
     * @param question the question
     * @param answer the answer
     * @param mark the mark
     * @return the Integer represent if add question successfully
     */
    public int addQuestion(String name, String question, String answer, int mark) {
        Question a = new Question(name, question, answer, mark);
        List<String> info = new ArrayList<>();
        info.add(a.getName());
        info.add(a.getQuestion());
        info.add(a.getAnswer());
        info.add(a.getMarks() + "");
        List<String> result = questionGate.write(1, info);
        if (result != null) {
            return Integer.parseInt(result.get(0));
        }
        return -1;
    }

    /**
     * Get question
     * @param qID the question ID
     * @return The question
     */
    public Question getQuestion(int qID){
        List<String> question = questionGate.readRow(qID);

        Question myQ = new Question(question.get(1), question.get(2), question.get(3),
                Integer.parseInt(question.get(4)),qID);
        return myQ;

    }

    /**
     * Get question
     * @param id the question ID
     * @return The string of question
     */
    public String getquestion(int id){
        return getQuestion(id).getQuestion();
    }

    /**
     * Get answer
     * @param id the question ID
     * @return The string of answer
     */
    public String getAnswer(int id){
        return getQuestion(id).getAnswer();
    }

    /**
     * Get mark
     * @param id the question ID
     * @return The int of mark
     */
    public int getMark(int id){
        return getQuestion(id).getMarks();
    }



}

