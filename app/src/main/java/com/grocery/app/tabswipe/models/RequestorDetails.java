package com.grocery.app.tabswipe.models;

import java.util.List;

/**
 * Created by SG0222540 on 7/10/2015.
 */
public class RequestorDetails {
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    String itemId;
    String totalQty;
    List<Requestor> details;

    public RequestorDetails(String itemId, String totalQty, List<Requestor> details) {
        this.itemId = itemId;
        this.totalQty = totalQty;
        this.details = details;
    }


    public List<Requestor> getBuyers(){
    return details;
    }

}
