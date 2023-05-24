package dto;

import entity.ItemDetails;

import java.util.ArrayList;

public class OrderDTO {
    private String orderId;
    private String customerId;
    private String orderDate;
    private String orderTime;
    private double cost;
    private ArrayList<ItemDetails> itemDetailsArrayList;
    private String itemCodeNames;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String customerId, String orderDate, String orderTime, double cost) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
    }

    public OrderDTO(String orderId, String customerId, String orderDate, String orderTime, double cost, ArrayList<ItemDetails> itemDetailsArrayList) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
        this.itemDetailsArrayList = itemDetailsArrayList;
    }

    public OrderDTO(String orderId, String customerId, String orderDate, String orderTime, double cost, String itemCodeNames) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
        this.itemCodeNames = itemCodeNames;
    }

    public OrderDTO(String orderId, String customerId, String orderDate, String orderTime, double cost, ArrayList<ItemDetails> itemDetailsArrayList, String itemCodeNames) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
        this.itemDetailsArrayList = itemDetailsArrayList;
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

    public String getItemCodeNames() {
        return itemCodeNames;
    }

    public void setItemCodeNames(String itemCodeNames) {
        this.itemCodeNames = itemCodeNames;
    }
}
