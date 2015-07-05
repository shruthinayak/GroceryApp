package com.grocery.app.tabswipe.adapters;

/**
 * Created by SG0222540 on 6/28/2015.
 */
import java.util.ArrayList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.utilities.Utilities;

public class BuyAdapter extends RecyclerView.Adapter<BuyAdapter.ViewHolder> {
    private ArrayList<DataModel> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView itemName;
        public TextView description;
        public TextView txtQuantity;
        public ImageButton btnPlus;
        public ImageButton btnMinus;

        public ViewHolder(View v) {
            super(v);
            itemName = (TextView) v.findViewById(R.id.txtItemName);
            description = (TextView) v.findViewById(R.id.txtDescription);
            txtQuantity = (TextView) v.findViewById(R.id.txtQuantity);
            btnPlus = (ImageButton) v.findViewById(R.id.btnPlus);
            btnMinus = (ImageButton) v.findViewById(R.id.btnMinus);
            btnMinus.setVisibility(View.GONE);
            btnPlus.setVisibility(View.VISIBLE);
        }
    }

    public void add(int position, DataModel item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(DataModel item) {
        int position = mDataset.indexOf(item);
        int q = Integer.parseInt(item.getQuantity());
        if(q>=2){
            mDataset.get(position).setQuantity(String.valueOf(q));
        } else{
            mDataset.remove(position);
        }
        notifyDataSetChanged();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BuyAdapter(ArrayList<DataModel> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BuyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_leg_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String itemName = mDataset.get(position).getItemName();
        holder.itemName.setText(itemName);
        holder.description.setText(mDataset.get(position).getDescription());
        holder.txtQuantity.setText(mDataset.get(position).getQuantity());
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(holder.txtQuantity.getText().toString());
                holder.txtQuantity.setText(String.valueOf(quantity+1));
                mDataset.get(position).setQuantity(String.valueOf(quantity + 1));
                Utilities.addToMyItems(itemName, mDataset.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
