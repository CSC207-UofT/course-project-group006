package BackEnd.Read.Answer;

public class ReadQuestionID extends AnswerReader {
    private final int ID;

    public ReadQuestionID(int ID) {
        this.ID = ID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + ID + "'";
        return readInfo(sql, QUESTIONIdCol, INT);
    }
}
