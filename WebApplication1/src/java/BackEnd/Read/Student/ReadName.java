package Read.Student;

public class ReadName extends StudentReader {
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
