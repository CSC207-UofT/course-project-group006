package Write.Test;

import Write.Writer;

public abstract class TestWriter extends Writer {

    protected final String TABLE = "TEST";
    protected final String ID = "id";
    protected final String NAME = "name";
    protected final String AUTHOR = "author";
    protected final String DUE = "date";
    protected final String PRICE = "price";
    protected final String QUESTIONS = "questions";


    protected final int IDCol = 1;
    protected final int NAMECol = 2;
    protected final int AUTHORCol = 3;
    protected final int DUECol = 4;
    protected final int PRICECol = 5;
    protected final int QUESTIONSCol = 6;

    public Object setTestInfo(String colName, int testID, String info) {
        return updateInfo(testID, info, TABLE, colName);
    }
    public Object setTestmark(String colName, int testID, int info) {
        return updatetest(testID, info, "TESTANSWER", colName);
    }


    public Object setTestInfo(String colName, int testID, int info) {
        return updateInfo(testID, info, TABLE, colName);
    }

}

