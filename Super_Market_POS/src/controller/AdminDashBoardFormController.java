package controller;

import bo.BOFactory;
import bo.custom.AdminDashBoardBO;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import entity.Item;
import stagep.NewStage;
import view.tm.AdminDashBordItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class AdminDashBoardFormController {

    public AnchorPane AddItemFormId;
    public AnchorPane adminAddItemFormId;

    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemDescription;
    public JFXTextField txtItemPackSize;
    public JFXTextField txtItemQuantity;
    public JFXTextField txtItemSellingPrice;
    public JFXTextField txtItemBuyingPrice;

    public TableView<AdminDashBordItemTM> tblAddItemTable;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colPackSize;
    public TableColumn colQuantity;
    public TableColumn colBuyingPrice;
    public TableColumn colSellingPrice;

    static ArrayList<Item> itemArrayList = new ArrayList<>();
    public Label lblTime;
    public Label lblDate;

    //private final ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ITEM);
    private final AdminDashBoardBO adminDashBoardBO= (AdminDashBoardBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ADMINDASHBOARD);

    int tableSelectedRow = -1;

    @SuppressWarnings("DuplicatedCode")
    public void initialize() {

        loadItemDataTable();
        loadDateAndTime();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("itemPackSize"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colBuyingPrice.setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

        tblAddItemTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            tableSelectedRow = (int) newValue;
        });
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


    public void backToLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginWindowForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        NewStage.getInstance().getStage().setScene(scene);
        NewStage.getInstance().getStage().setTitle("Login Form");
        NewStage.getInstance().getStage().centerOnScreen();
    }

    public void openAdminStockForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AdminStockForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AddItemFormId.getChildren().clear();
        AddItemFormId.getChildren().add(load);
    }

    public void openAdminAddItemForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AdminDashBoarForm.fxml");
        Parent load = FXMLLoader.load(resource);
        adminAddItemFormId.getChildren().clear();
        adminAddItemFormId.getChildren().add(load);
    }

    public void openReportForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AdminReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AddItemFormId.getChildren().clear();
        AddItemFormId.getChildren().add(load);
    }

    public void openManageDiscountForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageDiscountForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setTitle("Manage Discount");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    public void addItemSaveButtonOnAction(ActionEvent actionEvent) {

        try {

            if (!adminDashBoardBO.ifItemExists(txtItemCode.getText()) ) {

                if (txtItemCode.getText().equals("") || txtItemName.getText().equals("") || txtItemDescription.getText().equals("") || txtItemPackSize.getText().equals("") || txtItemQuantity.getText().equals("") || txtItemBuyingPrice.getText().equals("") || txtItemSellingPrice.getText().equals("")) {
                    new Alert(Alert.AlertType.WARNING, "Please Enter Item Data").show();
                } else if (findItemCodeAlreadyExists(txtItemCode.getText())) {
                    new Alert(Alert.AlertType.WARNING, "Item Code Already Exists..").show();
                } else {
                    Item item = new Item(txtItemCode.getText(), txtItemName.getText(), txtItemDescription.getText(),
                            txtItemPackSize.getText(), Integer.parseInt(txtItemQuantity.getText()), Double.parseDouble(txtItemBuyingPrice.getText()), Double.parseDouble(txtItemSellingPrice.getText()));
                    if (itemArrayList.add(item)) {
                        loadItemDataTable();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again").show();
                    }
                }

            } else {
                new Alert(Alert.AlertType.WARNING, "This Item Code Already Exists, Please Find Stock").show();
            }

        } catch (NumberFormatException n) {
            new Alert(Alert.AlertType.WARNING, "Can't Save,Please Check Your Data..").show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean findItemCodeAlreadyExists(String id) {
        boolean answer = false;

        for (Item i : itemArrayList
        ) {
            if (i.getItemCode().equals(id)) {
                answer = true;
            }
        }
        return answer;
    }

    ObservableList<AdminDashBordItemTM> itemTMObservableList = FXCollections.observableArrayList();

    private void loadItemDataTable() {
        itemTMObservableList.clear();

        for (Item i : itemArrayList
        ) {
            itemTMObservableList.add(
                    new AdminDashBordItemTM(i.getItemCode(), i.getItemName(), i.getItemDescription(), i.getItemPackSize(), i.getQtyOnHand(), i.getBuyingPrice(), i.getSellingPrice()));
        }
        tblAddItemTable.setItems(itemTMObservableList);
    }

    public void deleteItemButtonOnAction(ActionEvent actionEvent) {
        if (tableSelectedRow == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a Row").show();
        } else {

            itemArrayList.remove(tableSelectedRow);
            loadItemDataTable();
        }
    }

    public void addItemsToStockOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<AdminDashBordItemTM> objects = FXCollections.observableArrayList();
        for (AdminDashBordItemTM i : itemTMObservableList
        ) {
            objects.add(i);
        }
        int temp = 0;
        for (AdminDashBordItemTM i : objects
        ) {

            if (adminDashBoardBO.saveItemFromTable(i)) {
                if (temp == 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Added To Stock.").show();
                    temp++;
                }
                itemArrayList.clear();
                loadItemDataTable();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }
    }
}
