package dto;

public class ItemDTO {
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private String itemPackSize;
    private int qtyOnHand;
    private double buyingPrice;
    private double sellingPrice;

    public ItemDTO() {
    }

    public ItemDTO(String itemCode, String itemName, String itemDescription, String itemPackSize, int qtyOnHand, double buyingPrice, double sellingPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPackSize = itemPackSize;
        this.qtyOnHand = qtyOnHand;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
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
