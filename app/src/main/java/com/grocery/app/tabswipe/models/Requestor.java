package com.grocery.app.tabswipe.models;

/**
 * Created by SG0222540 on 7/10/2015.
 */
public class Requestor {
    String uname;
    String uid;
    String esplit;
    String qty;
    boolean bought;
    boolean lock;

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public Requestor(String userName, String userId, String splitwiseEmail, String qty, boolean bought, boolean lock) {
        this.uname = userName;
        this.uid = userId;
        this.esplit = splitwiseEmail;
        this.qty = qty;
        this.bought = bought;
        this.lock = lock;
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEsplit() {
        return esplit;
    }

    public void setEsplit(String esplit) {
        this.esplit = esplit;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
