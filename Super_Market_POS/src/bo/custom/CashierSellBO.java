package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CashierSellBO extends SuperBO {
    ItemDTO getItem(String id) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    String getOrderId() throws SQLException, ClassNotFoundException;

}
