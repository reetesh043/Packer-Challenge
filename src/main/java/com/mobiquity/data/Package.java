package com.mobiquity.data;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author Reetesh Kumar
 * Immutable Package Pojo
 * Holds the information about the package
 */
public final class Package {
    private final int weight;
    private final List<Item> itemList;

    public Package(int weight, List<Item> itemList) {
        this.weight = weight;
        this.itemList = itemList;
    }

    public int getWeight() {
        return weight;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Package.class.getSimpleName() + "[", "]")
                .add("weight=" + weight)
                .add("itemList=" + itemList)
                .toString();
    }
}