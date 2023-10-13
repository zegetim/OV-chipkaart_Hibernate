package dao;


import domain.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ReizigerDaoHibernate implements ReizigerDao{
    private SessionFactory sessionFactory;

    public ReizigerDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(reiziger);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(reiziger);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(reiziger);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;


    }

    @Override
    public List<Reiziger> findAll() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            List<Reiziger> reizigers = session.createQuery("FROM Reiziger", Reiziger.class).getResultList();
            transaction.commit();
            return reizigers;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            List<Reiziger> reizigers = session.createQuery("FROM Reiziger WHERE geboortedatum = :datum").setParameter("datum", java.sql.Date.valueOf(datum)).getResultList();
            transaction.commit();
            return reizigers;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return null;

    }

    @Override
    public Reiziger findById(int id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Reiziger reiziger = session.get(Reiziger.class, id);
            transaction.commit();
            return reiziger;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
