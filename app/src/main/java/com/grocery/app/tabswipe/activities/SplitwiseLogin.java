package com.grocery.app.tabswipe.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.communicate.RetrofitSettings;
import com.grocery.app.tabswipe.communicate.services.JsonService;
import com.grocery.app.tabswipe.communicate.services.SplitwiseTestService;
import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.models.SplitwiseTestModel;
import com.grocery.app.tabswipe.utilities.Constants;
import com.grocery.app.tabswipe.utilities.DataManipulationUtilities;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplitwiseLogin extends ActionBarActivity {

    private Button btnSplitTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitwise_login);
        btnSplitTest = (Button) findViewById(R.id.btnSplitTest);
        btnSplitTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitSettings app = getApp();
                SplitwiseTestService splitwiseService = app.getSplitwiseTestServiceService();
                splitwiseService.getSomeContent(new Callback<SplitwiseTestModel>() {
                    @Override
                    public void success(SplitwiseTestModel spModel, Response response) {
                        Toast.makeText(getApplicationContext(), spModel.getToken(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), "Nooooooo", Toast.LENGTH_LONG).show();
                    }

                });

            }
        });
    }


    private RetrofitSettings getApp() {
        return new RetrofitSettings(Constants.KEY_SPLITWISE_TEST_ID);
    }
}
