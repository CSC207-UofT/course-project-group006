package BackEnd.Read;

import java.sql.*;
import java.util.ArrayList;

public abstract class Reader implements Readable {

    public final int SUCCESS = 0;
    public final int FAILED = -1;

    public final int INT = 100;
    public final int STRING = 101;


    public static Connection getConnection() {
        try {
            // 加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 建立连接
            return DriverManager.getConnection("jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5449780",
                    "sql5449780", "HNzHR6WEhn");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean hasDuplicateNames(String table, String name) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from " + table + " where name ='" + name + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            boolean hasMatch = resultSet.next();
            if (hasMatch) {
                statement.close();
                connection.close();
                return true;
            }
            connection.close();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return true;
        }

    }


    protected Object readInfo(String sql, int col, int type) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                connection.close();
                return FAILED;
            }
            if (type == INT) {
                int result = resultSet.getInt(col);
                connection.close();
                return result;
            } else {
                String result = resultSet.getString(col);
                connection.close();
                return result;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }

    }

    protected Object readAll(String sql, int col, int type) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                connection.close();
                return FAILED;
            }
            if (type == INT) {
                ArrayList<Integer> intList = new ArrayList<>();
                intList.add(resultSet.getInt(col));
                while (resultSet.next()) {
                    intList.add(resultSet.getInt(col));
                }
                connection.close();
                return intList;
            } else {
                ArrayList<String> stringList = new ArrayList<>();
                stringList.add(resultSet.getString(col));
                while (resultSet.next()) {
                    stringList.add(resultSet.getString(col));
                }
                connection.close();
                return stringList;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }

    }
    protected Object readaverage(String sql, int col, int type) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                connection.close();
                return FAILED;
            }
            if (type == INT) {
                ArrayList<Integer> intList = new ArrayList<>();
                intList.add(resultSet.getInt(col));
                int sum = resultSet.getInt(col);
                int time = 1;
                while (resultSet.next()) {
                    sum += resultSet.getInt(col);
                    time += 1;
                }
                connection.close();
                return sum*1.0/time;
            } else {
                connection.close();
                return FAILED;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }

    }
    protected Object readsum(String sql, int col, int type) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                connection.close();
                return FAILED;
            }
            if (type == INT) {
                ArrayList<Integer> intList = new ArrayList<>();
                intList.add(resultSet.getInt(col));
                int sum = resultSet.getInt(col);
                while (resultSet.next()) {
                    sum += resultSet.getInt(col);
                }
                connection.close();
                return sum;
            } else {
                connection.close();
                return FAILED;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return FAILED;
        }

    }
}

