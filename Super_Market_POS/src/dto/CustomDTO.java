package dto;

public class CustomDTO {
    private String orderId;
    private String itemCode;
    private String itemName;
    private int orderQty;
    private double buyingPrice;
    private double sellingPrice;

    public CustomDTO() {
    }

    public CustomDTO(String orderId, String itemCode, String itemName, int orderQty, double buyingPrice, double sellingPrice) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.orderQty = orderQty;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
