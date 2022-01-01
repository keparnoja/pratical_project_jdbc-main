package model;

public class ReportRatingByCustomer {
    private String customerName;
    private int rating;
    private String menuName;
    private String restaurant;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        final String score = rating > 1 ?  rating + " stars" : rating + " star";

        return "ReportRatingByCustomer{" +
                "CustomerName='" + customerName + '\'' +
                ", Score=" + score +
                ", MenuName='" + menuName + '\'' +
                ", Restaurant='" + restaurant + '\'' +
                '}';
    }
}
