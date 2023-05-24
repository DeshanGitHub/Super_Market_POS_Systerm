package view.tm;

public class CashierOrderTM {
    private String orderId;
    private String date;
    private String time;
    private String customerId;
    private String items;
    private double amount;

    public CashierOrderTM() {
    }

    public CashierOrderTM(String orderId, String date, String time, String customerId, String items, double amount) {
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.customerId = customerId;
        this.items = items;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
