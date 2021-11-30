package BackEnd.Read.Student;

import BackEnd.Read.Reader;

public abstract class StudentReader extends Reader {

    protected final String TABLE = "STUDENT";
    protected final String ID = "id";
    protected final String NAME = "name";
    protected final String PASS = "pass";
    protected final String DATE = "date";
    protected final String EMAIL = "email";
    protected final String GROUPS = "groupID";
    protected final String TESTS = "testID";
    protected final String ANSWERS = "answerID";


    protected final int IDCol = 1;
    protected final int NAMECol = 2;
    protected final int PASSCol = 3;
    protected final int DATECol = 4;
    protected final int EMAILCol = 5;
    protected final int GROUPSCol = 7;
    protected final int TESTSCol = 8;
    protected final int ANSWERSCol = 9;

}

