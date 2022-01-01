package model;

import java.sql.Time;

public class Shopu {
    String ShopName;
    Time opening;
    String ShopType;
    String FirstName;
    String LastName;

    public Shopu() {
    }


    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public Time getOpening() {
        return opening;
    }

    public void setOpening(Time opening) {
        this.opening = opening;
    }

    public String getShopType() {
        return ShopType;
    }

    public void setShopType(String shopType) {
        ShopType = shopType;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
