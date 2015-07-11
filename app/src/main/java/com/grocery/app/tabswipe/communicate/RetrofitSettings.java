
package com.grocery.app.tabswipe.communicate;

import android.app.Application;

import com.grocery.app.tabswipe.communicate.services.JsonService;
import com.grocery.app.tabswipe.communicate.services.SplitwiseTestService;
import com.grocery.app.tabswipe.utilities.Constants;

import retrofit.RestAdapter;



/**
 * Created by SG0222540 on 7/5/2015.
 */

public class RetrofitSettings extends Application {

    private SplitwiseTestService splitwiseTestService;
    private JsonService jsonService;

    public RetrofitSettings(int key) {
        RestAdapter restAdapter;
        // This uses 10.0.2.2 which is how the emulator can address the host operating system.
        switch(key){
            case Constants.KEY_JSON_SERVER_ID:
                 restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).
                        setEndpoint(Constants.URL_JSON_SERVER).build();
                jsonService = restAdapter.create(JsonService.class);
                break;
            case Constants.KEY_SPLITWISE_TEST_ID:
                 restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).
                        setEndpoint(Constants.URL_SPLITWISE_TEST).build();
                splitwiseTestService = restAdapter.create(SplitwiseTestService.class);
                break;
        }

    }

    public JsonService getJsonService() {
        return jsonService;
    }
    public SplitwiseTestService getSplitwiseTestServiceService() {
        return splitwiseTestService;
    }
}

