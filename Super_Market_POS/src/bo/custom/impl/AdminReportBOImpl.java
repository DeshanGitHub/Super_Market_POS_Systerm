package bo.custom.impl;

import bo.custom.AdminReportBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dao.custom.QueryDAO;
import dto.CustomDTO;
import dto.OrderDTO;
import entity.OrderDataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminReportBOImpl implements AdminReportBO {
    OrderDAO orderDAO= (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> allOrders = new ArrayList<>();
        ArrayList<OrderDataBase> all = orderDAO.getAll();
        for (OrderDataBase order : all) {
            allOrders.add(new OrderDTO(order.getOrderId(),order.getCustId(),order.getOrderDate().toString(),order.getOrderTime(),order.getOrderCost()));
        }
        return allOrders;
    }

    @Override
    public ArrayList<CustomDTO> getOrderDetailsForNetIncome() throws SQLException, ClassNotFoundException {
        return queryDAO.orderAndItemDetails();
    }
}
