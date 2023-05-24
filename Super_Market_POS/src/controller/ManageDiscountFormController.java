package controller;

import bo.BOFactory;
import bo.custom.DiscountBO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ManageDiscountFormController {

    public AnchorPane manageDiscountFormId;
    public TextField txtSetNewDiscountId;
    public Label lblCurrentDiscountId;

    private final DiscountBO discountBO = (DiscountBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.DISCOUNT);

    public void initialize() {
        try {
            loadDiscountLabel();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDiscountLabel() throws SQLException, ClassNotFoundException {
        double discount = discountBO.getDiscount();
        //new DiscountController().getDiscount();
        lblCurrentDiscountId.setText(discount + "%");
    }

    public void cancelManageDiscountForm(ActionEvent actionEvent) {
        Stage stage = (Stage) manageDiscountFormId.getScene().getWindow();
        stage.close();
    }

    public void btnSaveDiscountOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtSetNewDiscountId.getText().equals("")) {
            new Alert(Alert.AlertType.WARNING, "Please Input New Discount Value..").show();
        } else {

            try {
                if (discountBO.getDiscountToValidation(Double.parseDouble(txtSetNewDiscountId.getText())) == 0) {
                    double discount = discountBO.getDiscount();
                    discountBO.deleteDiscount(discount);

                    boolean b = discountBO.saveDiscount(Double.parseDouble(txtSetNewDiscountId.getText()));
                    if (b) {
                        new Alert(Alert.AlertType.INFORMATION, "Discount Saved").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Can't Save Discount").show();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Discount Already Exists!").show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        loadDiscountLabel();
    }
}
