package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public ArrayList<String> getOrderItemIds(String orderId) throws SQLException, ClassNotFoundException {
       /* PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM OrderDetail WHERE orderId='" + orderId + "'");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<String> itemIds=new ArrayList<>();
        while (resultSet.next()){
            itemIds.add(resultSet.getString("itemCode"));
        }
        return itemIds;*/
        ArrayList<String> itemIds=new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM orderdetail WHERE orderId=?", orderId);
        while (rst.next()){
            itemIds.add(rst.getString("itemCode"));
        }
        return itemIds;
    }

    @Override
    public boolean add(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        /*PreparedStatement stm  = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO OrderDetail VALUES (?,?,?,?,?)");
        stm.setObject(1, orderDetail.getOrderId());
        stm.setObject(2, orderDetail.getItemCode());
        stm.setObject(3, orderDetail.getOrderQty());
        stm.setObject(4, orderDetail.getDiscount());
        stm.setObject(5, orderDetail.getPrice());

        return stm.executeUpdate()>0;*/
        return CrudUtil.executeUpdate("INSERT INTO orderdetail (orderId, itemCode, orderQTY, discount, price) VALUES (?,?,?,?,?)",
                orderDetail.getOrderId(),orderDetail.getItemCode(),orderDetail.getOrderQty(),orderDetail.getDiscount(),orderDetail.getPrice());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
