package BackEnd.Read.Group;

public class ReadCreator extends GroupReader {
    private int groupID;

    public ReadCreator(int groupID) {
        this.groupID = groupID;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where id='" + groupID + "'";
        return readInfo(sql, CREATORCol, INT);
    }
}
