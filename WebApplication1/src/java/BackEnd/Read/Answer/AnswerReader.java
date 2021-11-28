package BackEnd.Read.Answer;

import BackEnd.Read.Reader;

public abstract class AnswerReader extends Reader {
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
}
