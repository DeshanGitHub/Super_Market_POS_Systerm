package entity;

public class ItemDetails {
    private String itemCode;
    private int qtyForSale;
    private double discount;
    private double unitPrice;

    public ItemDetails() {
    }

    public ItemDetails(String itemCode, int qtyForSale, double discount, double unitPrice) {
        this.setItemCode(itemCode);
        this.setQtyForSale(qtyForSale);
        this.setDiscount(discount);
        this.setUnitPrice(unitPrice);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQtyForSale() {
        return qtyForSale;
    }

    public void setQtyForSale(int qtyForSale) {
        this.qtyForSale = qtyForSale;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemCode='" + itemCode + '\'' +
                ", qtyForSale=" + qtyForSale +
                ", discount=" + discount +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
