package view.tm;

public class AdminDashBordItemTM {
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private String itemPackSize;
    private int qtyOnHand;
    private double buyingPrice;
    private double sellingPrice;

    public AdminDashBordItemTM() {
    }

    public AdminDashBordItemTM(String itemCode, String itemName, String itemDescription, String itemPackSize, int qtyOnHand, double buyingPrice, double sellingPrice) {
        this.setItemCode(itemCode);
        this.setItemName(itemName);
        this.setItemDescription(itemDescription);
        this.setItemPackSize(itemPackSize);
        this.setQtyOnHand(qtyOnHand);
        this.setBuyingPrice(buyingPrice);
        this.setSellingPrice(sellingPrice);
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemPackSize() {
        return itemPackSize;
    }

    public void setItemPackSize(String itemPackSize) {
        this.itemPackSize = itemPackSize;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
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
