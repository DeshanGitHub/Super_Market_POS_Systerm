package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.DiscountDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountDAOImpl implements DiscountDAO {

    @Override
    public double getDiscount() throws SQLException, ClassNotFoundException {
        /*PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Discount");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return resultSet.getDouble(1);
        }else {
            return 0;
        }*/
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Discount");
        if(resultSet.next()){
            return resultSet.getDouble(1);
        }else {
            return 0;
        }
    }

    @Override
    public boolean deleteDiscount(double value) throws SQLException, ClassNotFoundException {
       /* PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Discount WHERE discount='" + value + "'");
        if (statement.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }*/
        return CrudUtil.executeUpdate("DELETE FROM Discount WHERE discount=?", value);
    }

    @Override
    public double searchDiscount(double discount) throws SQLException, ClassNotFoundException {
        /*PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Discount WHERE discount='" + discount + "'");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getDouble(1);
        } else {
            return 0;
        }*/
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Discount WHERE discount=?", discount);
        if(rst.next()){
            return rst.getDouble("discount");
        }else {
            return 0;
        }

    }

    @Override
    public boolean addDiscount(double discount) throws SQLException, ClassNotFoundException {
       /* PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Discount VALUES (?)");
        statement.setObject(1, discount);

        return statement.executeUpdate() > 0;*/
        return CrudUtil.executeUpdate("INSERT INTO Discount (discount) VALUES (?)", discount);

    }
}
