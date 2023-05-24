package entity;

public class OrderItemName {
    private String orderId;
    private String itemName;

    public OrderItemName() {
    }

    public OrderItemName(String orderId, String itemName) {
        this.setOrderId(orderId);
        this.setItemName(itemName);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
