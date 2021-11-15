package Read.Test;

public class ReadID extends TestReader {
    private final String name;
    private final int author;
    private final int price;

    public ReadID(String name, int author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public Object read() {
        String sql = "select * from " + TABLE + " where name='" + name + "' and author= " + author ;
        return readInfo(sql, IDCol, INT);
    }
}
