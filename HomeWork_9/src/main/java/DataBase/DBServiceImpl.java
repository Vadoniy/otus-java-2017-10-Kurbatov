package DataBase;

import Executor.Executor;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBServiceImpl implements DBService {

    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS user (ID BIGINT AUTO_INCREMENT, name VARCHAR(256), age INT(3), PRIMARY KEY (id))";
    private static final String SELECT_ALL_FROM_USER = "SELECT * FROM user";
    private static final String DROP_TABLE = "DROP TABLE user";

    private final Connection connection;

    private String metaData = "Connected to: %s\n" +
            "DB name: %s\n" +
            "DB version: %s\n" +
            "Driver: %s";

    public DBServiceImpl() {
        this.connection = ConnectionHelper.getConnection();
    }

    @Override
    public String getMetaData() {
        try {
            DatabaseMetaData dmd = getConnection().getMetaData();
            return String.format(metaData, dmd.getURL(),
            dmd.getDatabaseProductName(),
            dmd.getDatabaseProductVersion(),
            dmd.getDriverName());
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getSQLState() + "\n" + e.getMessage();
        }
    }

    @Override
    public void prepareTables() throws SQLException {
        Executor executor = new Executor(connection);
        executor.execQuery(CREATE_TABLE_USER);
        System.out.println("Table USER was created.");
    }

    @Override
    public List<UserDataSet> getAllUsers() throws SQLException {
        Executor executor = new Executor(getConnection());
        return executor.execQuery(SELECT_ALL_FROM_USER, result -> {
            List<UserDataSet> userDataSetList = new ArrayList<>();

            while (result.next()) {
                userDataSetList.add(new UserDataSet(result.getInt("id"),
                                                    result.getString("name"),
                                                    result.getInt("age")));
            }
            return userDataSetList;
        });
    }

    @Override
    public void dropTables() throws SQLException {
        Executor executor = new Executor(connection);
        executor.execQuery(DROP_TABLE);
        System.out.println("Table USER was dropped.");
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection was closed successfully.");
    }

    public Connection getConnection() {
        return connection;
    }
}
