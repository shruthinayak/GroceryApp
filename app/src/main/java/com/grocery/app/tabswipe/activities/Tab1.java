package com.grocery.app.tabswipe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.adapters.BuyAdapter;
import com.grocery.app.tabswipe.communicate.RetrofitSettings;
import com.grocery.app.tabswipe.communicate.services.JsonService;
import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.utilities.Constants;
import com.grocery.app.tabswipe.utilities.DataManipulationUtilities;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Tab1 extends Fragment  {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private static SwipeRefreshLayout mSwipeRefreshLayout;
    private FragmentActivity f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);
        f = getActivity();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerBuyView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(f);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DataManipulationUtilities.mBuyAdapter = new BuyAdapter(f, DataManipulationUtilities.getDataSet());
        mRecyclerView.setAdapter(DataManipulationUtilities.mBuyAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.activity_main_swipe_refresh_layout);
        final BuyListLoader loader = new BuyListLoader();
        // specify an adapter (see also next example)
         mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loader.load(getApp());
            }
        });


        return v;
    }

    public static class BuyListLoader {

        public void load(RetrofitSettings app) {

            JsonService jsonService = app.getJsonService();
            jsonService.getSomeContent(new Callback<List<DataModel>>() {
                @Override
                public void success(List<DataModel> dataModel, Response response) {
                    for (DataModel d : dataModel) {
                        DataManipulationUtilities.mBuyAdapter.add(d);
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void failure(RetrofitError error) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    private RetrofitSettings getApp() {
        return new RetrofitSettings(Constants.KEY_JSON_SERVER_ID);
    }
}