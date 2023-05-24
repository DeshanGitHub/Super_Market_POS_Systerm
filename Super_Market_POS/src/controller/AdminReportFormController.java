package controller;

import bo.BOFactory;
import bo.custom.AdminReportBO;
import dto.CustomDTO;
import dto.OrderDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class AdminReportFormController {

    public Label lblDailyTotalSaleId;
    public Label lblDailyTransactionId;
    public Label dailyNetIncomesId;
    public Label lblMonthlyTotalSales;
    public Label lblMonthlyTransactions;
    public Label lblAnnuallyTotalSales;
    public Label lblAnnuallyTransactions;
    public ComboBox<String> cmbCustomerIds;
    public TextField txtCustomerName;
    public TextField txtIncomeReceived;

    AdminReportBO adminReportBO = (AdminReportBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ADMINREPORT);

    public void initialize() {
        try {
            setDailySaleDetail();
            setMonthlySaleDetail();
            setAnnuallySaleDetail();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setAnnuallySaleDetail() throws SQLException, ClassNotFoundException {
        double allOrderCost = 0;
        int orderCount = 0;
        double netIncome = 0;

        for (int i = 0; i < 300; i++) {
            LocalDate date = LocalDate.now().minusDays(i + 1);
            ArrayList<OrderDTO> orderDTOArrayList = adminReportBO.getAllOrders();
            ArrayList<CustomDTO> customDTOArrayList = adminReportBO.getOrderDetailsForNetIncome();

            for (OrderDTO dto : orderDTOArrayList
            ) {
                if (date.toString().equals(dto.getOrderDate())) {
                    /*Set total sale & transactions*/
                    allOrderCost += dto.getCost();
                    orderCount++;
                    DecimalFormat twoFormat = new DecimalFormat("#.##");
                    lblAnnuallyTotalSales.setText(String.valueOf(Double.valueOf(twoFormat.format(allOrderCost))));
                    //lblAnnuallyTotalSales.setText(String.valueOf(allOrderCost));
                    lblAnnuallyTransactions.setText(String.valueOf(orderCount));

                    /*set net income*/
                    double buyingPrice = 0;
                    for (CustomDTO custom : customDTOArrayList
                    ) {
                        if (dto.getOrderId().equals(custom.getOrderId())) {
                            buyingPrice += custom.getBuyingPrice() * custom.getOrderQty();
                            netIncome = allOrderCost - buyingPrice;
                        }
                    }
                    //dailyNetIncomesId.setText(String.valueOf(netIncome));

                } else {

                }
            }
        }
    }


    private void setMonthlySaleDetail() throws SQLException, ClassNotFoundException {
        double allOrderCost = 0;
        int orderCount = 0;
        double netIncome = 0;

        for (int i = 0; i < 30; i++) {
            LocalDate date = LocalDate.now().minusDays(i + 1);
            ArrayList<OrderDTO> orderDTOArrayList = adminReportBO.getAllOrders();
            ArrayList<CustomDTO> customDTOArrayList = adminReportBO.getOrderDetailsForNetIncome();

            for (OrderDTO dto : orderDTOArrayList
            ) {
                if (date.toString().equals(dto.getOrderDate())) {
                    /*Set total sale & transactions*/
                    allOrderCost += dto.getCost();
                    orderCount++;
                    DecimalFormat twoFormat = new DecimalFormat("#.##");
                    lblMonthlyTotalSales.setText(String.valueOf(Double.valueOf(twoFormat.format(allOrderCost))));
                   // lblMonthlyTotalSales.setText(String.valueOf(allOrderCost));
                    lblMonthlyTransactions.setText(String.valueOf(orderCount));

                    /*set net income*/
                    double buyingPrice = 0;
                    for (CustomDTO custom : customDTOArrayList
                    ) {
                        if (dto.getOrderId().equals(custom.getOrderId())) {
                            buyingPrice += custom.getBuyingPrice() * custom.getOrderQty();
                            netIncome = allOrderCost - buyingPrice;
                        }
                    }
                    //dailyNetIncomesId.setText(String.valueOf(netIncome));

                } else {

                }
            }
        }
    }

    private void setDailySaleDetail() throws SQLException, ClassNotFoundException {
        double allOrderCost = 0;
        int orderCount = 0;
        double netIncome = 0;
        ArrayList<OrderDTO> orderDTOArrayList = adminReportBO.getAllOrders();
        ArrayList<CustomDTO> customDTOArrayList = adminReportBO.getOrderDetailsForNetIncome();

        for (OrderDTO dto : orderDTOArrayList
        ) {
            if (LocalDate.now().toString().equals(dto.getOrderDate())) {
                /*Set total sale & transactions*/
                allOrderCost += dto.getCost();
                orderCount++;
                DecimalFormat twoFormat = new DecimalFormat("#.##");
                lblDailyTotalSaleId.setText(String.valueOf(Double.valueOf(twoFormat.format(allOrderCost))));

                //lblDailyTotalSaleId.setText(String.valueOf(allOrderCost));
                lblDailyTransactionId.setText(String.valueOf(orderCount));

                /*set net income*/
                double buyingPrice = 0;
                for (CustomDTO custom : customDTOArrayList
                ) {
                    if (dto.getOrderId().equals(custom.getOrderId())) {
                        buyingPrice += custom.getBuyingPrice() * custom.getOrderQty();
                        netIncome = allOrderCost - buyingPrice;
                    }
                }
                //dailyNetIncomesId.setText(String.valueOf(netIncome));
            } else {

            }
        }
    }

    public void searchButtonOnAction(ActionEvent actionEvent) {

    }
}
