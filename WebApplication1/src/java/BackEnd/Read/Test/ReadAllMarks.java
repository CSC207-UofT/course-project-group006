package BackEnd.Read.Test;

import BackEnd.Read.Question.QuestionReader;
import BackEnd.Read.Question.ReadAnswer;
import BackEnd.Read.Question.ReadMark;

public class ReadAllMarks extends TestReader {
    private final int testID;

    public ReadAllMarks(int testID) {
        this.testID = testID;
    }

    @Override
    public Object read() {
        String allQuestionID = (String) new ReadQuestions(testID).read();
        if (allQuestionID.equals(FAILED + "")) {
            return FAILED;
        }

        String[] questionList = allQuestionID.split(",");
        int[] marks = new int[questionList.length];
        for (int i = 0; i < questionList.length; i++) {
            QuestionReader questionReader = new ReadMark(Integer.parseInt(questionList[i]));
            marks[i] = (int) questionReader.read();
        }
        return marks;
    }
}
