package Write.Teacher;

public class WriteTests extends TeacherWriter {
    private final int teacherID;
    private final String tests;

    public WriteTests(int teacherID, String tests) {
        this.tests = tests;
        this.teacherID = teacherID;
    }


    @Override
    public Object set() {
        return setTeacherInfo(TESTS, teacherID, tests);
    }
}
