package model;

public class ShopOwner {
    int ShopOwnerId;
    String firstName;
    String lastName;

    public int getShopOwnerId() {
        return ShopOwnerId;
    }

    public void setShopOwnerId(int shopOwnerId) {
        ShopOwnerId = shopOwnerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
