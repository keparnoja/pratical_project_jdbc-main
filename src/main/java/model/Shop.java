package model;

import java.sql.Time;

public class Shop {
    int ShopId;
    String shopName;
    Time openingTime;
    Time closingTime;
    int Shop_TypeId;
    int ShopOwnerId;

    public Shop() {
    }

    public int getShopId() {
        return ShopId;
    }

    public void setShopId(int shopId) {
        ShopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Time getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Time openingTime) {
        this.openingTime = openingTime;
    }

    public Time getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Time closingTime) {
        this.closingTime = closingTime;
    }

    public int getShop_TypeId() {
        return Shop_TypeId;
    }

    public void setShop_TypeId(int shop_TypeId) {
        Shop_TypeId = shop_TypeId;
    }

    public int getShopOwnerId() {
        return ShopOwnerId;
    }

    public void setShopOwnerId(int shopOwnerId) {
        ShopOwnerId = shopOwnerId;
    }
}
