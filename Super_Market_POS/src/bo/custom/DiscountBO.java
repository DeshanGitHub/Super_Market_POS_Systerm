package bo.custom;

import bo.SuperBO;

import java.sql.SQLException;

public interface DiscountBO extends SuperBO {
    double getDiscount() throws SQLException, ClassNotFoundException;

    boolean deleteDiscount(double value) throws SQLException, ClassNotFoundException;

    double getDiscountToValidation(double discount) throws SQLException, ClassNotFoundException;

    boolean saveDiscount(double discount) throws SQLException, ClassNotFoundException;
}
