package com.grocery.app.tabswipe.utilities;

import com.grocery.app.tabswipe.adapters.PostAdapter;
import com.grocery.app.tabswipe.models.DataModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SG0222540 on 6/28/2015.
 */
public class Utilities {
    static ArrayList<DataModel> myDataset = new ArrayList<DataModel>();
    static HashMap<String, DataModel> myItems = new HashMap<String, DataModel>();
    public static PostAdapter mPostAdapter;

    public static void initializeData() {
        myDataset.clear();
        myDataset.add(new DataModel("Kawan's Chapathi", "nice, healthy, frozen", "1"));
        myDataset.add(new DataModel("Shredded coconut", "nice, healthy, frozen", "1"));
        myDataset.add(new DataModel("Rice bag 20lb Grain market", "healthy", "1"));
        myDataset.add(new DataModel("Parle-G", "G maane genius", "1"));
    }

    public static ArrayList<DataModel> getDataSet() {
        return myDataset;
    }

    public static ArrayList<DataModel> getMyItems() {
        return new ArrayList<>(myItems.values());
    }

    public static void addToMyItems(String itemName, DataModel d, int position, boolean flag) {
        if (flag) {
            if (myItems.containsKey(itemName)) {
                int q = Integer.parseInt(myItems.get(itemName).getQuantity()) + 1;
                myItems.get(itemName).setQuantity(String.valueOf(q));
                //d.setQuantity(String.valueOf(q));
                mPostAdapter.add(position, myItems.get(itemName));
            } else {
                myItems.put(itemName, new DataModel(itemName, d.getDescription(), "1"));
                mPostAdapter.add(mPostAdapter.getItemCount(), new DataModel(itemName, d.getDescription(), "1"));
            }
        } else {
            myItems.put(itemName, d);
        }
    }

}
