package Write.Test;

public class WriteQuestions extends TestWriter {
    private final String questions;
    private final int testID;

    public WriteQuestions(String questions, int testID) {
        this.questions = questions;
        this.testID = testID;
    }

    @Override
    public Object set() {
        return setTestInfo(QUESTIONS, testID, questions);
    }
}
