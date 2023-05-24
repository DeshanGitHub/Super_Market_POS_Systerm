package dao.custom;

import dao.CrudDAO;
import entity.OrderDataBase;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<OrderDataBase, String> {
    String getOrderId() throws SQLException, ClassNotFoundException;

    boolean ifOrderExist(String orderId) throws SQLException, ClassNotFoundException;

}
