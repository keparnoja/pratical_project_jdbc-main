package model;

import java.util.Date;

public class Producer {
    int producerId;
    String name;
    Date FoundedIn;

    public Producer() {
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundedIn() {
        return FoundedIn;
    }

    public void setFoundedIn(Date foundedIn) {
        FoundedIn = foundedIn;
    }
}
