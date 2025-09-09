package model;

public class Part {
    private int partId;
    private String name;
    private double price;
    private int stock;

    public Part(int partId, String name, double price, int stock) {
        this.partId = partId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getPartId() { return partId; }
    public void setPartId(int partId) { this.partId = partId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}