package BackEnd.Read.Answer;

public class ReadMark extends AnswerReader{
    private final int studentID;
    private final int questionID;

    public ReadMark(int studentID, int questionID) {
        this.questionID = questionID;
        this.studentID = studentID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + "QUESTIONANSWER" + " where questionID= " + questionID + " and studentID= " + studentID;
        return readInfo(sql, 4, INT);
    }
}

