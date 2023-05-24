package controller;

import bo.BOFactory;
import bo.custom.CashierSellBO;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entity.Cart;
import view.tm.CartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CashierSellFormController {

    public ComboBox<String> cmbItemCode;
    public TextField txtItemName;

    public JFXTextField txtQtyWantToBuy;
    public TextField txtOrderId;
    public TextField txtItemQtyOnHand;
    public TextField txtItemDescription;
    public TextField txtItemPrice;
    public TextField txtItemAvailability;
    public Label lblAmount;
    public TableView<CartTM> tblOrderCart;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colItemUnitPrice;
    public TableColumn colQtyWantToBuy;
    public TableColumn colAmount;
    public TableColumn colRemoveItem;
    public Label lblTotalCart;
    public Label lblOrderId;

    //private final ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ITEM);
    //private final OrderBO orderBO = (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ORDER);
    private final CashierSellBO cashierSellBO= (CashierSellBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CASHIERSELL);

    private int cartTableSelectedRow = -1;

    static ArrayList<Cart> cartArrayList = new ArrayList<>();

    public void initialize() {
        loadCartTable();


        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyWantToBuy.setCellValueFactory(new PropertyValueFactory<>("qtyWantToBuy"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colRemoveItem.setCellValueFactory(new PropertyValueFactory<>("btn"));

        try {
            loadItemIdComboBox();
            setOrderId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //==============================

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setTextFieldItemData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblOrderCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartTableSelectedRow = (int) newValue;
            System.out.println(newValue);
        });

        //===================================

        txtQtyWantToBuy.textProperty().addListener((observable, oldValue, newValue) -> {
            setAmountLabel(newValue);
        });

    }

    public void setOrderId() throws SQLException, ClassNotFoundException {
        lblOrderId.setText(cashierSellBO.getOrderId());
    }

    ObservableList<CartTM> cartTMObservableList = FXCollections.observableArrayList();

    private void loadCartTable() {

       /* if (findCanLoadTable()) {
            Button btn = new Button("Remove");
            cartTMObservableList.add(new CartTM(
                    cmbItemCode.getValue(), txtItemName.getText(), txtItemDescription.getText(), Double.parseDouble(txtItemPrice.getText()), Integer.parseInt(txtQtyWantToBuy.getText()), (Double.parseDouble(txtItemPrice.getText()) * Double.parseDouble(txtQtyWantToBuy.getText())), btn
            ));

            btn.setOnAction((event)->{
                ButtonType yes=new ButtonType("Yes",ButtonBar.ButtonData.OK_DONE);
                ButtonType no=new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure,You Want To Delete This Customer?",yes,no);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> result = alert.showAndWait();

                btn.

                if(result.orElse(no)==yes){
                   cartTMObservableList.remove()
                }
            });
        }*/
//        if(findCanLoadTable()){
//            cartArrayList.add(new Cart(cmbItemCode.getValue(),txtItemName.getText(),txtItemDescription.getText(),Double.parseDouble(txtItemPrice.getText()),Integer.parseInt(txtQtyWantToBuy.getText()),Double.parseDouble(txtQtyWantToBuy.getText())*Double.parseDouble(txtItemPrice.getText())));
//        }

        cartTMObservableList.clear();
        for (Cart c : cartArrayList
        ) {

            Button btn = new Button("Delete");
            cartTMObservableList.add(new CartTM(
                    c.getItemCode(), c.getItemName(), c.getDescription(), c.getUnitPrice(), c.getQtyWantToBuy(), c.getAmount(), btn
            ));
            btn.setOnAction((event) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure,You Want To Delete This Item?", yes, no);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.orElse(no) == yes) {
                    cartArrayList.remove(c);
                    loadCartTable();
                }
            });

        }

        tblOrderCart.setItems(cartTMObservableList);
        calculateTotal();
    }

    private boolean findCanLoadTable() {
        if (cmbItemCode.getSelectionModel().isEmpty() || txtQtyWantToBuy.getText().equals("")) {
            //====================


            //==========================
            return false;
        } else {
            return true;
        }
    }

    private boolean findTwo() {
        for (Cart c : cartArrayList
        ) {
            if (cmbItemCode.getValue().equals(c.getItemCode())
                    && Integer.parseInt(txtItemQtyOnHand.getText()) < c.getQtyWantToBuy() + Integer.parseInt(txtQtyWantToBuy.getText())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    private void setAmountLabel(String qtyWantToBuy) {
        try {

            if (txtItemPrice.getText().equals("") || txtQtyWantToBuy.getText().equals("")) {
                lblAmount.setText("0.00/=");
            } else {
                int qty = Integer.parseInt(qtyWantToBuy);
                double price = Double.parseDouble(txtItemPrice.getText());
                lblAmount.setText("Rs. " + (qty * price) + "/=");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void setTextFieldItemData(String newValue) throws SQLException, ClassNotFoundException {
        ItemDTO item = cashierSellBO.getItem(newValue);

        if (item != null) {
            txtItemName.setText(item.getItemName());
            txtItemDescription.setText(item.getItemDescription());
            txtItemPrice.setText(String.valueOf(item.getSellingPrice()));
            txtItemQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            txtItemAvailability.setText(item.getItemPackSize());
            if (item.getQtyOnHand() > 0) {
                txtOrderId.setText("Available");
            } else {
                txtOrderId.setText("Sold Out");
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result!!").show();
        }
    }

    private void loadItemIdComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = cashierSellBO.getAllItems();
        //new ItemController().getAllItems();
        List<String> itemIds = new ArrayList<>();

        for (ItemDTO item : allItems
        ) {
            itemIds.add(item.getItemCode());
        }
        cmbItemCode.getItems().addAll(itemIds);
    }

    private void clearPage() {
        cartTMObservableList.clear();
        tblOrderCart.refresh();
        txtItemQtyOnHand.clear();
        txtOrderId.clear();
        txtQtyWantToBuy.clear();
        txtItemAvailability.clear();
        txtItemPrice.clear();
        txtItemDescription.clear();
        txtItemName.clear();
        lblTotalCart.setText("Rs. 0.0");
    }

    public void openPlaceOrderForm(ActionEvent actionEvent) throws IOException {
        if (cartArrayList.size() > 0) {
            URL resource = getClass().getResource("../view/CashierPlaceOrderForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            clearPage();

        } else {
            new Alert(Alert.AlertType.WARNING, "Please Add Items To Table..").show();
        }
    }

    public void addToCartButtonOnAction(ActionEvent actionEvent) {
        if (findCanLoadTable()) {
            if (Integer.parseInt(txtQtyWantToBuy.getText()) > Integer.parseInt(txtItemQtyOnHand.getText()) || findTwo()) {
                new Alert(Alert.AlertType.WARNING, "Invalid Quantity, Please Check..").show();
                return;
            }

            Cart c = new Cart(cmbItemCode.getValue(), txtItemName.getText(), txtItemDescription.getText(), Double.parseDouble(txtItemPrice.getText()), Integer.parseInt(txtQtyWantToBuy.getText()), Double.parseDouble(txtQtyWantToBuy.getText()) * Double.parseDouble(txtItemPrice.getText()));

            int rowNum = isExists(c);
            if (rowNum == -1) {
                cartArrayList.add(c);
            } else {
                Cart temp = cartArrayList.get(rowNum);
                Cart newTemp = new Cart(
                        temp.getItemCode(), temp.getItemName(), temp.getDescription(),
                        temp.getUnitPrice(), temp.getQtyWantToBuy() + Integer.parseInt(txtQtyWantToBuy.getText()), temp.getAmount() + (Double.parseDouble(txtQtyWantToBuy.getText()) * Double.parseDouble(txtItemPrice.getText()))
                );
                cartArrayList.remove(rowNum);
                cartArrayList.add(newTemp);
            }


            loadCartTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Enter Item Id & Quantity").show();
        }

    }

    private int isExists(Cart c) {
        for (int i = 0; i < cartArrayList.size(); i++) {
            if (c.getItemCode().equals(cartArrayList.get(i).getItemCode())) {
                return i;
            }
        }
        return -1;
    }

    void calculateTotal() {
        double total = 0.0;
        for (Cart c : cartArrayList
        ) {
            total += c.getAmount();
        }
        lblTotalCart.setText("Rs. " + total + "/=");
    }

    void createOrderId() {

    }
}
