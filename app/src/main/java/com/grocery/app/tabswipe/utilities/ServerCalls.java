package com.grocery.app.tabswipe.utilities;

import com.google.gson.Gson;
import com.grocery.app.tabswipe.models.Post;

import java.util.ArrayList;

/**
 * Created by SG0222540 on 7/13/2015.
 */
public class ServerCalls {
    static Gson gson = new Gson();

    public static boolean submitMyItemsToServer() {
        Post post = new Post("usr100", new ArrayList<>(DataManipulationUtilities.myItems.values()));
        String json = gson.toJson(post, Post.class);
        return false;
    }

}
