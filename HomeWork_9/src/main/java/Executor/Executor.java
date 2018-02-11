package Executor;

import DataBase.DataSet;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Executor {

    private static StringBuilder INSERT_INTO_USER = new StringBuilder("INSERT INTO user (");
    private static StringBuilder INSERT_VALUES = new StringBuilder(") VALUES (");
    private static final String SELECT_USER_ID = "SELECT * FROM user WHERE id = %s";
    private static final String COMMA = ", ";

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execQuery (String query) throws SQLException {
        try (Statement stmt = connection.createStatement()){
            stmt.execute(query);
        }
    }

    public <T> T execQuery (String query, ResultHandler<T> resultHandler) throws SQLException, NoSuchFieldException {
        try (Statement stmt = connection.createStatement()){
            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();
            return resultHandler.handle(resultSet);
        }
    }


    public String generateQueryUpdate(DataSet dataSet) throws IllegalAccessException {

        List<Field> fields = new ArrayList<>(Arrays.asList(dataSet.getClass().getDeclaredFields()));

        for (Field field : fields){
            field.setAccessible(true);
            INSERT_INTO_USER.append(field.getName());
            INSERT_INTO_USER.append(COMMA);
            INSERT_VALUES.append('\'');
            INSERT_VALUES.append(field.get(dataSet));
            INSERT_VALUES.append('\'');
            INSERT_VALUES.append(COMMA);
            }

        INSERT_INTO_USER.deleteCharAt(INSERT_INTO_USER.lastIndexOf(COMMA));
        INSERT_VALUES.deleteCharAt(INSERT_VALUES.lastIndexOf(COMMA));
        INSERT_INTO_USER.append(INSERT_VALUES);
        INSERT_INTO_USER.append(")");
        String query = INSERT_INTO_USER.toString();
        INSERT_INTO_USER = new StringBuilder("INSERT INTO user (");
        INSERT_VALUES = new StringBuilder(") VALUES (");

        return query;
    }

    public String generateQuerySelect(long id){
        return String.format(SELECT_USER_ID, id);
    }
}