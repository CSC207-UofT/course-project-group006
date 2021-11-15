package Write.Teacher;

import Read.Group.GroupReader;
import Read.Reader;
import Read.Teacher.ReadID;
import Read.Teacher.TeacherReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteNewTeacher extends TeacherWriter {

    private final String name;
    private final String pass;
    private final String email;
    private final int type;

    public WriteNewTeacher(String name, String pass, String email, int type) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.type = type;
    }

    @Override
    public Object set() {
        //check if name already exists
        if (Reader.hasDuplicateNames(TABLE, name)) {
            return FAILED;
        }
        if (Reader.hasDuplicateNames("STUDENT", name)) {
            return FAILED;
        }

        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());//get date
        String groupID = "";
        String testID = "";

        try {
            Connection connection = getConnection();

            //register teacher
            String sql = "insert into " + TABLE + " (name,pass,date,email,groupID,testID) VALUE (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, groupID);
            preparedStatement.setString(6, testID);
            preparedStatement.executeUpdate();
            connection.close();

            //find teacher ID
            TeacherReader teacherReader = new ReadID(name);
            return teacherReader.read();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }


    }
}
