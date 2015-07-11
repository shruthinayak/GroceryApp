package com.grocery.app.tabswipe.communicate.services;

import com.grocery.app.tabswipe.models.DataModel;
import com.grocery.app.tabswipe.models.SplitwiseTestModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by SG0222540 on 7/10/2015.
 */
public interface SplitwiseTestService {
    @GET("/")
    public void getSomeContent(Callback<SplitwiseTestModel> d);
}
