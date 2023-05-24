package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CashierCustomerDetailBO extends SuperBO {
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO c) throws SQLException, ClassNotFoundException;

    boolean isCustomerExists(String id) throws SQLException, ClassNotFoundException;
}
