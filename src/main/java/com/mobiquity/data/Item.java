package com.mobiquity.data;

/**
 * @author Reetesh Kumar
 * Immutable Item Pojo
 * Holds the information about the items in the package
 */
public final class Item {
    private final int indexNumber;
    private final double weight;
    private final String price;

    public Item(int indexNumber, double weight, String price) {
        this.indexNumber = indexNumber;
        this.weight = Double.valueOf(weight).intValue();
        this.price = price;
    }
    public int getIndexNumber() {
        return indexNumber;
    }
    public double getWeight() {
        return weight;
    }
    public String getPrice() {
        return price;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("indexNumber=").append(indexNumber);
        sb.append(", weight=").append(weight);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}