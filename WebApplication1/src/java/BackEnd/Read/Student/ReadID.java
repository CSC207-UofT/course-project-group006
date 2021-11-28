package Read.Student;


public class ReadID extends StudentReader {
    private final String name;

    public ReadID(String name) {
        this.name = name;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where name='" + name + "'";
        return readInfo(sql, IDCol, INT);
    }
}
