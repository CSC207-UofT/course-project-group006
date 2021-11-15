package BackEnd.Read.Question;

public class ReadMark extends QuestionReader {
    private final int ID;

    public ReadMark(int ID) {
        this.ID = ID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + ID + "'";
        return readInfo(sql, MARKCol, INT);
    }
}
