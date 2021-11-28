package Read.Test;

public class ReadAve extends TestReader {
    private final int studentID;

    public ReadAve(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + "TESTANSWER" + " where studentID='" + studentID + "'";
        return readaverage(sql, 4, INT);
    }
}