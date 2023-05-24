package dao.custom;

import bo.SuperBO;
import dao.SuperDAO;
import dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {

    ArrayList<CustomDTO> orderAndItemDetails() throws SQLException, ClassNotFoundException;
}
