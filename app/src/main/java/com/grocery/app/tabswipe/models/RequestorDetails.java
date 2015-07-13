package com.grocery.app.tabswipe.models;

import java.util.List;

/**
 * Created by SG0222540 on 7/10/2015.
 */
public class RequestorDetails {
    public String getItm_id() {
        return itm_id;
    }

    public void setItm_id(String itm_id) {
        this.itm_id = itm_id;
    }

    String itm_id;
    String itm_name;
    String totalQty;
    List<Requestor> details;

    public RequestorDetails(String itemName, String itemId, String totalQty, List<Requestor> details) {
        this.itm_id = itemId;
        this.itm_name = itemName;
        this.totalQty = totalQty;
        this.details = details;
    }


    public List<Requestor> getBuyers(){
    return details;
    }

    public boolean areAllItemsLocked(){
        for(Requestor r: details){
            if(!r.isLock()){
                return false;
            }
        }
        return true;
    }
}
