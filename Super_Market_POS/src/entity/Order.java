package entity;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String customerId;
    private String orderDate;
    private String orderTime;
    private double cost;
    private ArrayList<ItemDetails> itemDetailsArrayList;
    private String itemCodeNames;

    public String getItemCodeNames() {
        return itemCodeNames;
    }

    public void setItemCodeNames(String itemCodeNames) {
        this.itemCodeNames = itemCodeNames;
    }

    public Order() {
    }

    public Order(String orderId, String customerId, String orderDate, String orderTime, double cost, ArrayList<ItemDetails> itemDetailsArrayList) {
        this.setOrderId(orderId);
        this.setCustomerId(customerId);
        this.setOrderDate(orderDate);
        this.setOrderTime(orderTime);
        this.setCost(cost);
        this.setItemDetailsArrayList(itemDetailsArrayList);
    }

    public Order(String orderId, String customerId, String orderDate, String orderTime, double cost, String itemCodeNames) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
        this.itemCodeNames = itemCodeNames;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<ItemDetails> getItemDetailsArrayList() {
        return itemDetailsArrayList;
    }

    public void setItemDetailsArrayList(ArrayList<ItemDetails> itemDetailsArrayList) {
        this.itemDetailsArrayList = itemDetailsArrayList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", cost=" + cost +
                ", itemDetailsArrayList=" + itemDetailsArrayList +
                '}';
    }
}
