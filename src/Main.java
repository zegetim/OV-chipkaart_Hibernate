import dao.*;
import domain.Adres;
import domain.OVChipkaart;
import domain.Product;
import domain.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
//        testFetchAll();
        AdresDaoHibernate adresDaoHibernate = new AdresDaoHibernate(factory);
        ReizigerDaoHibernate reizigerDaoHibernate = new ReizigerDaoHibernate(factory);
        OVChipkaartDaoHibernate ovChipkaartDaoHibernate = new OVChipkaartDaoHibernate(factory);
        ProductDaoHibernate productDaoHibernate = new ProductDaoHibernate(factory);
        testDAOHibernate(adresDaoHibernate,reizigerDaoHibernate,ovChipkaartDaoHibernate, productDaoHibernate);
    }

    private static void testDAOHibernate(AdresDao adresDao, ReizigerDao reizigerDao, OVChipkaartDao ovChipkaartDao, ProductDao productDao) throws SQLException {

        //update Ovchipkaart met Reiziger en Product
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(10000000, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        OVChipkaart ovChipkaart = new OVChipkaart(333, java.sql.Date.valueOf(gbdatum), 3, 35);
        Product product = new Product(333,"TESTTT","TESTEN",123);
        ovChipkaart.setReiziger(sietske);
        sietske.voegToeOVChipkaart(ovChipkaart);
        ovChipkaart.addProduct(product);
        ovChipkaartDao.update(ovChipkaart);
        System.out.println(sietske);

        //Update Reiziger met Adres
        Adres adres = new Adres(40000,"1111AA","1","dorpstraat","utrecht");
        Reiziger reiziger2 = new Reiziger(123,"T","","Zegel",java.sql.Date.valueOf(gbdatum));
        adres.setReiziger(reiziger2);
        ovChipkaart.setReiziger(reiziger2);
        reiziger2.setAdres(adres);
        reiziger2.voegToeOVChipkaart(ovChipkaart);
        reizigerDao.update(reiziger2);

        //Reiziger find
        Reiziger reiziger = reizigerDao.findById(1);
        System.out.println("Reiziger : " + reiziger);
        List<Reiziger> reizigers = reizigerDao.findByGbdatum(gbdatum);
        for(Reiziger reiziger1 : reizigers){
            System.out.println(reiziger1);
        }

        //update alles
        Reiziger reiziger1 = new Reiziger(400002,"T","","Zegeling",java.sql.Date.valueOf(gbdatum));
        Adres adres1 = new Adres(400002,"1111AA","1","dorpstraat","utrecht");
        OVChipkaart ovChipkaart4 = new OVChipkaart(403, java.sql.Date.valueOf(gbdatum), 3, 35);
        Product product1 = new Product(7654321,"prod","nslkhfk",135);
        ovChipkaart4.addProduct(product1);
        adres1.setReiziger(reiziger1);
        reiziger1.setAdres(adres1);
        reiziger1.voegToeOVChipkaart(ovChipkaart4);
        ovChipkaart4.setReiziger(reiziger1);
        reizigerDao.update(reiziger1);
        System.out.println("\nReiziger: " + reiziger1);
        System.out.println("\nAdres: " + adres1);
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }
}
