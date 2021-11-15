package BackEnd.Read.Group;

import java.util.ArrayList;
import java.util.HashMap;

public class ReadAllGroups extends GroupReader {
    @Override
    public Object read() {
        HashMap<Integer, String> result = new HashMap<>();
        String sql = "select * from " + TABLE;

        try {
            ArrayList<Integer> IDList = (ArrayList<Integer>) readAll(sql, IDCol, INT);
            ArrayList<String> nameList = (ArrayList<String>) readAll(sql, NAMECol, STRING);
            for (int i = 0; i < IDList.size(); i++) {
                result.put(IDList.get(i), nameList.get(i));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return FAILED;
        }

    }
}
