package controller;

import bo.BOFactory;
import bo.custom.AdminStockBO;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.AdminStockTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminStockFormController {

    public TableView tblAdminStockTable;
    public TableColumn colItemCount;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colItemPackSize;
    public TableColumn colItemQuantity;
    public TableColumn colItemBuyingPrice;
    public TableColumn colItemSellingPrice;

    //private final ItemBO itemBO= (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ITEM);
    private final AdminStockBO adminStockBO= (AdminStockBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ADMINSTOCK);

    int tableSelectedRow=-1;

    @SuppressWarnings("DuplicatedCode")
    public void initialize(){

        try {
            loadTableData();

            colItemCount.setCellValueFactory(new PropertyValueFactory<>("countNo"));
            colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
            colItemPackSize.setCellValueFactory(new PropertyValueFactory<>("itemPackSize"));
            colItemQuantity.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colItemBuyingPrice.setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
            colItemSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblAdminStockTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            tableSelectedRow=(int) newValue;
        });
    }

    ObservableList<AdminStockTM> adminStockTMObservableList = FXCollections.observableArrayList();
    private void loadTableData() throws SQLException, ClassNotFoundException {
        adminStockTMObservableList.clear();
        ArrayList<ItemDTO> allItems = adminStockBO.getAllItems();
                //new ItemController().getAllItems();

        int num=0;

        for (ItemDTO i:allItems
             ) {
            num++;
            adminStockTMObservableList.add(new AdminStockTM(
                    num,i.getItemCode(),i.getItemName(),i.getItemDescription(),i.getItemPackSize(),i.getQtyOnHand(),i.getBuyingPrice(),i.getSellingPrice()
            ));
        }
        tblAdminStockTable.setItems(adminStockTMObservableList);
    }

    public void removeItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(tableSelectedRow==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a Row..").show();
        }else {
            AdminStockTM temp=adminStockTMObservableList.get(tableSelectedRow);
            if( adminStockBO.deleteItem(temp.getItemCode()) ){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted..").show();
                loadTableData();
            }
        }
    }

    public void refreshTableOnAction(ActionEvent actionEvent) {
            tblAdminStockTable.refresh();
    }
}
