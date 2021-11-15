package BackEnd.Read.Group;

public class ReadStudents extends GroupReader {
    private final int groupID;

    public ReadStudents(int groupID) {
        this.groupID = groupID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + groupID + "'";
        return readInfo(sql, IDCol, INT);
    }
}
