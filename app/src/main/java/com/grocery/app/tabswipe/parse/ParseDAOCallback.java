package com.grocery.app.tabswipe.parse;

/**
 * Created by upendra on 7/15/15.
 */
public interface ParseDAOCallback<T> {
    void onDataAvailable(T data);
}
