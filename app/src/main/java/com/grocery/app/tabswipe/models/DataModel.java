package com.grocery.app.tabswipe.models;

/**
 * Created by SG0222540 on 6/28/2015.
 */
public class DataModel {
    private String itemName;
    private String quantity;
    private String description;

    public DataModel(String itemName, String description, String quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.description = description;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof DataModel){
            DataModel d = (DataModel) o;
            if(this.getItemName().equals(d.getItemName()) && this.getDescription().equals(d.getDescription())){
                return true;
            }
        }
        return false;
    }

}
