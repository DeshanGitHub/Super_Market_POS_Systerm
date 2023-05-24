package bo.custom.impl;

import bo.custom.CashierSellBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class CashierSellBOImpl implements CashierSellBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ItemDTO getItem(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(id);
        return new ItemDTO(item.getItemCode(), item.getItemName(), item.getItemDescription(), item.getItemPackSize(), item.getQtyOnHand(), item.getBuyingPrice(), item.getSellingPrice());

    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOArrayList = new ArrayList<>();
        for (Item i : all
        ) {
            itemDTOArrayList.add(new ItemDTO(i.getItemCode(), i.getItemName(), i.getItemDescription(), i.getItemPackSize(), i.getQtyOnHand(), i.getBuyingPrice(), i.getSellingPrice()));
        }
        return itemDTOArrayList;
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderId();
    }
}
