package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import entity.OrderDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean add(OrderDataBase orderDataBase) throws SQLException, ClassNotFoundException {
        /*PreparedStatement stm  = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `Order` VALUES (?,?,?,?,?)");
        stm.setObject(1, orderDataBase.getOrderId());
        stm.setObject(2, orderDataBase.getCustId());
        stm.setObject(3, orderDataBase.getOrderDate());
        stm.setObject(4, orderDataBase.getOrderTime());
        stm.setObject(5, orderDataBase.getOrderCost());

        return stm.executeUpdate()>0;*/
        return CrudUtil.executeUpdate("INSERT INTO `Order` (orderId, custId, orderDate, orderTime, orderCost) VALUES (?,?,?,?,?)",
                orderDataBase.getOrderId(),orderDataBase.getCustId(),orderDataBase.getOrderDate(),orderDataBase.getOrderTime(),orderDataBase.getOrderCost());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDataBase orderDataBase) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDataBase search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDataBase> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDataBase> orderDataBaseArrayList=new ArrayList<>();
       /* PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Order`");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            orderDataBaseArrayList.add(new OrderDataBase(resultSet.getString("orderId"),resultSet.getString("custId"), LocalDate.parse(resultSet.getString("orderDate")),resultSet.getString("orderTime"),resultSet.getDouble("orderCost")));
        }
        return orderDataBaseArrayList;*/
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order`");
        while (rst.next()){
            orderDataBaseArrayList.add(new OrderDataBase(rst.getString("orderId"),rst.getString("custId"),LocalDate.parse(rst.getString("orderDate")) ,rst.getString("orderTime"),rst.getDouble("orderCost")));
        }
        return orderDataBaseArrayList;
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        /*ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1").executeQuery();*/
        ResultSet resultSet = CrudUtil.executeQuery("SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1");

        if (resultSet.next()) {

            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId = tempId + 1;

            if (tempId < 10) {
                return "O-00" + tempId;
            } else if (tempId < 100) {
                return "O-0" + tempId;
            } else {
                return "O-" + tempId;
            }

        } else {
            return "O-001";
        }
    }

    @Override
    public boolean ifOrderExist(String orderId) throws SQLException, ClassNotFoundException {
        /*PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT orderId FROM `order` WHERE orderId=?");
        stm.setObject(1,orderId);
        ResultSet resultSet = stm.executeQuery();*/

         ResultSet rst = CrudUtil.executeQuery("SELECT orderId FROM `order` WHERE orderId=?", orderId);
        return rst.next();
    }
}
