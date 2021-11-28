package Read.Student;

import java.util.HashMap;

public class ReadGroups extends StudentReader {
    private final int studentID;

    public ReadGroups(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public Object read() {
        HashMap<Integer, String> result = new HashMap<>();
        String sql = "select * from " + TABLE + " where id='" + studentID + "'";
        String groups = (String) readInfo(sql, GROUPSCol, STRING);
        String IDs = (String) readInfo(sql, IDCol, STRING);
        if (groups.equals(FAILED + "")) {
            return FAILED;
        }

        try {
            String[] groupsList = groups.trim().split(",");
            String[] IDList = IDs.trim().split(",");
            for (int i = 0; i < groupsList.length; i++) {
                result.put(Integer.parseInt(IDList[i]), groupsList[i]);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return FAILED;
        }

    }
}
