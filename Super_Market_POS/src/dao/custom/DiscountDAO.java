package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;

import java.sql.SQLException;

public interface DiscountDAO extends SuperDAO {
    double getDiscount() throws SQLException, ClassNotFoundException;

    boolean deleteDiscount(double value) throws SQLException, ClassNotFoundException;

    double searchDiscount(double discount) throws SQLException, ClassNotFoundException;

    boolean addDiscount(double discount) throws SQLException, ClassNotFoundException;
}
