package Write.Teacher;

public class WriteGroups extends TeacherWriter {
    private final int teacherID;
    private final String groups;

    public WriteGroups(int teacherID, String groups) {
        this.groups = groups;
        this.teacherID = teacherID;
    }


    @Override
    public Object set() {
        return setTeacherInfo(GROUPS, teacherID, groups);
    }
}
