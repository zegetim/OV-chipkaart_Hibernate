package dao;

import domain.OVChipkaart;
import domain.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDaoHibernate implements ProductDao{
    private SessionFactory sessionFactory;

    public ProductDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(Product product) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
            return true;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            List<Product> producten = session.createQuery("SELECT p FROM Product p JOIN p.ovchipkaarten ov WHERE ov.kaart_nummer = :kaart").setParameter("kaart", ovChipkaart.getKaartnummer()).getResultList();
            transaction.commit();
            return producten;
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            List<Product> producten = session.createQuery("FROM Product ", Product.class).getResultList();
            transaction.commit();
            return producten;
        } catch(HibernateException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
