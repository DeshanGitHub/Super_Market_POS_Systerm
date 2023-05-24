package bo.custom.impl;

import bo.custom.CashierPlaceOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import db.DbConnection;
import dto.CustomerDTO;
import dto.OrderDTO;
import entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CashierPlaceOrderBOImpl implements CashierPlaceOrderBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final DiscountDAO discountDAO= (DiscountDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DISCOUNT);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);


    @Override
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerCity(), customer.getCustomerProvince(), customer.getCustomerPostalCode());

    }

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
    public double getDiscount() throws SQLException, ClassNotFoundException {
        return discountDAO.getDiscount();
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderId();
    }

    @Override
    public boolean purchaseOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;

        connection = DbConnection.getInstance().getConnection();
        boolean orderAvailable = orderDAO.ifOrderExist(orderDTO.getOrderId());
        /*if order id already exist*/
        if (orderAvailable) {
            return false;
        }

        connection.setAutoCommit(false);
        /*Add Order*/
        OrderDataBase orderDataBase = new OrderDataBase(orderDTO.getOrderId(), orderDTO.getCustomerId(), LocalDate.parse(orderDTO.getOrderDate()), orderDTO.getOrderTime(), orderDTO.getCost());
        boolean orderAdded = orderDAO.add(orderDataBase);
        if (!orderAdded) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        //ArrayList<OrderDetail> orderDetailArrayList=new ArrayList<>();
        for (ItemDetails itemDetails : orderDTO.getItemDetailsArrayList()
        ) {
            OrderDetail orderDetail = new OrderDetail(orderDTO.getOrderId(), itemDetails.getItemCode(), itemDetails.getQtyForSale(), itemDetails.getDiscount(), itemDetails.getUnitPrice());

            /*add Order Details to Database*/
            boolean orderDetailsAdded = orderDetailDAO.add(orderDetail);
            if (!orderDetailsAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            //Search & Update Item
            Item search = itemDAO.search(itemDetails.getItemCode());
            search.setQtyOnHand(search.getQtyOnHand() - orderDetail.getOrderQty());
            boolean update = itemDAO.update(search);
            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        //if every thing ok
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }
}
