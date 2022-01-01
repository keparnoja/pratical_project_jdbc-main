package model;

import java.sql.Date;

public class Produ {
    Date date;
    int total;
    String name;
    int supply;
    String CategoryName;
    String ProducerName;
    public Produ() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getProducerName() {
        return ProducerName;
    }

    public void setProducerName(String producerName) {
        ProducerName = producerName;
    }
}
