package Read.Teacher;

public class ReadID extends TeacherReader {
    private final String name;

    public ReadID(String name) {
        this.name = name;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where name='" + name + "'";
        return readInfo(sql, IDCol, INT);
    }
}
