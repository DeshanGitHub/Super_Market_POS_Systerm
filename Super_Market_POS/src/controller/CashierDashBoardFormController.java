package controller;

import bo.BOFactory;
import bo.custom.CashierDashBoardBO;
import dto.ItemDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import stagep.NewStage;
import view.tm.CashierStockTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class CashierDashBoardFormController {

    public AnchorPane cashierInStockTableFormId;
    public AnchorPane cashierDashBoardFullId;

    public TableView<CashierStockTM> tblStockItems;
    public TableColumn colCount;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colItemPackSize;
    public TableColumn colItemQty;
    public TableColumn colItemSellingPrice;
    public Label lblTime;
    public Label lblDate;

    //private final ItemBO itemBO= (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ITEM);
    private final CashierDashBoardBO cashierDashBoardBO = (CashierDashBoardBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CASHIERDASHBOARD);

    @SuppressWarnings("DuplicatedCode")
    public void initialize() {
        try {
            loadDateAndTime();
            loadStockTable();


            colCount.setCellValueFactory(new PropertyValueFactory<>("countNo"));
            colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
            colItemPackSize.setCellValueFactory(new PropertyValueFactory<>("itemPackSize"));
            colItemQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colItemSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDateAndTime() {

        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

    }


    private void loadStockTable() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = cashierDashBoardBO.getAllItems();
        //new ItemController().getAllItems();
        ObservableList<CashierStockTM> cashierStockTMObservableList = FXCollections.observableArrayList();
        int temp = 0;
        for (ItemDTO i : allItems
        ) {
            temp++;
            cashierStockTMObservableList.add(new CashierStockTM(
                    temp, i.getItemCode(), i.getItemName(), i.getItemDescription(), i.getItemPackSize(), i.getQtyOnHand(), i.getSellingPrice()
            ));
        }
        tblStockItems.setItems(cashierStockTMObservableList);
    }

    public void backToLoginForm(ActionEvent actionEvent) throws IOException {
        @SuppressWarnings("DuplicatedCode") URL resource = getClass().getResource("../view/LoginWindowForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        NewStage.getInstance().getStage().setScene(scene);
        NewStage.getInstance().getStage().setTitle("Login Form");
        NewStage.getInstance().getStage().centerOnScreen();
    }

    public void openCashierSellForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierSellForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierInStockTableFormId.getChildren().clear();
        cashierInStockTableFormId.getChildren().add(load);
    }

    public void openCashierStockForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierDashBoardFullId.getChildren().clear();
        cashierDashBoardFullId.getChildren().add(load);
    }

    public void openOrderDetailsForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierOrderDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierInStockTableFormId.getChildren().clear();
        cashierInStockTableFormId.getChildren().add(load);
    }

    public void openCustomerDetailsForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierCustomerDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierInStockTableFormId.getChildren().clear();
        cashierInStockTableFormId.getChildren().add(load);
    }

    public void btnRefreshTableOnAction(ActionEvent actionEvent) {
        tblStockItems.refresh();
    }
}
