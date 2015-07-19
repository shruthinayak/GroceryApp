package com.grocery.app.tabswipe.models;

/**
 * Created by upendra on 7/15/15.
 */
public class GroceryItem {
    private String itemId;
    private String itemName;
    private int quantity;
    private String description;
    private double unitPrice;

    public static final String[] fieldKeys = {
            "itemId",
            "itemName",
            "quantity",
            "description",
            "unitPrice"
    };

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroceryItem that = (GroceryItem) o;

        if (quantity != that.quantity) return false;
        if (Double.compare(that.unitPrice, unitPrice) != 0) return false;
        if (!itemId.equals(that.itemId)) return false;
        if (!itemName.equals(that.itemName)) return false;
        return !(description != null ? !description.equals(that.description) : that.description != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemId.hashCode();
        result = 31 * result + itemName.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(unitPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
