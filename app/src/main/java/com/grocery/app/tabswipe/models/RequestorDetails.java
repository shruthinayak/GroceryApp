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

    public String getItm_name() {
        return itm_name;
    }

    public void setItm_name(String itm_name) {
        this.itm_name = itm_name;
    }

    public String getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
    }

    public List<Requestor> getDetails() {
        return details;
    }

    public void setDetails(List<Requestor> details) {
        this.details = details;
    }

    String itm_name;
    String totalQty;
    List<Requestor> details;

    public RequestorDetails(String itemName, String itemId, String totalQty, List<Requestor> details) {
        this.itm_id = itemId;
        this.itm_name = itemName;
        this.totalQty = totalQty;
        this.details = details;
    }


    public List<Requestor> getBuyers() {
        return details;
    }

    public boolean areAllItemsLocked() {
        for (Requestor r : details) {
            if (!r.isLock()) {
                return false;
            }
        }
        return true;
    }
    public boolean areAllItemsBought() {
        for (Requestor r : details) {
            if (!r.isBought()) {
                return false;
            }
        }
        return true;
    }
    public void setAllItemsLocked() {
        for (Requestor r : details) {
            r.setLock(true);
        }
    }
    public void setAllItemsBought() {
        for (Requestor r : details) {
            r.setBought(true);
        }
    }
}
