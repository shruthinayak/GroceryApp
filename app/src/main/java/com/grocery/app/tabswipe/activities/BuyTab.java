package com.grocery.app.tabswipe.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.adapters.BuyAdapter;
import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.parse.ParseDAO;
import com.grocery.app.tabswipe.parse.ParseDAOCallback;

import java.util.ArrayList;

public class BuyTab extends Fragment  {

    private RecyclerView mRecyclerView;
    private BuyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.buy_tab, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerBuyView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BuyAdapter(getActivity(), new ArrayList<DataModel>());
        mRecyclerView.setAdapter(mAdapter);

        ParseDAO pd = new ParseDAO();
        pd.getBuyItems(new ParseDAOCallback<ArrayList<DataModel>>() {
            @Override
            public void onDataAvailable(ArrayList<DataModel> data) {
                loadData(data);
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 refreshBuyList();
             }
         });
        return v;
    }

    private void refreshBuyList() {
        ParseDAO pd = new ParseDAO();
        pd.getBuyItems(new ParseDAOCallback<ArrayList<DataModel>>() {
            @Override
            public void onDataAvailable(ArrayList<DataModel> data) {
                loadData(data);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadData(ArrayList<DataModel> data) {
        mAdapter.clearItems();
        mAdapter.addItems(data);
    }
    /*
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
    */
}