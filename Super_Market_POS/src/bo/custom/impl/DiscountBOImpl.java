package bo.custom.impl;

import bo.custom.DiscountBO;
import dao.DAOFactory;
import dao.custom.DiscountDAO;

import java.sql.SQLException;

public class DiscountBOImpl implements DiscountBO {
    private final DiscountDAO discountDAO = (DiscountDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DISCOUNT);

    @Override
    public double getDiscount() throws SQLException, ClassNotFoundException {
        return discountDAO.getDiscount();
    }

    @Override
    public boolean deleteDiscount(double value) throws SQLException, ClassNotFoundException {
        return discountDAO.deleteDiscount(value);
    }

    @Override
    public double getDiscountToValidation(double discount) throws SQLException, ClassNotFoundException {
        return discountDAO.searchDiscount(discount);
    }

    @Override
    public boolean saveDiscount(double discount) throws SQLException, ClassNotFoundException {
        return discountDAO.addDiscount(discount);
    }
}
