package com.grocery.app.tabswipe.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toolbar;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.slidetabs.SlidingTabLayout;
import com.grocery.app.tabswipe.slidetabs.ViewPagerAdapter;
import com.grocery.app.tabswipe.utilities.DataManipulationUtilities;

public class MainActivity extends ActionBarActivity {

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Buy", "Post"};
    int Numboftabs = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataManipulationUtilities.initializeData(MainActivity.this);
        DataManipulationUtilities.initializeBuyerItems(MainActivity.this);
        setContentView(R.layout.activity_main);

        getActionBar().hide();
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


    }
}
