package Write.Student;

import Write.Writer;

public abstract class StudentWriter extends Writer {

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




    public Object setStudentInfo(String colName, int teacherID, String info) {
        return updateInfo(teacherID, info, TABLE, colName);
    }

    public Object setStudentInfo(String colName, int teacherID, int info) {
        return updateInfo(teacherID, info, TABLE, colName);
    }
}

