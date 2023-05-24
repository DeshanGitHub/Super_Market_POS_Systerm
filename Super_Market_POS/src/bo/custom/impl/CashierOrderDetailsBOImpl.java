package bo.custom.impl;

import bo.custom.CashierOrderDetailsBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import dto.OrderDTO;
import entity.OrderDataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class CashierOrderDetailsBOImpl implements CashierOrderDetailsBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDataBase> orderDataBases = orderDAO.getAll();
        ArrayList<OrderDTO> orderArrayList = new ArrayList<>();

        for (OrderDataBase orderDataBase : orderDataBases) {
            orderArrayList.add(new OrderDTO(
                    orderDataBase.getOrderId(), orderDataBase.getCustId(),
                    orderDataBase.getOrderDate().toString(), orderDataBase.getOrderTime(), orderDataBase.getOrderCost(), getOrderItemNames(orderDataBase.getOrderId())
            ));
        }
        return orderArrayList;
    }

    @Override
    public String getOrderItemNames(String orderId) throws SQLException, ClassNotFoundException {
        ArrayList<String> itemIds = orderDetailDAO.getOrderItemIds(orderId);
        ArrayList<String> itemCodesArrayList = new ArrayList<>();
        for (String itemId : itemIds) {
            itemCodesArrayList.add(getOrderItemName(itemId));
        }

        String namesString = itemCodesArrayList.toString();
        return namesString;
    }

    @Override
    public String getOrderItemName(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.getOrderItemName(itemId);
    }
}
