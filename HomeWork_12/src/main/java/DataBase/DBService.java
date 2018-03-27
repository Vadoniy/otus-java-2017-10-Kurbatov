package DataBase;

import DataSet.UserDataSet;

import java.util.List;

public interface DBService {
    String getLocalStatus();

    void save(UserDataSet user);

    UserDataSet load(long id);

    List<UserDataSet> getAllUsers();

    UserDataSet readByName(String name);

    void shutdown();
}
