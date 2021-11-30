package BackEnd.oldstuff.Read.Test;

import BackEnd.oldstuff.Read.Question.QuestionReader;
import BackEnd.oldstuff.Read.Question.ReadQuestion;

public class ReadAllQuestions extends TestReader {
    private final int testID;

    public ReadAllQuestions(int testID) {
        this.testID = testID;
    }

    @Override
    public Object read() {
        String allQuestionID = (String) new ReadQuestions(testID).read();
        if (allQuestionID.equals(FAILED + "")) {
            return FAILED;
        }
        String[] questionList = allQuestionID.split(",");

        String[] result = new String[questionList.length];
        for (int i = 0; i < questionList.length; i++) {
            QuestionReader questionReader = new ReadQuestion(Integer.parseInt(questionList[i]));
            result[i] = (String) questionReader.read();
        }
        return result;

    }
}
