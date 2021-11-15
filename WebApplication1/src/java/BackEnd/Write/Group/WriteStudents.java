package Write.Group;

public class WriteStudents extends GroupWriter {
    private final int groupID;
    private final String students;

    public WriteStudents(String students, int groupID) {
        this.groupID = groupID;
        this.students = students;
    }

    @Override
    public Object set() {
        return setGroupInfo(STUDENTS, groupID, students);
    }
}
