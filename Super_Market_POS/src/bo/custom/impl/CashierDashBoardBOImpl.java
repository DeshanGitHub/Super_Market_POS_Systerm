package bo.custom.impl;

import bo.custom.CashierDashBoardBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class CashierDashBoardBOImpl implements CashierDashBoardBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOArrayList=new ArrayList<>();
        for (Item i:all
        ) {
            itemDTOArrayList.add(new ItemDTO(i.getItemCode(),i.getItemName(),i.getItemDescription(),i.getItemPackSize(),i.getQtyOnHand(),i.getBuyingPrice(),i.getSellingPrice()));
        }
        return itemDTOArrayList;
    }
}
