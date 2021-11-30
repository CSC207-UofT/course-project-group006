package BackEnd.Gateways;

import BackEnd.ReadWriter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A parent Gateway class that accesses the remote data.
 */
public abstract class Gateway implements ReadWriter {
    /**
     * Constants
     */
    protected final String FAILED = "FAILED";
    protected final int INT = 111;
    protected final int STRING = 222;

    @Override
    public abstract List<String> read();

    @Override
    public abstract List<String> write();

    /**
     * Gets connection of the database.
     *
     * @return the connection
     */
    public Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            String user = "sql5454663";
            String password = "SinqmMLSgB";
            String url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5454663";
            return DriverManager.getConnection(url,
                    user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Read one element in the database.
     *
     * @param sql  the sql wanted to be executed
     * @param col  the col number of the wanted element
     * @param type the type of the element: 111 for int, 222 for string
     * @return the element as a string
     */
    public String readOne(String sql, int col, int type) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                statement.close();
                connection.close();
                return FAILED;
            }
            String result;
            if (type == INT) {
                result = resultSet.getInt(col) + "";
            } else {
                result = resultSet.getString(col);
            }
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }


    /**
     * Read all elements in a table that satisfy the requirement
     *
     * @param sql  the sql wanted to be executed
     * @param col  the col number of the wanted element(s)
     * @param type the type of the element(s): 111 for int, 222 for string
     * @return the list
     */
    protected List<String> readAll(String sql, int col, int type) {
        try {
            ArrayList<String> result = new ArrayList<>();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                statement.close();
                connection.close();
                return result;
            }
            if (type == INT) {
                result.add(resultSet.getInt(col) + "");
                while (resultSet.next()) {
                    result.add(resultSet.getInt(col) + "");
                }
            } else {
                result.add(resultSet.getString(col));
                while (resultSet.next()) {
                    result.add(resultSet.getString(col));
                }
            }
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }


}
