package BackEnd.Read.Answer;

public class ReadAnswerByMore extends AnswerReader {
    private final int studentID;
    private final int questionID;
    private final int groupID;

    public ReadAnswerByMore(int studentID, int questionID, int groupID) {
        this.groupID = groupID;
        this.questionID = questionID;
        this.studentID = studentID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where groupID = ' " + groupID + " ' and studentID = '" + studentID + " ' and questionID = '" + questionID + " '";
        return readInfo(sql, ANSWERCol, STRING);
    }
}
