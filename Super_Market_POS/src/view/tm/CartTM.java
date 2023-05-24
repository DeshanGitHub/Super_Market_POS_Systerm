package view.tm;

import javafx.scene.control.Button;

public class CartTM {
    private String itemCode;
    private String itemName;
    private String description;
    private double unitPrice;
    private int qtyWantToBuy;
    private double amount;
    private Button btn;

    public CartTM() {
    }

    public CartTM(String itemCode, String itemName, String description, double unitPrice, int qtyWantToBuy, double amount, Button btn) {
        this.setItemCode(itemCode);
        this.setItemName(itemName);
        this.setDescription(description);
        this.setUnitPrice(unitPrice);
        this.setQtyWantToBuy(qtyWantToBuy);
        this.setAmount(amount);
        this.setBtn(btn);
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
