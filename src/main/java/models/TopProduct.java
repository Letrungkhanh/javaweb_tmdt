package models;

public class TopProduct {
    private int productId;
    private String productName;
    private int totalSold;

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getTotalSold() {
        return totalSold;
    }
    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }
}
