package BackEnd.Gateways;

import BackEnd.Interfaces.GeneralReadWriter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A parent Gateway class that accesses the remote data.
 */
public abstract class Gateway implements GeneralReadWriter {
    /**
     * Constants
     */
    protected final String FAILED = "FAILED";
    protected final String SUCCESS = "SUCCESS";
    protected final int INT = 111;
    protected final int STRING = 222;
    protected final int ID = 1;
    protected final int NAME = 2;
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String user = "sql5454663";
    public static String password = "SinqmMLSgB";
    public static String url = "jdbc:MySQL://sql5.freemysqlhosting.net:3306/sql5454663";
    /**
     * Gets connection of the database.
     *
     * @return the connection
     */
    public Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,
                    user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Read all elements in a table that satisfy the requirement
     *
     * @param sql  the sql wanted to be executed
     * @param col  the col number of the wanted element(s)
     * @param type the type of the element(s): 111 for int, 222 for string
     * @return a list or an empty list
     */
    protected List<String> read(String sql, int col, int type) {
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
                String raw = resultSet.getString(col);
                result.add(raw.startsWith(",") ? raw.substring(1) : raw);
                while (resultSet.next()) {
                    raw = resultSet.getString(col);
                    result.add(raw.startsWith(",") ? raw.substring(1) : raw);
                }
            }
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Rewrite a field in the database.
     *
     * @param sql the sql wanted to be executed
     * @return SUCCESS or FAILED
     */
    protected String rewrite(String sql) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
    }

    /**
     * Create a row and gets its id string.
     *
     * @param preparedStatement the prepared statement
     * @return the string of id
     * @throws SQLException the sql exception
     */
    protected String createGetID(PreparedStatement preparedStatement) throws SQLException {
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected == 0) {
            return FAILED;
        }
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int newID = generatedKeys.getInt(ID);
            return newID + "";
        } else {
            return FAILED;
        }
    }

    /**
     * Identify whether the name has already exist
     * @param table the table
     * @param name  the name
     * @return True if name exists, false if the name is new
     */
    public boolean hasDuplicateNames(String table, String name) {
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


}
