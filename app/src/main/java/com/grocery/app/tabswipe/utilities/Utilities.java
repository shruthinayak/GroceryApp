package com.grocery.app.tabswipe.utilities;

import com.grocery.app.tabswipe.adapters.BuyAdapter;
import com.grocery.app.tabswipe.adapters.PostAdapter;
import com.grocery.app.tabswipe.models.DataModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Utilities {
    static ArrayList<DataModel> myDataset = new ArrayList<DataModel>();
    static HashMap<String, DataModel> myItems = new HashMap<String, DataModel>();
    public static PostAdapter mPostAdapter;
    public static BuyAdapter mBuyAdapter;

    public static void initializeData() {
        myDataset.clear();
        myDataset.add(new DataModel("Kawan's Chapathi", "nice, healthy, frozen", "1"));
        myDataset.add(new DataModel("Shredded coconut", "nice, healthy, frozen", "1"));
        myDataset.add(new DataModel("Rice bag 20lb Grain market", "healthy", "1"));
        myDataset.add(new DataModel("Parle-G", "G maane genius", "1"));
        myDataset.add(new DataModel("Dal", "Yellow Dal", "1"));
        myDataset.add(new DataModel("Maggi", "RIP", "1"));
        myDataset.add(new DataModel("Saabudhana", "Best for vada", "1"));
        myDataset.add(new DataModel("Maiyas", "Rare find in US", "1"));
        myDataset.add(new DataModel("Haldirams", "Was better in India", "1"));
        myDataset.add(new DataModel("MTR", "The hated brand", "1"));

    }

    public static ArrayList<DataModel> getDataSet() {
        return myDataset;
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

