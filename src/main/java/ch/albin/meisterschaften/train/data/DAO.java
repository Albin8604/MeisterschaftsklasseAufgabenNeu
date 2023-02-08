package ch.albin.meisterschaften.train.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DAO<T> {
    private final Class<T> tClass;
    private SessionFactory sessionFactory;

    public DAO(Class<T> tClass) {
        this.tClass = tClass;
        sessionFactory = MySqlDB.getInstance().getSessionFactory();
    }


    public T selectOne(Serializable s) {
        T result;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            result = session.get(tClass, s);
            transaction.commit();
        }
        return result;
    }


    public List<T> selectAll() {
        List<T> result;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            result = session.createQuery("from " + tClass.getName(), tClass).list();
            transaction.commit();
        }
        return result;
    }


    public void deleteOne(T object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(object);
            transaction.commit();
        }
    }


    public void deleteOne(Serializable s) {
        T object = selectOne(s);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(object);
            transaction.commit();
        }
    }


    public void update(T object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(object);
            transaction.commit();
        }
    }


    public void addOne(T object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(object);
            transaction.commit();
        }
    }


    public void addAll(List<T> objects) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            objects.forEach(session::persist);
            transaction.commit();
        }
    }


    public List<T> namedQueries(String nameOfQuery, Map<String, Object> params) {
        List<T> result;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<T> query = session.createNamedQuery(nameOfQuery, tClass);

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            result = query.getResultList();

            transaction.commit();
        }
        return result;
    }


    public DAO<T> setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        return this;
    }
}
