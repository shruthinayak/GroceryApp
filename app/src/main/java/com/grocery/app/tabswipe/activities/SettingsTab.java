package com.grocery.app.tabswipe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.grocery.app.tabswipe.R;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsTab extends Fragment {
    private ListView mListView;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.settings_tab, container, false);
        mListView = (ListView) mView.findViewById(R.id.lv_settings);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    ParseUser.logOutInBackground(new LogOutCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null)
                                Toast.makeText(mView.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            else {
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        }
                    });

                }
            }
        });

        return mView;
    }
}
