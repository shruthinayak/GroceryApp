package com.grocery.app.tabswipe.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.adapters.BuyAdapter;
import com.grocery.app.tabswipe.adapters.PostAdapter;
import com.grocery.app.tabswipe.communicate.JsonService;
import com.grocery.app.tabswipe.communicate.RetrofitSettings;
import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.utilities.Utilities;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Tab1 extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private static SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);
        FragmentActivity f = getActivity();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerBuyView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.activity_main_swipe_refresh_layout);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(f);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final BuyListLoader loader = new BuyListLoader();
        // specify an adapter (see also next example)
        Utilities.mBuyAdapter = new BuyAdapter(Utilities.getDataSet());
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loader.load(getApp());
            }
        });

        mRecyclerView.setAdapter(Utilities.mBuyAdapter);

        return v;
    }
    public static class BuyListLoader {

        public void load(RetrofitSettings app) {

            JsonService jsonService = app.getJsonService();
            jsonService.getSomeContent(new Callback<DataModel>() {
                @Override
                public void success(DataModel dataModel, Response response) {
                    Utilities.mBuyAdapter.add(Utilities.mBuyAdapter.getItemCount(),dataModel);
                    mSwipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }
    }
    private RetrofitSettings getApp() {
        return new RetrofitSettings();
    }
}