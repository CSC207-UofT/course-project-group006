package Read.Teacher;


public class ReadName extends TeacherReader {
    private final int ID;

    public ReadName(int ID) {
        this.ID = ID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + ID + "'";
        return readInfo(sql, IDCol, STRING);
    }
}
