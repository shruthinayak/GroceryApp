package com.grocery.app.tabswipe.activities;

/**
 * Created by SG0222540 on 6/22/2015.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.adapters.PostAdapter;
import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.utilities.DataManipulationUtilities;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab2 extends Fragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private AlertDialog.Builder theDialogue;
    private AutoCompleteTextView edtItemName;
    private EditText edtItemDesc;
    private EditText edtItemQty;
    private Button btnOk;

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
        DataManipulationUtilities.mPostAdapter = new PostAdapter(DataManipulationUtilities.getMyItems());
        mRecyclerView.setAdapter(DataManipulationUtilities.mPostAdapter);
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                TextView t = (TextView) viewHolder.itemView.findViewById(R.id.txtItemName);
                DataManipulationUtilities.deleteFromMyItems(t.getText().toString(), viewHolder.getOldPosition(), true);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        //function to add items
        Button btnAdd = (Button) v.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theDialogue = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View layout = inflater.inflate(R.layout.add_layout, null);
                edtItemName = (AutoCompleteTextView) layout.findViewById(R.id.etxt_item_name);
                edtItemDesc = (EditText) layout.findViewById(R.id.etxt_item_desc);
                edtItemQty = (EditText) layout.findViewById(R.id.etxt_item_qty);
                edtItemName.addTextChangedListener(mDateEntryWatcher);
                edtItemDesc.addTextChangedListener(mDateEntryWatcher);
                edtItemQty.addTextChangedListener(mDateEntryWatcher);
                ArrayList<String> names = new ArrayList<String>();
                for (String d : DataManipulationUtilities.myDataset.keySet()) {
                    names.add(d);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, names);
                edtItemName.setAdapter(adapter);
                edtItemName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        String selection = (String) adapterView.getItemAtPosition(position);
                        DataModel d = DataManipulationUtilities.myDataset.get(selection);
                        edtItemDesc.setText(d.getDescription());
                        edtItemDesc.setEnabled(false);
                    }
                });
                theDialogue.setView(layout);
                theDialogue.setTitle("Add Item");
                theDialogue.setMessage("Enter Details of the product");

                theDialogue.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String itemName = edtItemName.getText().toString();
                        String itemDesc = edtItemDesc.getText().toString();
                        String itemQty = edtItemQty.getText().toString();
                        if (!itemName.isEmpty() && !itemDesc.isEmpty() && !itemQty.isEmpty()) {
                            DataModel add_data = new DataModel(itemName, itemDesc, itemQty);
                            DataManipulationUtilities.addToMyItems(itemName, add_data);
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
                AlertDialog alertDialog = theDialogue.create();
                alertDialog.show();
                btnOk = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                btnOk.setEnabled(false);
            }
        });

        Button btnSubmit = (Button) v.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theDialogue = new AlertDialog.Builder(getActivity());
                theDialogue.setTitle("SUBMIT");
                theDialogue.setMessage("Are you sure?");

                theDialogue.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(getActivity(), "Submitted Successfully", Toast.LENGTH_SHORT).show();

                    }
                });
                theDialogue.setNegativeButton("Review", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Clicked Review", Toast.LENGTH_SHORT).show();
                    }
                });
                theDialogue.show();

            }
        });
        return v;
    }

    private TextWatcher mDateEntryWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(edtItemName.length()!=0 && edtItemDesc.length()!=0 && edtItemQty.length()!=0){
                btnOk.setEnabled(true);
            }
            else {
                btnOk.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    };
}
