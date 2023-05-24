package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import db.DbConnection;
import entity.Item;
import view.tm.AdminDashBordItemTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(Item itemTM) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE itemCode='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
       /* PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE item SET itemName=?, itemDescription=?, itemPackSize=?, qtyOnHand=?, buyingPrice=?, sellingPrice=? WHERE itemCode=?"
        );
        stm.setObject(1,item.getItemName());
        stm.setObject(2,item.getItemDescription());
        stm.setObject(3,item.getItemPackSize());
        stm.setObject(4,item.getQtyOnHand());
        stm.setObject(5,item.getBuyingPrice());
        stm.setObject(6,item.getSellingPrice());
        stm.setObject(7,item.getItemCode());

        return stm.executeUpdate()>0;*/

        return CrudUtil.executeUpdate("UPDATE item SET itemName=?, itemDescription=?, itemPackSize=?, qtyOnHand=?, buyingPrice=?, sellingPrice=? WHERE itemCode=?",
                item.getItemName(), item.getItemDescription(), item.getItemPackSize(), item.getQtyOnHand(), item.getBuyingPrice(), item.getSellingPrice(), item.getItemCode());
    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        /*PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE itemCode = ?");
        statement.setObject(1,id);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return new Item(
                    resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getDouble(6),resultSet.getDouble(7)
            );
        }else {
            return null;
        }*/
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE itemCode=?", id);
        if (rst.next()) {
            return new Item(rst.getString("itemCode"), rst.getString("itemName"), rst.getString("itemDescription"), rst.getString("itemPackSize"), rst.getInt("qtyOnHand"), rst.getDouble("buyingPrice"), rst.getDouble("sellingPrice"));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        /*PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Item> itemArrayList=new ArrayList<>();

        while (resultSet.next()){
            itemArrayList.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7)
            ));
        }
        return itemArrayList;*/
        ArrayList<Item> allItems = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM item");
        while (rst.next()) {
            allItems.add(new Item(rst.getString("itemCode"), rst.getString("itemName"), rst.getString("itemDescription"), rst.getString("itemPackSize"), rst.getInt("qtyOnHand"), rst.getDouble("buyingPrice"), rst.getDouble("sellingPrice")));
        }
        return allItems;
    }

    @Override
    public boolean addItemFromTable(AdminDashBordItemTM itemTM) throws SQLException, ClassNotFoundException {
        /*PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Item VALUES (?,?,?,?,?,?,?)");
        statement.setObject(1,itemTM.getItemCode());
        statement.setObject(2,itemTM.getItemName());
        statement.setObject(3,itemTM.getItemDescription());
        statement.setObject(4,itemTM.getItemPackSize());
        statement.setObject(5,itemTM.getQtyOnHand());
        statement.setObject(6,itemTM.getBuyingPrice());
        statement.setObject(7,itemTM.getSellingPrice());
        return statement.executeUpdate()>0;*/
        return CrudUtil.executeUpdate("INSERT INTO Item (itemCode,itemName,itemDescription,itemPackSize,qtyOnHand,buyingPrice,sellingPrice) VALUES (?,?,?,?,?,?,?)",
                itemTM.getItemCode(), itemTM.getItemName(), itemTM.getItemDescription(), itemTM.getItemPackSize(), itemTM.getQtyOnHand(), itemTM.getBuyingPrice(), itemTM.getSellingPrice());
    }

    @Override
    public String getOrderItemName(String itemId) throws SQLException, ClassNotFoundException {
        /*PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE itemCode='" + itemId + "'");
        ResultSet resultSet = statement.executeQuery();
        // ArrayList<OrderItemName> orderItemNames=new ArrayList<>();
        if (resultSet.next()) {
            return resultSet.getString(2);
        } else {
            return "Unknown Item";
        }*/
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE itemCode=?", itemId);
        if (rst.next()) {
            return rst.getString("itemName");
        } else {
            return "Unknown Item";
        }
    }

    @Override
    public boolean ifItemExist(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT itemCode FROM item WHERE itemCode=?", text).next();
    }
}
