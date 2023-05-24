package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CashierPlaceOrderBO extends SuperBO {
    CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    double getDiscount() throws SQLException, ClassNotFoundException;

    String getOrderId() throws SQLException, ClassNotFoundException;

    boolean purchaseOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

}
