package Read.Test;

import Read.Question.QuestionReader;
import Read.Question.ReadAnswer;

public class ReadAllAnswers extends TestReader {
    private final int testID;

    public ReadAllAnswers(int testID) {
        this.testID = testID;
    }

    @Override
    public Object read() {

        String allQuestionID = (String) new ReadQuestions(testID).read();
        if (allQuestionID.equals(FAILED + "")) {
            return FAILED;
        }

        String[] questionList = allQuestionID.split(",");
        String[] answers = new String[questionList.length];
        for (int i = 0; i < questionList.length; i++) {
            QuestionReader questionReader = new ReadAnswer(Integer.parseInt(questionList[i]));
            answers[i] = (String) questionReader.read();
        }
        return answers;
    }

}
