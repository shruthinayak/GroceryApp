package com.grocery.app.tabswipe.models;

import java.util.List;

/**
 * Created by SG0222540 on 7/13/2015.
 */
public class Post {
    String uid;
    List<DataModel> items;

    public Post(String uid, List<DataModel> items) {
        this.uid = uid;
        this.items = items;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<DataModel> getItems() {
        return items;
    }

    public void setItems(List<DataModel> items) {
        this.items = items;
    }


}
