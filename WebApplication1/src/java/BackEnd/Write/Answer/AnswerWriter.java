package Write.Answer;

import Write.Writer;

public abstract class AnswerWriter extends Writer {
    protected final String TABLE = "QUESTIONANSWER";
    protected final String ID = "id";
    protected final String QUESTIONId = "questionID";
    protected final String ANSWER = "answer";
    protected final String MARK = "mark";
    protected final String STUDENTId = "studentID";

    protected final int IDCol = 1;
    protected final int QUESTIONIdCol = 2;
    protected final int ANSWERCol = 3;
    protected final int MARKCol = 4;
    protected final int STUDENTIdCol = 5;

    public Object setAnswerInfo(String colName, int answerID, String info) {
        return updateInfo(answerID, info, TABLE, colName);
    }

    public Object setAnswerInfo(String colName, int answerID, int info) {
        return updateInfo(answerID, info, TABLE, colName);
    }
}
