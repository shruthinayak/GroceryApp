package com.grocery.app.tabswipe.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.adapters.BuyDetailViewAdapter;
import com.grocery.app.tabswipe.models.Requestor;
import com.grocery.app.tabswipe.utilities.DataManipulationUtilities;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BuyListDetailViewActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private String itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list_detail_view);
        itemName = getIntent().getStringExtra("itemName");
        customizeActionBar();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerBuyDetailView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DataManipulationUtilities.mBuyerDetailsAdapter = new BuyDetailViewAdapter(new ArrayList<Requestor>(DataManipulationUtilities.getBuyerDetailsForItemName(itemName).getBuyers()));
        mRecyclerView.setAdapter(DataManipulationUtilities.mBuyerDetailsAdapter);

    }

    private void customizeActionBar() {
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.action_bar_title_view, null);
        ((TextView)v.findViewById(R.id.txtTitle)).setText(itemName);
        this.getActionBar().setCustomView(v);

    }
}
