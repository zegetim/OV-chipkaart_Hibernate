package dao;

import domain.Adres;
import domain.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AdresDaoHibernate implements AdresDao{
    private SessionFactory sessionFactory;

    public AdresDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(adres);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Adres adres) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(adres);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(adres);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Adres adres = session.get(Adres.class, reiziger.getId());
            transaction.commit();
            return adres;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Adres> findAll() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            List<Adres> adressen = session.createQuery("FROM Adres", Adres.class).getResultList();
            transaction.commit();
            return adressen;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
