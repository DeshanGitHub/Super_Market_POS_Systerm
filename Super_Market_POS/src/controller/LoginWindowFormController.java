package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import stagep.NewStage;

import java.io.IOException;
import java.net.URL;

public class LoginWindowFormController {

    public JFXTextField txtUserId;
    public JFXPasswordField txtPassword;
    public JFXComboBox<String> cmbSelectRole;
    public BorderPane loginBorderPane;
    public Label txtWrongPassword;
    public JFXButton logInButtonId;


    private String[] roles = {"Administrator", "Cashier"};

    public LoginWindowFormController() {
    }

    public void initialize() {
        cmbSelectRole.getItems().addAll(roles);
    }


    public void logInButtonOnAction(ActionEvent actionEvent) throws IOException {

        try {

            if (cmbSelectRole.getValue().equals("Administrator") && txtUserId.getText().equals("admin") && txtPassword.getText().equals("123")) {
                URL resource = getClass().getResource("../view/AdminDashBoarForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);

                Stage stage = (Stage) loginBorderPane.getScene().getWindow();
                stage.close();

                NewStage.getInstance().getStage().setScene(scene);
                NewStage.getInstance().getStage().setTitle("Admin Form");
                NewStage.getInstance().getStage().show();
            } else if (txtUserId.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                txtWrongPassword.setText("Please Enter Your Data.!!");
            } else if (cmbSelectRole.getValue().equals("Cashier") && txtUserId.getText().equals("cashier") && txtPassword.getText().equals("123")) {
                URL resource = getClass().getResource("../view/CashierDashBoardForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage = (Stage) logInButtonId.getScene().getWindow();
                stage.close();
                Stage stage1 = NewStage.getInstance().getStage();
                stage1.setTitle("Cashier Form");
                stage1.setScene(scene);
                stage1.show();
            } else {
                txtWrongPassword.setText("Invalid User ID OR Password, Try again.!");
            }

        } catch (Exception e) {
            txtWrongPassword.setText("Please Enter Your Data.!!");
        }
    }
}
