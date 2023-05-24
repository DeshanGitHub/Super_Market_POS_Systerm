package bo.custom;

import bo.SuperBO;
import dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CashierOrderDetailsBO extends SuperBO {
    ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException;

    String getOrderItemNames(String orderId) throws SQLException, ClassNotFoundException;

    String getOrderItemName(String itemId) throws SQLException, ClassNotFoundException;

}
