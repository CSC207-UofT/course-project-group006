package Write.Group;

import Write.Writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public abstract class GroupWriter extends Writer {
    protected final String TABLE = "STUDYGROUP";
    protected final String ID = "id";
    protected final String NAME = "name";
    protected final String CREATOR = "creator";
    protected final String STUDENTS = "students";
    protected final String POST = "posts";
    protected final String TESTS = "testID";

    protected final int IDCol = 1;
    protected final int NAMECol = 2;
    protected final int CREATORCol = 3;
    protected final int STUDENTSCol = 4;
    protected final int POSTCol = 5;
    protected final int TESTSCol = 6;


    public Object setGroupInfo(String colName, int groupID, String info) {
        return updateInfo(groupID, info, TABLE, colName);
    }

    public Object setGroupInfo(String colName, int groupID, int info) {
        return updateInfo(groupID, info, TABLE, colName);
    }

    public Object delete(int groupID) {
        try {
            Connection connection = getConnection();
            String sql = "delete from " + TABLE + " where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, groupID);
            preparedStatement.executeUpdate();
            return SUCCESS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }
    }


}


