package com.grocery.app.tabswipe.parse;

import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.models.GroceryItem;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by upendra on 7/15/15.
 */
public class ParseDAO {

    public void getItemSuperSet(final ParseDAOCallback<ArrayList<GroceryItem>> callback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(GroceryItem.class.getSimpleName());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                GroceryItem gi = null;
                ArrayList<GroceryItem> items = new ArrayList<GroceryItem>(list.size());
                for (ParseObject po: list) {
                    gi = new GroceryItem();
                    gi.setItemId(po.getString(GroceryItem.fieldKeys[0]));
                    gi.setItemName(po.getString(GroceryItem.fieldKeys[1]));
                    gi.setQuantity(po.getInt(GroceryItem.fieldKeys[2]));
                    gi.setDescription(po.getString(GroceryItem.fieldKeys[3]));
                    gi.setUnitPrice(po.getDouble(GroceryItem.fieldKeys[4]));
                    items.add(gi);
                }
                callback.onDataAvailable(items);
            }
        });
    }

    public void getBuyItems(final ParseDAOCallback<ArrayList<DataModel>> callback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(DataModel.class.getSimpleName());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                ArrayList<DataModel> items = new ArrayList<DataModel>(list.size());
                for (ParseObject po: list) {
                    items.add(new DataModel(po.getString("item_name"), po.getString("item_desc"),
                            po.getString("quantity")));
                }
                callback.onDataAvailable(items);
            }
        });
    }
}
