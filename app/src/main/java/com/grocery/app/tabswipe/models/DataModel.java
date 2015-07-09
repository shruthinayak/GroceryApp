package com.grocery.app.tabswipe.models;

public class DataModel {


    private String item_id;
    private String item_name;
    private String total_qty;
    private String description;
    private String unitPrice;
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotal_qty() {
        return total_qty;
    }

    public void setTotal_qty(String total_qty) {
        this.total_qty = total_qty;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }



    public DataModel(String itemName, String description, String quantity) {
        this.item_name = itemName;
        this.description = description;
        this.total_qty = quantity;
    }
    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
    public String getItemName() {
        return item_name;
    }

    public void setItemName(String itemName) {
        this.item_name = itemName;
    }

    public String getQuantity() {
        return total_qty;
    }

    public void setQuantity(String quantity) {
        this.total_qty = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DataModel) {
            DataModel d = (DataModel) o;
            if (this.getItemName().equals(d.getItemName()) && this.getDescription().equals(d.getDescription())) {
                return true;
            }
        }
        return false;
    }

}
