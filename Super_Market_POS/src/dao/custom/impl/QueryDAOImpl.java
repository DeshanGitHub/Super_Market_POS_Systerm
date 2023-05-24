package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import dto.CustomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomDTO> orderAndItemDetails() throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> all=new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT orderdetail.orderId, orderdetail.itemCode, item.itemName, orderdetail.orderQTY, item.buyingPrice, item.sellingPrice FROM orderdetail INNER JOIN item ON orderdetail.itemCode=item.itemCode");
        while (rst.next()){
            all.add(new CustomDTO(rst.getString("orderId"),rst.getString("itemCode"),rst.getString("itemName"),rst.getInt("orderQTY"),rst.getDouble("buyingPrice"),rst.getDouble("sellingPrice")));
        }
        return all;
    }
}
