package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import view.tm.AdminDashBordItemTM;

import java.sql.SQLException;

public interface AdminDashBoardBO extends SuperBO {
    ItemDTO getItem(String id) throws SQLException, ClassNotFoundException;

    boolean saveItemFromTable(AdminDashBordItemTM itemTM) throws SQLException, ClassNotFoundException;

    boolean ifItemExists(String text) throws SQLException, ClassNotFoundException;
}
