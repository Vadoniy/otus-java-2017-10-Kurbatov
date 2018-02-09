package DataBase;

import java.sql.SQLException;
import java.util.List;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void prepareTables() throws SQLException;

    <T extends DataSet> void save(T user) throws SQLException, IllegalAccessException, NoSuchFieldException;

    <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;

    <T extends DataSet> List<T> getAllUsers(Class<T> clazz) throws SQLException, NoSuchFieldException;

    void dropTables() throws SQLException;
}