package dao;

import domain.OVChipkaart;
import domain.Product;
import domain.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    public boolean save(Product product) throws SQLException;
    public boolean update(Product product) throws SQLException;
    public boolean delete(Product product) throws SQLException;
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException;
    public List<Product> findAll() throws SQLException;
}
