package Write;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Writer implements Writable {


    public String driver = "com.mysql.cj.jdbc.Driver";//驱动程序名
    public String url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5449780";//url指向要访问的数据库study
    public String user = "sql5449780";//MySQL配置时的用户名
    public String password = "HNzHR6WEhn";//MySQL配置时的密码
    public final int SUCCESS = 0;
    public final int FAILED = -1;

    public Connection getConnection() {
        try {
            // 加载驱动类
            Class.forName(driver);
            // 建立连接
            return DriverManager.getConnection(url,
                    user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object updateInfo(int ID, String info, String table, String col) {
        try {
            Connection connection = getConnection();
            String sql = "update " + table + " set " + col + " = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, info);
            preparedStatement.setInt(2, ID);
            preparedStatement.executeUpdate();
            connection.close();
            return SUCCESS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }
    }

    public Object updateInfo(int ID, int info, String table, String col) {
        try {
            Connection connection = getConnection();
            String sql = "update " + table + " set " + col + " = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, info);
            preparedStatement.setInt(2, ID);
            preparedStatement.executeUpdate();
            connection.close();
            return SUCCESS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }
    }
    public Object updatetest(int testID, int info, String table, String col) {
        try {
            Connection connection = getConnection();
            String sql = "update " + table + " set " + col + " = ? where testID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, info);
            preparedStatement.setInt(2, testID);
            preparedStatement.executeUpdate();
            connection.close();
            return SUCCESS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }
    }


}
