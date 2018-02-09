package DataBase;

import Executor.Executor;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBServiceImpl implements DBService {

    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS user (ID BIGINT AUTO_INCREMENT, name VARCHAR(256), age INT(3), PRIMARY KEY (id))";

    private static final String SELECT_ALL_FROM_USER = "SELECT * FROM user";

    private static final String DROP_TABLE = "DROP TABLE user";

    private final Connection connection;
    private final Statement statement;

    private String metaData = "Connected to: %s\n" +
            "DB name: %s\n" +
            "DB version: %s\n" +
            "Driver: %s";

    public DBServiceImpl() throws SQLException {
        this.connection = ConnectionHelper.getConnection();
        this.statement = connection.createStatement();
    }

    @Override
    public String getMetaData() {
        try {
            DatabaseMetaData dmd = connection.getMetaData();
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

    @Override
    public <T extends DataSet> void save(T dataSet){
        try {
            String query = new Executor(connection).generateQueryUpdate(dataSet);
            statement.execute(query);
        } catch (SQLException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) throws NoSuchFieldException {
        T dataSet = null;
        try {
            dataSet = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Executor executor = new Executor(connection);
            List<Field> fieldList = new ArrayList<>();
            fieldList.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            ResultSet resultSet = statement.executeQuery(executor.generateQuerySelect(id));
            resultSet.next();
            for (Field field : fieldList){
                field.setAccessible(true);
                field.set(dataSet, resultSet.getObject(field.getName()));
            }

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return dataSet;
    }

    @Override
    public <T extends DataSet> List<T> getAllUsers(Class<T> clazz) throws SQLException, NoSuchFieldException {

        Executor executor = new Executor(connection);
        return executor.execQuery(SELECT_ALL_FROM_USER, result -> {
            List<T> dataSetList = new ArrayList<>();

            int i = 1;
            while (result.next()) {
                dataSetList.add(load(i, clazz));
                i++;
            }
            return dataSetList;
        });
    }
}
