
package com.grocery.app.tabswipe.communicate;

import android.app.Application;

import com.grocery.app.tabswipe.communicate.JsonService;

import retrofit.RestAdapter;


/**
 * Created by SG0222540 on 7/5/2015.
 */

public class RetrofitSettings extends Application {
    private static final String JSON_SERVER = "http://192.168.2.15:9000/";
    private JsonService jsonService;

    public RetrofitSettings() {
        // This uses 10.0.2.2 which is how the emulator can address the host operating system.
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).
                setEndpoint(JSON_SERVER).build();
        jsonService = restAdapter.create(JsonService.class);
    }

    public JsonService getJsonService() {
        return jsonService;
    }
}

