package BackEnd.Managers;

import BackEnd.Entities.Test;
import BackEnd.Entities.Question;
import BackEnd.Entities.QuestionInterface;
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

}

