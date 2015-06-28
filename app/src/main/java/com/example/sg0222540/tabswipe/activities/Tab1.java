package com.example.sg0222540.tabswipe.activities;

/**
 * Created by SG0222540 on 6/22/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sg0222540.tabswipe.R;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);
        return v;
    }
}