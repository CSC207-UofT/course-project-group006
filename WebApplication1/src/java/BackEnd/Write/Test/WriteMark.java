package Write.Test;

public class WriteMark extends TestWriter {
    private final int testID;
    private final int mark;

    public WriteMark(int testID, int mark) {
        this.testID = testID;
        this.mark = mark;
    }

    @Override
    public Object set() {
        return setTestmark("mark", testID, mark);
    }
}
