package Write.Group;


public class DeleteGroup extends GroupWriter {
    private final int ID;

    public DeleteGroup(int ID) {
        this.ID = ID;
    }

    @Override
    public Object set() {
        return delete(ID);
    }
}
