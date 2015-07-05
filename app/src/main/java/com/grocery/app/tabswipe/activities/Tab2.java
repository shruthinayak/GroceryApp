package com.grocery.app.tabswipe.activities;

/**
 * Created by SG0222540 on 6/22/2015.
 */

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.adapters.PostAdapter;
import com.grocery.app.tabswipe.models.DataModel;
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

        //function to add items
        Button btn_add = (Button) v.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder theDialogue = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View layout = inflater.inflate(R.layout.add_layout, null);
                final EditText etxt_item_name = (EditText) layout.findViewById(R.id.etxt_item_name);
                final EditText etxt_item_desc = (EditText) layout.findViewById(R.id.etxt_item_desc);
                final EditText etxt_item_qty = (EditText) layout.findViewById(R.id.etxt_item_qty);
                theDialogue.setView(layout);
                theDialogue.setTitle("Add Item");
                theDialogue.setMessage("Enter Details of the product");

                theDialogue.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String item_name = etxt_item_name.getText().toString();
                        String item_desc = etxt_item_desc.getText().toString();
                        String item_qty = etxt_item_qty.getText().toString();
                        DataModel add_data = new DataModel(item_name,item_desc,item_qty);
                        Utilities.addToMyItems(item_name,add_data,0);
                        Toast.makeText(getActivity(), "Clicked OK", Toast.LENGTH_SHORT).show();
                    }
                });
                theDialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Clicked Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                theDialogue.show();
            }
        });
        return v;
    }
}
