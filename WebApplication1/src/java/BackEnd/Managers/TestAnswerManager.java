package BackEnd.Managers;

import BackEnd.Gateways.QuestionGateway;
import BackEnd.Gateways.TestGateway;
import BackEnd.Interfaces.GeneralReadWriter;
import BackEnd.Interfaces.ReadIDName;

import java.util.ArrayList;
import java.util.List;

public class TestAnswerManager {
    private final GeneralReadWriter TestAnswerGate;
    private final GeneralReadWriter TestGate;
    private final ReadIDName QuestionAnswerGate;
    private int total_mark = 0;

    public TestAnswerManager(GeneralReadWriter readWriter, GeneralReadWriter rw, ReadIDName r) {
        this.TestAnswerGate = readWriter;
        this.TestGate = rw;
        this.QuestionAnswerGate = r;
    }

    public int createTestAnswer(int testID, int studentID, GeneralReadWriter TestAnswerGate, GeneralReadWriter TestGate, ReadIDName QuestionAnswerGate) {

        List<String> list = new ArrayList<>();
        list.add(testID + "");
        list.add(studentID + "");
        List<String> result = TestAnswerGate.write(1, list);
        List<String> questions = TestGate.readByID(222, 6, testID);
        String[] all_questions = questions.get(0).split(",");
        if (result != null && result.size() != 0) {
            int TestAnswerID = Integer.parseInt(result.get(0));
            for (String i : all_questions) {
                List<String> mark = QuestionAnswerGate.readByIDName(111, studentID, 4, Integer.parseInt(i));
                if (mark.size() > 0){
                    total_mark += Integer.parseInt(mark.get(0));}
            }
            List<String> new_mark = new ArrayList<>();
            new_mark.add(TestAnswerID + "");
            new_mark.add(total_mark + "");
            TestAnswerGate.write(4, new_mark);
            return total_mark;
        } else {
            return -1;
        }
    }
}

