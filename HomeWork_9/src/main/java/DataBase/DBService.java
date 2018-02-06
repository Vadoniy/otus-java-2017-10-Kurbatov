package DataBase;

import java.sql.SQLException;
import java.util.List;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void prepareTables() throws SQLException;

    List<UserDataSet> getAllUsers() throws SQLException;

    void dropTables() throws SQLException;
}
