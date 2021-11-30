package BackEnd.Read.Answer;

public class ReadAnswerByID extends AnswerReader {
    private final int ID;

    public ReadAnswerByID(int ID) {
        this.ID = ID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + ID + "'";
        return readInfo(sql, ANSWERCol, STRING);
    }
}
