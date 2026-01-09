package models;

import java.util.Date;

public class Product {
    private int productId;
    private String title;
    private String alias;
    private String description;
    private String detail;
    private String image;
    private double price;
    private double priceSale;
    private int quantity;
    private int categoryId;
    private boolean isNew;
    private boolean isBestSeller;
    private int unitInStock;
    private boolean isActive;
    private float star;
    private int createdBy;
    private Date createdDate;
    private int modifiedBy;
    private Date modifiedDate;

    public Product() {}

    public Product(int productId, String title, String alias, String description, String detail,
                   String image, double price, double priceSale, int quantity, int categoryId,
                   boolean isNew, boolean isBestSeller, int unitInStock, boolean isActive,
                   float star, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate) {
        this.productId = productId;
        this.title = title;
        this.alias = alias;
        this.description = description;
        this.detail = detail;
        this.image = image;
        this.price = price;
        this.priceSale = priceSale;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.isNew = isNew;
        this.isBestSeller = isBestSeller;
        this.unitInStock = unitInStock;
        this.isActive = isActive;
        this.star = star;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    // Getter và Setter
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getPriceSale() { return priceSale; }
    public void setPriceSale(double priceSale) { this.priceSale = priceSale; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public boolean isNew() { return isNew; }
    public void setNew(boolean isNew) { this.isNew = isNew; }
    public boolean isBestSeller() { return isBestSeller; }
    public void setBestSeller(boolean isBestSeller) { this.isBestSeller = isBestSeller; }
    public int getUnitInStock() { return unitInStock; }
    public void setUnitInStock(int unitInStock) { this.unitInStock = unitInStock; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
    public float getStar() { return star; }
    public void setStar(float star) { this.star = star; }
    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    public int getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(int modifiedBy) { this.modifiedBy = modifiedBy; }
    public Date getModifiedDate() { return modifiedDate; }
    public void setModifiedDate(Date modifiedDate) { this.modifiedDate = modifiedDate; }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", alias='" + alias + '\'' +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", priceSale=" + priceSale +
                ", quantity=" + quantity +
                ", categoryId=" + categoryId +
                ", isNew=" + isNew +
                ", isBestSeller=" + isBestSeller +
                ", unitInStock=" + unitInStock +
                ", isActive=" + isActive +
                ", star=" + star +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", modifiedBy=" + modifiedBy +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}

