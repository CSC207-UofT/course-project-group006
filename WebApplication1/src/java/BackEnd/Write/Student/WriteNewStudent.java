package Write.Student;


import Read.Reader;
import Read.Student.ReadID;
import Read.Student.StudentReader;
import Read.Teacher.TeacherReader;

import java.sql.*;

public class WriteNewStudent extends StudentWriter {
    private final String name;
    private final String pass;
    private final String email;
    private final int type;

    public WriteNewStudent(String name, String pass, String email, int type) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.type = type;
    }

    @Override
    public Object set() {
        try {
            if (Reader.hasDuplicateNames(TABLE, name)) {
                return FAILED;
            }
            if (Reader.hasDuplicateNames("TEACHER", name)) {
                return FAILED;
            }


            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());//get date
            String groupID = "";
            String testID = "";
            String answerID = "";
            String words = "";
            int level = 1;

            //register as a student
            Connection connection = getConnection();
            String sql = "insert into " + TABLE + " (name,pass,date,email,words,groupID,testID,answerID,level) VALUE (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, words);
            preparedStatement.setString(6, groupID);
            preparedStatement.setString(7, testID);
            preparedStatement.setString(8, answerID);
            preparedStatement.setInt(9, level);
            preparedStatement.executeUpdate();
            connection.close();


            //find student ID
            StudentReader studentReader = new ReadID(name);
            return studentReader.read();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }

    }
}

