package Write.Teacher;

import Write.Writer;

public abstract class TeacherWriter extends Writer {
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


    public Object setTeacherInfo(String colName, int teacherID, String info) {
        return updateInfo(teacherID, info, TABLE, colName);
    }

    public Object setTeacherInfo(String colName, int teacherID, int info) {
        return updateInfo(teacherID, info, TABLE, colName);
    }
}

