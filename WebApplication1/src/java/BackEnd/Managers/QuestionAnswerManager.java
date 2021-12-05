package BackEnd.Managers;

import BackEnd.Gateways.QuestionGateway;
import BackEnd.Interfaces.GeneralReadWriter;


import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerManager {
    private final GeneralReadWriter QuestionAnswerGate;

    public QuestionAnswerManager(GeneralReadWriter readWriter) {
        this.QuestionAnswerGate = readWriter;
    }


    public int createQuestionAnswer(int questionID, String answer, int studentID, int groupID, GeneralReadWriter QuestionAnswerGate) {
        List<String> list = new ArrayList<>();
        list.add(questionID + "");
        list.add(answer);
        list.add(studentID + "");
        list.add(groupID + "");
        List<String> result = QuestionAnswerGate.write(1, list);
        if (result != null && result.size() != 0) {
            int QuestionAnswerID = Integer.parseInt(result.get(0));
            gradeQuestion(questionID, answer, QuestionAnswerID);
            return 1;
        } else {
            return -1;
        }

    }

    public void gradeQuestion(int questionID, String answer, int questionAnswerID) {
        GeneralReadWriter questionReader = new QuestionGateway();
        String correct_answer = questionReader.readRow(questionID).get(3);
        String mark = questionReader.readRow(questionID).get(4);
        if (correct_answer.equals(answer)) {
            List<String> info = new ArrayList<>();
            info.add(Integer.toString(questionAnswerID));
            info.add(mark);
            QuestionAnswerGate.write(4, info);
        } else {
            List<String> info = new ArrayList<>();
            info.add(Integer.toString(questionAnswerID));
            info.add(Integer.toString(0));
            QuestionAnswerGate.write(4, info);

        }
    }

}

