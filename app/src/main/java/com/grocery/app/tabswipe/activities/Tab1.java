package com.grocery.app.tabswipe.activities;

/**
 * Created by SG0222540 on 6/22/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.adapters.Adapter;
import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.utilities.Utilities;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1 extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<DataModel> myDataset = new ArrayList<DataModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);
        FragmentActivity f = getActivity();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerBuyView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(f);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        Adapter mAdapter = new Adapter(Utilities.getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }
}