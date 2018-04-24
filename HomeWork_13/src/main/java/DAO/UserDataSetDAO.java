package DAO;

import DataSet.UserDataSet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDataSetDAO implements UserDAO {
    private Session session;

    public UserDataSetDAO(Session session){
        this.session = session;
    }

    @Override
    public void save(UserDataSet userDataSet) {
        session.save(userDataSet);
    }

    @Override
    public UserDataSet load(long id) {
        return session.load(UserDataSet.class, id);
    }

    @Override
    public List<UserDataSet> getAllUsers() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteriaQuery = builder.createQuery(UserDataSet.class);
        criteriaQuery.from(UserDataSet.class);
        return session.createQuery(criteriaQuery).list();
    }

    @Override
    public UserDataSet getByName(String name) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteriaQuery = criteriaBuilder.createQuery(UserDataSet.class);
        Root<UserDataSet> from = criteriaQuery.from(UserDataSet.class);
        criteriaQuery.where(criteriaBuilder.equal(from.get("name"), name));
        Query<UserDataSet> query = session.createQuery(criteriaQuery);
        return query.uniqueResult();
    }
}
