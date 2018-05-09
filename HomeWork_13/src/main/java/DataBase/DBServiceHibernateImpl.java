package DataBase;

import Cache.CacheEngine;
import Cache.Element;
import DAO.UserDataSetDAO;
import DataSet.DataSet;
import DataSet.UserDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Created by Vadoniy on 09.02.2018.
 */
public class DBServiceHibernateImpl implements DBService {

    private final SessionFactory sessionFactory;
    private final CacheEngine<Long, DataSet> cacheEngine;

    public DBServiceHibernateImpl(CacheEngine<Long, DataSet> cacheEngine) {
        this.cacheEngine = cacheEngine;
        this.sessionFactory = ConnectionHelper.getSessionFactory();
    }

    @Override
    public String getLocalStatus() {
        return runInSession(session -> {
            return session.getTransaction().getStatus().name();
        });
    }

    @Override
    public void shutdown() {
        sessionFactory.close();
        System.out.println("Session is closed.");
    }

    @Override
    public List<UserDataSet> getAllUsers() {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.getAllUsers();
        });
    }

    @Override
    public UserDataSet readByName(String name) {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.getByName(name);
        });
    }

    @Override
    public UserDataSet load(long id) {
        UserDataSet userDataSet;
        if (Objects.isNull(cacheEngine)){
            userDataSet = getUserDataSetById(id);
            System.out.println(String.format("Data from DB. Written to cache with id = %s", id));
            cacheEngine.put(new Element(id, userDataSet));
            return userDataSet;
        } else {
            userDataSet = (UserDataSet)cacheEngine.get(id);

            if (Objects.isNull(userDataSet)){
                userDataSet = getUserDataSetById(id);
                System.out.println(String.format("Cache was empty, data received from DB. Cache data was updated with id %s", id));
                cacheEngine.put(new Element(id, userDataSet));
                return userDataSet;
            } else {
                System.out.println(String.format("Data from cache, id = %s", id));
                return userDataSet;
            }
        }
    }

    @Override
    public void save(UserDataSet user) {
        try (Session session = sessionFactory.openSession()) {
            UserDataSetDAO userDataSetDAO = new UserDataSetDAO(session);
            userDataSetDAO.save(user);
            session.close();
        }

        if (Objects.isNull(cacheEngine)){
            cacheEngine.put(new Element(user.getId(), user));
            System.out.println("DataSet was added to cache.");
        }
    }

    public CacheEngine getCache(){
        return cacheEngine;
    }

    private UserDataSet getUserDataSetById(long id) {
        UserDataSet userDataSet;
        userDataSet = runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.load(id);
        });
        return userDataSet;
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }
}
