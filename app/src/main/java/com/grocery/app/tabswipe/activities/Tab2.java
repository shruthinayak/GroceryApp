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
import com.grocery.app.tabswipe.adapters.PostAdapter;
import com.grocery.app.tabswipe.utilities.Utilities;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab2 extends Fragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_2, container, false);
        FragmentActivity f = getActivity();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerPostView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(f);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        Utilities.mPostAdapter = new PostAdapter(Utilities.getMyItems());
        mRecyclerView.setAdapter(Utilities.mPostAdapter);
        return v;
    }
}
