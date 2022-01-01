package model;

import java.util.Date;

public class Products {
    int productId;
    String productName;
    int supplyAmount;
    Date ReleaseDate;
    int CategoryID;

    public Products() {
    }


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

    public int getSupplyAmount() {
        return supplyAmount;
    }

    public void setSupplyAmount(int supplyAmount) {
        this.supplyAmount = supplyAmount;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }
}
