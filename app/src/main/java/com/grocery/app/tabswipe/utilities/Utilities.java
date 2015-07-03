package com.grocery.app.tabswipe.utilities;

import com.grocery.app.tabswipe.adapters.PostAdapter;
import com.grocery.app.tabswipe.models.DataModel;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static void addToMyItems(String itemName, DataModel d, int position) {
        if (myItems.containsKey(itemName)) {
            int q = Integer.parseInt(myItems.get(itemName).getQuantity()) + 1;
            myItems.get(itemName).setQuantity(String.valueOf(q));
            mPostAdapter.add(myItems.get(itemName));
        } else {
            myItems.put(itemName, new DataModel(itemName, d.getDescription(), "1"));
            mPostAdapter.add(new DataModel(itemName, d.getDescription(), "1"));
        }
    }
}
   /* public static void decrementInTotalDataSet(String itemName, DataModel d, int position, boolean flag) {
            for(DataModel d: myDataset){
                if(d.getItemName().equals(itemName)){
                    d.setQuantity(Integer.parseInt(d.getQuantity())-1);
                }
            }
    }*/
