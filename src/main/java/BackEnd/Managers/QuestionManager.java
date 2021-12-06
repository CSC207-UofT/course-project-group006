package BackEnd.Managers;

import BackEnd.Entities.Question;
import BackEnd.Interfaces.GeneralReadWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionManager {
    private HashMap<Integer, Question> allQuestions;
    private GeneralReadWriter questionGate;

    public QuestionManager(HashMap<Integer, Question> allQuestions) {
        this.allQuestions = allQuestions;
    }

    public QuestionManager(GeneralReadWriter questionGate) {
        this.questionGate = questionGate;
    }

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

    public Question getQuestion(int qID){
        List<String> question = questionGate.readRow(qID);
        Question myQ = new Question(question.get(1), question.get(2), question.get(3),
                Integer.parseInt(question.get(4)),qID);
        return myQ;
    }
    public String getquestion(int id){
        return getQuestion(id).getQuestion();
    }
    public String getAnswer(int id){
        return getQuestion(id).getAnswer();
    }
    public int getMark(int id){
        return getQuestion(id).getMarks();
    }



}

