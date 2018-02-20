package DAO;

import DataSet.UserDataSet;

import java.util.List;

public interface UserDAO {
    void save(UserDataSet userDataSet);

    UserDataSet load(long id);

    List<UserDataSet> getAllUsers();

    UserDataSet getByName(String name);
}
