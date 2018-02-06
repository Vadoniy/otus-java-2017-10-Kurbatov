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
    private Statement statement;

    public Executor(Connection connection) {
        this.connection = connection;
        try {
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Can't create statement.");
        }
    }

    public <T extends DataSet> void save(T dataSet){
        try {
            String query = generateQueryUpdate(dataSet);
            statement.execute(query);
        } catch (SQLException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) throws NoSuchFieldException {
        T dataSet = null;
        try {
            dataSet = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        String name = "";
        int age = 0;

        try {
            ResultSet resultSet = statement.executeQuery(generateQuerySelect(id));

            while (resultSet.next()){
                name = resultSet.getString("name");
                age = resultSet.getInt("age");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Field> fieldList = new ArrayList<>();
        fieldList.add(clazz.getSuperclass().getDeclaredField("id"));

        for (Field field : dataSet.getClass().getDeclaredFields()){
            fieldList.add(field);
        }

        try{
            for (Field field : fieldList){
                field.setAccessible(true);
                switch (field.getName()){
                    case "id":
                        field.set(dataSet, id);
                        continue;
                    case "name":
                        field.set(dataSet, name);
                        continue;
                    case "age":
                        field.set(dataSet, age);
                        continue;
                }
            }
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }

        return dataSet;
    }

    public void execQuery (String query) throws SQLException {
        try (Statement stmt = connection.createStatement()){
            stmt.execute(query);
        }
    }

    public <T> T execQuery (String query, ResultHandler<T> resultHandler) throws SQLException {
        try (Statement stmt = connection.createStatement()){
            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();
            return resultHandler.handle(resultSet);
        }
    }

    private String generateQueryUpdate(DataSet dataSet) throws IllegalAccessException {
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

    private String generateQuerySelect(long id){
        return String.format(SELECT_USER_ID, id);
    }
}