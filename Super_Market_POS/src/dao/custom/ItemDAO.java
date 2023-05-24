package dao.custom;

import dao.CrudDAO;
import entity.Item;
import view.tm.AdminDashBordItemTM;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {
    boolean addItemFromTable(AdminDashBordItemTM itemTM) throws SQLException, ClassNotFoundException;

    String getOrderItemName(String itemId) throws SQLException, ClassNotFoundException;

    boolean ifItemExist(String text) throws SQLException, ClassNotFoundException;
}
