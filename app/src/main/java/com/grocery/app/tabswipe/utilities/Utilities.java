package com.grocery.app.tabswipe.utilities;

import com.grocery.app.tabswipe.models.DataModel;

import java.util.ArrayList;

/**
 * Created by SG0222540 on 6/28/2015.
 */
public class Utilities {
    static ArrayList<DataModel> myDataset = new ArrayList<DataModel>();
    public static void initializeData(){
        myDataset.clear();
        myDataset.add(new DataModel("Kawan's Chapathi", "nice, healthy, frozen", "1"));
        myDataset.add(new DataModel("Shredded coconut", "nice, healthy, frozen", "1"));
        myDataset.add(new DataModel("Rice bag 20lb Grain market", "healthy", "1"));
        myDataset.add(new DataModel("Parle-G", "G maane genius", "1"));
    }
    public static ArrayList<DataModel> getDataSet(){
        return myDataset;
    }

    public static void addToMyItems(String itemName, int i) {

    }
}
