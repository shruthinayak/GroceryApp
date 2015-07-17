package com.grocery.app.tabswipe.adapters;

/**
 * Created by SG0222540 on 6/28/2015.
 */
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.activities.BuyListDetailViewActivity;
import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.utilities.DataManipulationUtilities;

public class BuyAdapter extends RecyclerView.Adapter<BuyAdapter.ViewHolder>  implements View.OnClickListener {
    private ArrayList<DataModel> mDataset;
    private Context ctx;
    private String itemId;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView itemName;
        public TextView description;
        public TextView txtQuantity;
        public ImageButton btnPlus;
        public ImageButton btnMinus;
        public RelativeLayout lytOuterBuyLeg;
        public View indicateLockBoughtView;

        public ViewHolder(View v) {
            super(v);
            itemName = (TextView) v.findViewById(R.id.txtItemName);
            description = (TextView) v.findViewById(R.id.txtDescription);
            txtQuantity = (TextView) v.findViewById(R.id.txtQuantity);
            btnPlus = (ImageButton) v.findViewById(R.id.btnPlus);
            btnMinus = (ImageButton) v.findViewById(R.id.btnMinus);
            lytOuterBuyLeg = (RelativeLayout) v.findViewById(R.id.lytOuterBuyLeg);
            indicateLockBoughtView =  v.findViewById(R.id.indicator);
            btnMinus.setVisibility(View.GONE);
            btnPlus.setVisibility(View.VISIBLE);
        }

    }

    public void add(DataModel item) {
        if (mDataset.contains(item)) {
            int position = mDataset.indexOf(item);
            mDataset.get(position).setQuantity(item.getQuantity());
            notifyDataSetChanged();
        } else {
            mDataset.add(mDataset.size(), item);
            notifyItemInserted(mDataset.size());
        }
    }

    public void remove(DataModel item) {
        int position = mDataset.indexOf(item);
        int q = Integer.parseInt(mDataset.get(position).getQuantity());
        if(q<1){
            mDataset.remove(position);
        }
        notifyDataSetChanged();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BuyAdapter(Context context, ArrayList<DataModel> myDataset) {
        mDataset = myDataset;
        ctx = context;
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
       // final String itemName = "Haldirams";
        itemId = mDataset.get(position).getItm_id();
        holder.itemName.setText(itemName);
        holder.description.setText(mDataset.get(position).getItm_desc());
        holder.itemName.setOnClickListener(this);
        holder.description.setOnClickListener(this);
        holder.txtQuantity.setText(mDataset.get(position).getQuantity());
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(holder.txtQuantity.getText().toString());
                holder.txtQuantity.setText(String.valueOf(quantity + 1));
                mDataset.get(position).setQuantity(String.valueOf(quantity + 1));
                DataManipulationUtilities.addToMyItems(itemName, mDataset.get(position));
            }
        });
        holder.indicateLockBoughtView.setVisibility(View.GONE);
         if(DataManipulationUtilities.areAllItemsLocked(itemName)){
                holder.indicateLockBoughtView.setBackgroundColor(ctx.getResources().getColor(R.color.ColorLockRed));
                holder.indicateLockBoughtView.setVisibility(View.VISIBLE);
        }
        if(DataManipulationUtilities.areAllItemsBought(itemName)){
            holder.indicateLockBoughtView.setBackgroundColor(ctx.getResources().getColor(R.color.ColorLockGreen));
            holder.indicateLockBoughtView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.txtDescription:
                break;
            case R.id.txtItemName:
                TextView t = (TextView) view;
                Intent intent = new Intent(ctx, BuyListDetailViewActivity.class);
                //Send item ID of the item pressed in bundle.
                intent.putExtra("itemName", t.getText().toString());
                ctx.startActivity(intent);
                break;

        }
    }
}
