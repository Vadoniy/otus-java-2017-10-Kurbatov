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

    private static final String INSERT_INTO_USER = "INSERT INTO user (name, age) VALUES ('%s', '%s')";
    private static final String SELECT_USER_ID = "SELECT * FROM user WHERE id = %s";

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
        String name = "";
        int age = 0;
        for (Field field : fields){
            field.setAccessible(true);
            switch (field.getName()){
                case "name":
                    name = (String)field.get(dataSet);
                    continue;
                case "age":
                    age = field.getInt(dataSet);
                    continue;
            }
        }
        return String.format(INSERT_INTO_USER, name, age);
    }

    public String generateQuerySelect(long id){
        return String.format(SELECT_USER_ID, id);
    }
}