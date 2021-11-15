package Read.Question;

public class ReadQuestion extends QuestionReader {
    private final int ID;

    public ReadQuestion(int ID) {
        this.ID = ID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + ID + "'";
        return readInfo(sql, QUESTIONCol, STRING);
    }
}
