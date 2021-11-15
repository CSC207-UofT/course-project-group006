package Write.Group;

import Read.Group.GroupReader;
import Read.Group.ReadID;
import Read.Reader;
import Read.Teacher.ReadGroups;
import Read.Teacher.TeacherReader;
import Write.Teacher.TeacherWriter;
import Write.Teacher.WriteGroups;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteNewGroup extends GroupWriter {
    private final int teacherID;
    private final String groupName;

    public WriteNewGroup(int teacherID, String groupName) {
        this.teacherID = teacherID;
        this.groupName = groupName;
    }

    @Override
    public Object set() {
        try {

            GroupReader groupReader;
            TeacherReader teacherReader;
            TeacherWriter teacherWriter;

            //check if name already exists
            groupReader = new ReadID(groupName);
            if (Reader.hasDuplicateNames(TABLE, groupName)) {
                return FAILED;
            }

            //create
            String students = "";
            String posts = "";
            String testID = "";
            //register a group:id(auto-generated)|name|creator|students|posts
            Connection connection = getConnection();
            String sql = "insert into " + TABLE + " (name,creator,students,posts,testID) VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, groupName);
            preparedStatement.setInt(2, teacherID);
            preparedStatement.setString(3, students);
            preparedStatement.setString(4, posts);
            preparedStatement.setString(5, testID);
            preparedStatement.executeUpdate();
            connection.close();

            //get group id
            int groupID = (int) groupReader.read();

            //add new group id to string
            teacherReader = new ReadGroups(teacherID);
            String allGroups = (String) teacherReader.read();
            if (allGroups.length() == 0) {
                allGroups = groupID + "";
            } else {
                allGroups = groupID + "," + allGroups;
            }

            //add group to teacher
            teacherWriter = new WriteGroups(teacherID, allGroups);
            if ((int) teacherWriter.set() == FAILED) {
                return FAILED;
            }

            return groupID;

        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }
}
