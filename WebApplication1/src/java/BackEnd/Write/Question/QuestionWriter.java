package Write.Question;

import Write.Writer;


public abstract class QuestionWriter extends Writer {

    protected final String TABLE = "QUESTION";
    protected final String ID = "id";
    protected final String NAME = "name";
    protected final String QUESTION = "question";
    protected final String ANSWER = "answer";


    protected final int IDCol = 1;
    protected final int NAMECol = 2;
    protected final int QUESTIONCol = 3;
    protected final int ANSWERCol = 4;
    protected final int MARKCol = 5;

    public Object setQuestionInfo(String colName, int teacherID, String info) {
        return updateInfo(teacherID, info, TABLE, colName);
    }
}
