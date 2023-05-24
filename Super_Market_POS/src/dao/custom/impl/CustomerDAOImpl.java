package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer c) throws SQLException, ClassNotFoundException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Customer VALUES (?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, c.getCustomerId());
        statement.setObject(2, c.getCustomerName());
        statement.setObject(3, c.getCustomerAddress());
        statement.setObject(4, c.getCustomerCity());
        statement.setObject(5, c.getCustomerProvince());
        statement.setObject(6, c.getCustomerPostalCode());
        return statement.executeUpdate()>0;*/

        return CrudUtil.executeUpdate("INSERT INTO customer (custId,custName,custAddress,city,province,postalCode) VALUES (?,?,?,?,?,?)",
                c.getCustomerId(), c.getCustomerName(), c.getCustomerAddress(), c.getCustomerCity(), c.getCustomerProvince(), c.getCustomerPostalCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE CustId='" + id + "'");
        if (statement.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }*/

        return CrudUtil.executeUpdate("DELETE FROM customer WHERE custId=?", id);
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        /*PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE custId='" + id + "'");
        ResultSet resultSet = stm.executeQuery();

        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        } else {
            return null;
        }*/
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM customer WHERE custId=?", id);
        if (rst.next()) {
            return new Customer(rst.getString("custId"), rst.getString("custName"), rst.getString("custAddress"), rst.getString("city"), rst.getString("province"), rst.getString("postalCode"));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        /*PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = statement.executeQuery();
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return customers;*/
        ArrayList<Customer> allCustomers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM customer");
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString("custId"), rst.getString("custName"), rst.getString("custAddress"), rst.getString("city"), rst.getString("province"), rst.getString("postalCode")));
        }
        return allCustomers;
    }

    @Override
    public boolean ifCustomerExists(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT custId FROM customer WHERE custId=?", id).next();
    }
}
