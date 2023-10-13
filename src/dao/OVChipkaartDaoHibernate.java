package dao;

import domain.OVChipkaart;
import domain.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OVChipkaartDaoHibernate implements OVChipkaartDao {
    private SessionFactory sessionFactory;

    public OVChipkaartDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(ovChipkaart);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(ovChipkaart);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(ovChipkaart);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            List<OVChipkaart> ovchipkaarten = session.createQuery("FROM OVChipkaart WHERE reiziger = :reiziger").setParameter("reiziger", reiziger).getResultList();
            transaction.commit();
            return ovchipkaarten;
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
