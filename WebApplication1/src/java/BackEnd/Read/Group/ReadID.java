package BackEnd.Read.Group;

public class ReadID extends GroupReader {
    private final String groupName;

    public ReadID(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where name='" + groupName + "'";
        return readInfo(sql, IDCol, INT);
    }
}
