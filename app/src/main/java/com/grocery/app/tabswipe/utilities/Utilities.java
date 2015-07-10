package com.grocery.app.tabswipe.utilities;

import com.grocery.app.tabswipe.adapters.BuyAdapter;
import com.grocery.app.tabswipe.adapters.PostAdapter;
import com.grocery.app.tabswipe.models.DataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utilities {
    public static HashMap<String,DataModel> myDataset = new HashMap<String,DataModel>();
    static HashMap<String, DataModel> myItems = new HashMap<String, DataModel>();
    public static PostAdapter mPostAdapter;
    public static BuyAdapter mBuyAdapter;

    public static void initializeData() {

        myDataset.clear();
        myDataset.put("Kawan's Chapathi", new DataModel("Kawan's Chapathi", "nice, healthy, frozen", "1"));
        myDataset.put("Shredded coconut", new DataModel("Shredded coconut", "nice, healthy, frozen", "1"));
        myDataset.put("Rice bag 20lb Grain market", new DataModel("Rice bag 20lb Grain market", "healthy", "1"));
        myDataset.put("Parle-G", new DataModel("Parle-G", "G maane genius", "1"));
        myDataset.put("Dal", new DataModel("Dal", "Yellow Dal", "1"));
        myDataset.put("Maggi", new DataModel("Maggi", "RIP", "1"));
        myDataset.put("Saabudhana", new DataModel("Saabudhana", "Best for vada", "1"));
        myDataset.put("Maiyas", new DataModel("Maiyas", "Rare find in US", "1"));
        myDataset.put("Haldirams", new DataModel("Haldirams", "Was better in India", "1"));
        myDataset.put("MTR", new DataModel("MTR", "The hated brand", "1"));

    }

    public static ArrayList<DataModel> getDataSet() {
        ArrayList<DataModel> objs = new ArrayList<DataModel>(myDataset.values());
        return objs;
    }

    public static ArrayList<DataModel> getMyItems() {
        return new ArrayList<>(myItems.values());
    }

    public static void addToMyItems(String itemName, DataModel d) {
        if (myItems.containsKey(itemName)) {
            int q = Integer.parseInt(myItems.get(itemName).getQuantity()) + 1;
            myItems.get(itemName).setQuantity(String.valueOf(q));
            mPostAdapter.add(myItems.get(itemName));
        } else {
            myItems.put(itemName, new DataModel(itemName, d.getDescription(), "1"));
            mPostAdapter.add(new DataModel(itemName, d.getDescription(), "1"));
        }
    }

    public static void removeFromMyItems(String itemName) {
        int q = Integer.parseInt(myItems.get(itemName).getQuantity()) - 1;
        if (q == 0) {
            myItems.remove(itemName);
        } else {
            myItems.get(itemName).setQuantity(String.valueOf(q));
        }
        mBuyAdapter.remove(myItems.get(itemName));
        mPostAdapter.remove(myItems.get(itemName));
    }
}

