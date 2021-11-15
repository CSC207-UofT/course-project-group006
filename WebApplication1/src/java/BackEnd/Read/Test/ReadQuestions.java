package Read.Test;

public class ReadQuestions extends TestReader {
    private final int ID;

    public ReadQuestions(int ID) {
        this.ID = ID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + ID + "'";
        return readInfo(sql, QUESTIONSCol, STRING);
    }
}
