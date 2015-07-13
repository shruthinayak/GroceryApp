package com.grocery.app.tabswipe.models;

public class DataModel {
    /*String itm_id;
    String itm_name;
    String itm_desc;
    String itm_qty;*/

    private String itm_id;
    private String itm_name;
    private String itm_qty;
    private String itm_desc;
    private String unitPrice;
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getItm_qty() {
        return itm_qty;
    }

    public void setItm_qty(String itm_qty) {
        this.itm_qty = itm_qty;
    }

    public String getItm_name() {
        return itm_name;
    }

    public void setItm_name(String itm_name) {
        this.itm_name = itm_name;
    }



    public DataModel(String itemName, String description, String quantity) {
        this.itm_name = itemName;
        this.itm_desc = description;
        this.itm_qty = quantity;
    }
    public String getItm_id() {
        return itm_id;
    }

    public void setItm_id(String itm_id) {
        this.itm_id = itm_id;
    }
    public String getItemName() {
        return itm_name;
    }

    public void setItemName(String itemName) {
        this.itm_name = itemName;
    }

    public String getQuantity() {
        return itm_qty;
    }

    public void setQuantity(String quantity) {
        this.itm_qty = quantity;
    }

    public String getItm_desc() {
        return itm_desc;
    }

    public void setItm_desc(String itm_desc) {
        this.itm_desc = itm_desc;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DataModel) {
            DataModel d = (DataModel) o;
            if (this.getItemName().equals(d.getItemName()) && this.getItm_desc().equals(d.getItm_desc())) {
                return true;
            }
        }
        return false;
    }

}
