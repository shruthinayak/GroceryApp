package com.grocery.app.tabswipe.models;

/**
 * Created by SG0222540 on 7/10/2015.
 */
public class SplitwiseTestModel {
        private String client_id;
        private String token;
        private String request_url;
        private String params;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public SplitwiseTestModel(String client_id, String token, String request_url, String params) {
        this.client_id = client_id;
        this.token = token;
        this.request_url = request_url;
        this.params = params;
    }
}
