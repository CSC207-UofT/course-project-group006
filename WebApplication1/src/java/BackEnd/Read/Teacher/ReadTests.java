package Read.Teacher;

public class ReadTests extends TeacherReader {
    private final int ID;

    public ReadTests(int ID) {
        this.ID = ID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + ID + "'";
        return readInfo(sql, TESTSCol, STRING);
    }
}
