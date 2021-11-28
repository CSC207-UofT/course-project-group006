package BackEnd.Read.Answer;

public class ReadID extends AnswerReader{
    private final int mark;

    public ReadID(int mark) {
        this.mark = mark;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where mark='" + mark + "'";
        return readInfo(sql, IDCol, INT);
    }
}
