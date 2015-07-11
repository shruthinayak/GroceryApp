package com.grocery.app.tabswipe.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.adapters.BuyDetailViewAdapter;
import com.grocery.app.tabswipe.models.Requestor;
import com.grocery.app.tabswipe.utilities.DataManipulationUtilities;

import java.util.ArrayList;

public class BuyListDetailViewActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buy_list_detail_view);
        String itemId = "100"; //grab from intent
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerBuyDetailView);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DataManipulationUtilities.mBuyerDetailsAdapter = new BuyDetailViewAdapter(new ArrayList<Requestor>(DataManipulationUtilities.getBuyerDetailsForItemId(itemId).getBuyers()));
        mRecyclerView.setAdapter(DataManipulationUtilities.mBuyerDetailsAdapter);
        getActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buy_list_detail_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
