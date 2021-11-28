package Write.Group;

import Read.Group.GroupReader;

public class WriteName extends GroupWriter {
    private final int groupID;
    private final String name;

    public WriteName(int groupID, String name) {
        this.groupID = groupID;
        this.name = name;
    }

    @Override
    public Object set() {
        //check if name already exists
        if (GroupReader.hasDuplicateNames(TABLE, name)) {
            return FAILED;
        }
        return setGroupInfo(NAME, groupID, name);
    }

}
