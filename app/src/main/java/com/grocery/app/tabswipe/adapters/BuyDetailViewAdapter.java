package com.grocery.app.tabswipe.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.grocery.app.tabswipe.R;
import com.grocery.app.tabswipe.models.Requestor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SG0222540 on 7/10/2015.
 */
public class BuyDetailViewAdapter extends RecyclerView.Adapter<BuyDetailViewAdapter.ViewHolder> {
    List<Requestor> buyersList;

    public BuyDetailViewAdapter(ArrayList<Requestor> buyersList) {
        this.buyersList = buyersList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuantity;
        TextView buyerName;
        Switch lock;

        public ViewHolder(View v) {
            super(v);
            buyerName = (TextView) v.findViewById(R.id.txtBuyerName);
            txtQuantity = (TextView) v.findViewById(R.id.txtQuantity);
            lock = (Switch) v.findViewById(R.id.switchLock);
        }
    }
    @Override
    public BuyDetailViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_leg_detail_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BuyDetailViewAdapter.ViewHolder holder, int position) {
        Requestor requestor = buyersList.get(position);
        holder.buyerName.setText(requestor.getUname());
        holder.txtQuantity.setText(requestor.getQty());
        //holder.lock.set

    }

    @Override
    public int getItemCount() {
        return buyersList.size();
    }
}
