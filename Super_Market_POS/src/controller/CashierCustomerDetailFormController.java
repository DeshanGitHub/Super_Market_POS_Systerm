package controller;

import bo.BOFactory;
import bo.custom.CashierCustomerDetailBO;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CashierCustomerDetailFormController {

    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerCity;
    public TableColumn colCustomerProvince;
    public TableColumn colCustomerPostalCode;
    public TableColumn colRemoveCustomer;
    public JFXTextField txtSearchCustomerId;

    //private final CustomerBO customerBO= (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CUSTOMER);
    private final CashierCustomerDetailBO cashierCustomerDetailBO= (CashierCustomerDetailBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CASHIERCUSTOMERDETAIL);

    public void initialize() {
        try {
            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
            colCustomerCity.setCellValueFactory(new PropertyValueFactory<>("customerCity"));
            colCustomerProvince.setCellValueFactory(new PropertyValueFactory<>("customerProvince"));
            colCustomerPostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
            colRemoveCustomer.setCellValueFactory(new PropertyValueFactory<>("btn"));


            loadCustomerTable();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadCustomerTable() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = cashierCustomerDetailBO.getAllCustomers();
                //new CustomerController().getAllCustomers();
        ObservableList<CustomerTM> tmCustomerList = FXCollections.observableArrayList();

        for (CustomerDTO c : allCustomers
        ) {
            Button btn = new Button("Delete");
            tmCustomerList.add(new CustomerTM(c.getCustomerId(), c.getCustomerName(), c.getCustomerAddress(), c.getCustomerCity(), c.getCustomerProvince(), c.getCustomerPostalCode(), btn));

            btn.setOnAction((event) -> {

                @SuppressWarnings("DuplicatedCode") ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure,You Want To Delete This Customer?", yes, no);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.orElse(no) == yes) {
                    try {
                        //new CustomerController().deleteCustomer(c.getCustomerId());
                        cashierCustomerDetailBO.deleteCustomer(c.getCustomerId());
                        loadCustomerTable();
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }

            });
        }

        tblCustomer.setItems(tmCustomerList);

    }

    public void btnSaveCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //CustomerDTO customer = cashierCustomerDetailBO.isCustomerExists(txtCustomerId.getText());
                //new CustomerController().getCustomer(txtCustomerId.getText());


        //System.out.println(txtCustomerId.getText());

        if (txtCustomerId.getText().equals("") || txtCustomerName.getText().equals("")) {
            new Alert(Alert.AlertType.WARNING, "Please Enter Customer ID & Name").show();
        } else if (cashierCustomerDetailBO.isCustomerExists(txtCustomerId.getText())) {
            new Alert(Alert.AlertType.WARNING, "Customer Id Already Exists..").show();
        } else {

            CustomerDTO c1 = new CustomerDTO(
                    txtCustomerId.getText(),txtCustomerName.getText(),txtCustomerAddress.getText(),txtCity.getText(),txtProvince.getText(),txtPostalCode.getText()
                    /*txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), txtCity.getText(),
                    txtProvince.getText(), txtPostalCode.getText()*/
            );

            if (cashierCustomerDetailBO.saveCustomer(c1)) {
                new Alert(Alert.AlertType.INFORMATION, "Saved..").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }
            loadCustomerTable();

        }
    }

    @SuppressWarnings("DuplicatedCode")
    public void searchButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO customer = cashierCustomerDetailBO.getCustomer(txtCustomerId.getText());
                //new CustomerController().getCustomer(txtSearchCustomerId.getText());

        if (customer != null) {

            ObservableList<CustomerTM> observableCustomer = FXCollections.observableArrayList();

            Button btn = new Button("Delete");
            observableCustomer.add(new CustomerTM(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerCity(), customer.getCustomerProvince(), customer.getCustomerPostalCode(), btn));

            btn.setOnAction((event) -> {

                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure,You Want To Delete This Vehicle?", yes, no);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.orElse(no) == yes) {
                    try {
                        //new CustomerController().deleteCustomer(customer.getCustomerId());
                        cashierCustomerDetailBO.deleteCustomer(customer.getCustomerId());
                        loadCustomerTable();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            });

            tblCustomer.setItems(observableCustomer);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    public void refreshButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        loadCustomerTable();
    }
}
