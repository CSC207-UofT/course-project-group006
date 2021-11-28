package Write.Answer;

public class WriteMark extends AnswerWriter {
    private final int answerID;
    private final int mark;

    public WriteMark(int answerID, int mark) {
        this.answerID = answerID;
        this.mark = mark;
    }

    @Override
    public Object set() {
        return setAnswerInfo(MARK, answerID, mark);
    }
}
