package entity;

import java.time.LocalDate;

public class OrderDataBase {
    private String orderId;
    private String custId;
    private LocalDate orderDate;
    private String orderTime;
    private double orderCost;

    public OrderDataBase() {
    }

    public OrderDataBase(String orderId, String custId, LocalDate orderDate, String orderTime, double orderCost) {
        this.orderId = orderId;
        this.custId = custId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderCost = orderCost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(double orderCost) {
        this.orderCost = orderCost;
    }
}
