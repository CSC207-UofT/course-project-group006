package Read.Teacher;

import Read.Reader;

public abstract class TeacherReader extends Reader {
    protected final String TABLE = "TEACHER";
    protected final String ID = "id";
    protected final String NAME = "name";
    protected final String PASS = "pass";
    protected final String EMAIL = "email";
    protected final String GROUPS = "groupID";
    protected final String TESTS = "testID";


    protected final int IDCol = 1;
    protected final int NAMECol = 2;
    protected final int PASSCol = 3;
    protected final int EMAILCol = 5;
    protected final int GROUPSCol = 6;
    protected final int TESTSCol = 7;

}

