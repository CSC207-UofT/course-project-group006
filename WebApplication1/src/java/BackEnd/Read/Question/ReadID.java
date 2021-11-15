package BackEnd.Read.Question;

public class ReadID extends QuestionReader {
    private final String question;
    private final String answer;

    public ReadID(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where question=''" + question + "' and answer=''" + answer + "'";
        return readInfo(sql, IDCol, INT);
    }
}
