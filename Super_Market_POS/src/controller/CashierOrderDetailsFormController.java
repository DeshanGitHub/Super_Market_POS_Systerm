package controller;

import bo.BOFactory;
import bo.custom.CashierOrderDetailsBO;
import dto.OrderDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.CashierOrderTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class CashierOrderDetailsFormController {
    public TableView<CashierOrderTM> tblOrderList;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colOrderTime;
    public TableColumn colCustomerId;
    public TableColumn colItems;
    public TableColumn colAmount;

    //private final OrderBO orderBO= (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ORDER);
    private final CashierOrderDetailsBO cashierOrderDetailsBO= (CashierOrderDetailsBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CASHIERORDERDETAILS);

    public void initialize(){
        try {
            colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
            colOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colOrderTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            colItems.setCellValueFactory(new PropertyValueFactory<>("items"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

            loadOrderTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void loadOrderTable() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDTO> allOrders = cashierOrderDetailsBO.getAllOrders();
                //new OrderController().getAllOrders();
        ObservableList<CashierOrderTM> cashierOrderTMObservableList = FXCollections.observableArrayList();


        for (OrderDTO temp: allOrders
             ) {
                cashierOrderTMObservableList.add(new CashierOrderTM(
                        temp.getOrderId(),temp.getOrderDate(),temp.getOrderTime(),
                        temp.getCustomerId(),temp.getItemCodeNames(),temp.getCost()
                ));
        }
        tblOrderList.setItems(cashierOrderTMObservableList);
    }

}
