package Read.Test;

public class ReadMark extends TestReader {
    private final int studentID;

    public ReadMark(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + "TESTANSWER" + " where studentID='" + studentID + "'";
        return readInfo(sql, IDCol, INT);
    }
}