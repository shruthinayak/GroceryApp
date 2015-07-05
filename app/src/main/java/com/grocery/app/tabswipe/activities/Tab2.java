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
        Button btnAdd = (Button) v.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder theDialogue = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View layout = inflater.inflate(R.layout.add_layout, null);
                final EditText edtItemName = (EditText) layout.findViewById(R.id.etxt_item_name);
                final EditText edtItemDesc = (EditText) layout.findViewById(R.id.etxt_item_desc);
                final EditText edtItemQty = (EditText) layout.findViewById(R.id.etxt_item_qty);
                theDialogue.setView(layout);
                theDialogue.setTitle("Add Item");
                theDialogue.setMessage("Enter Details of the product");

                theDialogue.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String itemName = edtItemName.getText().toString();
                        String itemDesc = edtItemDesc.getText().toString();
                        String itemQty = edtItemQty.getText().toString();
                        if(!itemName.isEmpty() && !itemDesc.isEmpty() && !itemQty.isEmpty()) {
                            DataModel add_data = new DataModel(itemName, itemDesc, itemQty);
                            Utilities.addToMyItems(itemName, add_data);
                            Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                        }
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
