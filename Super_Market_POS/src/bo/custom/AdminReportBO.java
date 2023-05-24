package bo.custom;

import bo.SuperBO;
import dto.CustomDTO;
import dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminReportBO extends SuperBO {
    ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException;

    ArrayList<CustomDTO> getOrderDetailsForNetIncome() throws SQLException, ClassNotFoundException;
}
