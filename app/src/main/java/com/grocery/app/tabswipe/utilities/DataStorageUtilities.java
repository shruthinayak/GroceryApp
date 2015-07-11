package com.grocery.app.tabswipe.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.*;
import android.provider.SyncStateContract;

/**
 * Created by SG0222540 on 7/10/2015.
 */
public class DataStorageUtilities {
    SharedPreferences userPreferences;
    Context ctx;

    public DataStorageUtilities(SharedPreferences preferences, Context ctx) {
        this.userPreferences = getUserPreferences();
        this.ctx = ctx;
    }

    public SharedPreferences getUserPreferences() {
        if (userPreferences == null) {
            return userPreferences = ctx.getSharedPreferences("user-data", Context.MODE_PRIVATE);
        } else {
            return userPreferences;
        }
    }

    public void saveToPreferences(String key, String value){
        SharedPreferences userDetails = getUserPreferences();
        Editor edit = userDetails.edit();
        edit.clear();
        edit.putString(key, value);
        edit.commit();
    }

    public String getFromPreferences(String key){
        SharedPreferences userDetails = ctx.getSharedPreferences("user-data", Context.MODE_PRIVATE);
        return userDetails.getString(key, getDefaultValue(key));
    }
    public String getDefaultValue(String key){
        String keyName = "KEY_"+key;
        try {
            return String.valueOf(Constants.class.getDeclaredField(keyName));

        } catch (NoSuchFieldException e) {
            return "";
        }
    }
}
