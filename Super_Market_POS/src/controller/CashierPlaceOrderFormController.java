package controller;

import bo.BOFactory;
import bo.custom.CashierPlaceOrderBO;
import dto.CustomerDTO;
import dto.OrderDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import entity.Cart;
import entity.ItemDetails;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controller.CashierSellFormController.cartArrayList;

public class CashierPlaceOrderFormController {

    public AnchorPane placeOrderFormId;

    public Label lblAmountDueId;
    public Label lblTotalId;
    public Label lblDiscountId;
    public Label lblDiscountReductionId;

    public TextField txtCashInputId;
    public ComboBox<String> cmbSelectCustomerId;
    public Label lblBalanceId;
    public Label lblSelectedCustomerName;

    public Label lblDateNew;
    public Label lblTimeNew;

    //private final CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CUSTOMER);
    //private final DiscountBO discountBO = (DiscountBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.DISCOUNT);
    //private final OrderBO orderBO= (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ORDER);
    private final CashierPlaceOrderBO cashierPlaceOrderBO = (CashierPlaceOrderBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CASHIERPLACEORDER);

    public void initialize() {
        try {
            loadDateAndTime();
            loadLabelData();
            loadCustomerIdComboBox();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //===========================================

        cmbSelectCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadSelectedCustomerName(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        //==========================================

        txtCashInputId.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                calculateBalance(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void loadDateAndTime() {

        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDateNew.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTimeNew.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

    }

    private void calculateBalance(String newValue) throws SQLException, ClassNotFoundException {
        if (newValue.equals("")) {
            lblBalanceId.setText("Rs. " + findAmount() * (-1));
        } else {
            double d = Double.parseDouble(newValue) - (int) findAmount();
            DecimalFormat twoFormat = new DecimalFormat("#.##");
            Double.valueOf(twoFormat.format(d));
            lblBalanceId.setText("Rs. " + (d));
        }
    }

    private void loadSelectedCustomerName(String id) throws SQLException, ClassNotFoundException {
        CustomerDTO customer = cashierPlaceOrderBO.getCustomer(id);
        //new CustomerController().getCustomer(id);
        lblSelectedCustomerName.setText("Name : " + customer.getCustomerName());
    }

    private void loadCustomerIdComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = cashierPlaceOrderBO.getAllCustomers();
        //new CustomerController().getAllCustomers();
        List<String> customerIds = new ArrayList<>();

        for (CustomerDTO c : allCustomers
        ) {
            customerIds.add(
                    c.getCustomerId()
            );
        }
        cmbSelectCustomerId.getItems().addAll(customerIds);
    }

    private void loadLabelData() throws SQLException, ClassNotFoundException {
        calculateTotal();
        findDiscount();
        double discountReductionPrice = findDiscountReductionPrice();
        lblDiscountReductionId.setText("Rs. " + discountReductionPrice);
        lblAmountDueId.setText("Rs. " + findAmount() + "/=");

    }

    private double findAmount() throws SQLException, ClassNotFoundException {
        double total = 0.0;
        for (Cart c : cartArrayList
        ) {
            total += c.getAmount();
        }
        double discountReductionPrice = findDiscountReductionPrice();

        double d = total - discountReductionPrice;
        DecimalFormat twoFormat = new DecimalFormat("#.##");
        return Double.valueOf(twoFormat.format(d));
    }

    private double findDiscountReductionPrice() throws SQLException, ClassNotFoundException {
        double total = 0.0;
        for (Cart c : cartArrayList
        ) {
            total += c.getAmount();
        }
        double discount = cashierPlaceOrderBO.getDiscount();
        //new DiscountController().getDiscount();

        if (total > 0 && discount > 0) {
            double discountReduction = (discount * total) / 100;
            DecimalFormat twoFormat = new DecimalFormat("#.##");
            return Double.valueOf(twoFormat.format(discountReduction));
        } else {
            return 0.0;
        }

    }

    private void findDiscount() throws SQLException, ClassNotFoundException {
        double discount = cashierPlaceOrderBO.getDiscount();
        //new DiscountController().getDiscount();
        if (discount > 0) {
            lblDiscountId.setText(discount + "%");
        } else {
            lblDiscountId.setText(0.0 + "%");
        }
    }

    void calculateTotal() {
        double total = 0.0;
        for (Cart c : cartArrayList
        ) {
            total += c.getAmount();
        }
        lblTotalId.setText("Rs. " + total + "/=");
    }

    public void cancelPlaceOrderForm(ActionEvent actionEvent) {
        Stage window = (Stage) placeOrderFormId.getScene().getWindow();
        window.close();
    }


    public void submitButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDetails> itemDetailsArrayList = new ArrayList<>();

        //System.out.println(cmbSelectCustomerId.getId());
        if (!lblTotalId.getText().equals("00.00")) {
            for (Cart temp : cartArrayList
            ) {
                itemDetailsArrayList.add(new ItemDetails(
                        temp.getItemCode(), temp.getQtyWantToBuy(), cashierPlaceOrderBO.getDiscount(), temp.getUnitPrice()
                ));
            }
            OrderDTO orderDTO = new OrderDTO(
                    //new OrderController().getOrderId(),
                    cashierPlaceOrderBO.getOrderId(),
                    cmbSelectCustomerId.getValue(),
                    lblDateNew.getText(),
                    lblTimeNew.getText(),
                    findAmount(),
                    itemDetailsArrayList
            );

            if (cashierPlaceOrderBO.purchaseOrder(orderDTO) /*new OrderController().purchaseOrder(order)*/) {
                new Alert(Alert.AlertType.INFORMATION, "Success").show();
                clearPage();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please Add Items To Cart..").show();
        }
    }

    private void clearPage() {
        cartArrayList.clear();
        lblTotalId.setText("00.00");
        lblDiscountId.setText("00");
        lblDiscountReductionId.setText("00.00");
        lblAmountDueId.setText("00.00");
        lblBalanceId.setText("00.00");
        lblSelectedCustomerName.setText("");
        txtCashInputId.clear();
    }
}
