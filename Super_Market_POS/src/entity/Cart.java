package entity;

public class Cart {
    private String itemCode;
    private String itemName;
    private String description;
    private double unitPrice;
    private int qtyWantToBuy;
    private double amount;

    public Cart() {
    }

    public Cart(String itemCode, String itemName, String description, double unitPrice, int qtyWantToBuy, double amount) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyWantToBuy = qtyWantToBuy;
        this.amount = amount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyWantToBuy() {
        return qtyWantToBuy;
    }

    public void setQtyWantToBuy(int qtyWantToBuy) {
        this.qtyWantToBuy = qtyWantToBuy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
