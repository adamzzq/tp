package model;

public abstract class Item {
    private String id;
    private final String name;
    private final double unitPrice;

    public Item(String id, String name, double unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getID() {
        return this.id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.unitPrice;
    }
}
