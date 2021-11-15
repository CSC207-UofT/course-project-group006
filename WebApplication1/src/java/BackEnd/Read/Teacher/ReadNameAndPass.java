package Read.Teacher;

public class ReadNameAndPass extends TeacherReader {
    private final String name;
    private final String pass;

    public ReadNameAndPass(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    @Override
    public Object read() {

        String sql = "select * from " + TABLE + " where name='" + name + "' and pass='" + pass + "'";
        return readInfo(sql, IDCol, INT);
    }
}
