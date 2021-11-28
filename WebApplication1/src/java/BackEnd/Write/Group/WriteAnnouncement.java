package Write.Group;

public class WriteAnnouncement extends GroupWriter {
    private final int groupID;
    private final String post;

    public WriteAnnouncement(int groupID, String post) {
        this.groupID = groupID;
        this.post = post;
    }

    @Override
    public Object set() {
        return setGroupInfo(POST, groupID, post);
    }
}
