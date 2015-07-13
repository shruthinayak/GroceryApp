package com.grocery.app.tabswipe.utilities;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.adapters.BuyAdapter;
import com.grocery.app.tabswipe.adapters.BuyDetailViewAdapter;
import com.grocery.app.tabswipe.adapters.PostAdapter;
import com.grocery.app.tabswipe.models.RequestorDetails;
import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.models.Requestor;

import org.parceler.apache.commons.lang.StringUtils;
import org.parceler.guava.io.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataManipulationUtilities {
    public static HashMap<String, DataModel> myDataset = new HashMap<String, DataModel>();
    static HashMap<String, DataModel> myItems = new HashMap<String, DataModel>();
    static HashMap<String, RequestorDetails> requestorDetails = new HashMap<>();
    public static PostAdapter mPostAdapter;
    public static BuyAdapter mBuyAdapter;
    public static BuyDetailViewAdapter mBuyerDetailsAdapter;
    //item-id, buyerslist
    public static HashMap<String, RequestorDetails> buyerDetails = new HashMap<>();
    private static Gson gson = new Gson();


    public static void initializeBuyerItems(Context ctx) {
        /*requestors.add(new Requestor("Shruthi Nayak", "usr100", "rn.shruthi@gmail.com", "1", false, true));
        requestors.add(new Requestor("Jithendra", "usr101", "jithendra.jayaram@gmail.com", "1", false, true));
        buyerDetails.put("Haldirams", new RequestorDetails("100", "itm001", "2", requestors));*/
       /* for(String jsonName: myDataset.keySet()){
            String json = getStringJson(jsonName+".json");

            RequestorDetails rd = gson.fromJson(json, RequestorDetails.class);
            buyerDetails.put(rd.getItm_name(), rd);
        }*/
        R.raw r = new R.raw();
        Field[] fields = R.raw.class.getFields();
        for(Field f: fields){
            if(!f.getName().contains("buy") && !f.getName().contains("post")){
                f.setAccessible(true);
                try {
                    int id = (Integer) f.get(r);
                    String json = getStringJson(ctx.getResources().openRawResource(id));
                    RequestorDetails rd = gson.fromJson(json, RequestorDetails.class);
                    buyerDetails.put(rd.getItm_name(), rd);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public static void initializeData(Context ctx) {
        String json = getStringJson(ctx.getResources().openRawResource(R.raw.buy));
        List<DataModel> dm = gson.fromJson(json, DataModel.getListType());
        for(DataModel d: dm){
            myDataset.put(d.getItemName(), d);
        }
    }

    private static String getStringJson(InputStream io) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(io));
        try {
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = bf.readLine()) != null) {
                out.append(line);
                out.append(" ");
            }
            bf.close();
            return out.toString();   //Prints the string content read from input stream
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
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
            myItems.put(itemName, new DataModel(itemName, d.getItm_desc(), "1"));
            mPostAdapter.add(new DataModel(itemName, d.getItm_desc(), "1"));
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

    public static void deleteFromMyItems(String itemName, int pos, boolean deleteFlag) {
        int q = Integer.parseInt(myItems.get(itemName).getQuantity());
        int f = Integer.parseInt(myDataset.get(itemName).getQuantity());

        if (deleteFlag) {
            myDataset.get(itemName).setQuantity(String.valueOf(f - q));
            myItems.get(itemName).setQuantity("0");
            mPostAdapter.remove(myItems.get(itemName));
            mPostAdapter.notifyItemRemoved(pos);
            myItems.remove(itemName);
        } else {
            myDataset.get(itemName).setQuantity(String.valueOf(f - 1));
            myItems.get(itemName).setQuantity(String.valueOf(q - 1));
            mPostAdapter.notifyDataSetChanged();
        }
        mBuyAdapter.notifyDataSetChanged();
    }

    public static RequestorDetails getBuyerDetailsForItemName(String itemName) {
        if(buyerDetails.containsKey(itemName))
        return buyerDetails.get(itemName);
        else return null;
    }

    public static boolean areAllItemsLocked(String itemName) {
        if(buyerDetails.containsKey(itemName))
        return buyerDetails.get(itemName).areAllItemsLocked();
        else return false;
    }
    public static boolean areAllItemsBought(String itemName) {
        if(buyerDetails.containsKey(itemName))
            return buyerDetails.get(itemName).areAllItemsBought();
        else return true;
    }
}

