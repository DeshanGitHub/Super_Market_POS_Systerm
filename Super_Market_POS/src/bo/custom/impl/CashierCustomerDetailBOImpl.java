package bo.custom.impl;

import bo.custom.CashierCustomerDetailBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CashierCustomerDetailBOImpl implements CashierCustomerDetailBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all
        ) {
            allCustomers.add(new CustomerDTO(c.getCustomerId(), c.getCustomerName(), c.getCustomerAddress(), c.getCustomerCity(), c.getCustomerProvince(), c.getCustomerPostalCode()));
        }
        return allCustomers;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerCity(), customer.getCustomerProvince(), customer.getCustomerPostalCode());

    }

    @Override
    public boolean saveCustomer(CustomerDTO c) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(c.getCustomerId(), c.getCustomerName(), c.getCustomerAddress(), c.getCustomerCity(), c.getCustomerProvince(), c.getCustomerPostalCode()));
    }

    @Override
    public boolean isCustomerExists(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.ifCustomerExists(id);
    }

}
