package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.*;


/* Suprascrierea metodele CRUD pentru a stoca informatiile  in baza de date
       si am folosit o instanta a acesteia in pachetul "SERVICE"

 */
public class GenericDao<T> implements Dao {

    private SessionFactory sessionFactory;


    public GenericDao() {
        DbConnection dbConnection = DbConnection.getInstance();
        sessionFactory = dbConnection.getSessionFactory();
    }

    @Override
    public void add(Object object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        if (session != null) {
            session.close();
        }

    }

    @Override
    public List<T> getFromDb(Object object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + object.getClass().getName());
        List<T> results = query.getResultList();
        transaction.commit();
        if (session != null) {
            session.close();
        }
        return results;
    }


    @Override
    public void deleteFromDb(Object object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        if (session != null) {
            session.close();
        }

    }


    @Override
    public void updateDB(Object object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        if (session != null) {
            session.close();
        }

    }


}
