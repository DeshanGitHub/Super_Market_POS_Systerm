package bo.custom.impl;

import bo.custom.AdminDashBoardBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;
import view.tm.AdminDashBordItemTM;

import java.sql.SQLException;

public class AdminDashBoardBOImpl implements AdminDashBoardBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ItemDTO getItem(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(id);
        return new ItemDTO(item.getItemCode(),item.getItemName(),item.getItemDescription(),item.getItemPackSize(),item.getQtyOnHand(),item.getBuyingPrice(),item.getSellingPrice());
    }

    @Override
    public boolean saveItemFromTable(AdminDashBordItemTM itemTM) throws SQLException, ClassNotFoundException {
        return itemDAO.addItemFromTable(itemTM);
    }

    @Override
    public boolean ifItemExists(String text) throws SQLException, ClassNotFoundException {
        return itemDAO.ifItemExist(text);
    }
}
