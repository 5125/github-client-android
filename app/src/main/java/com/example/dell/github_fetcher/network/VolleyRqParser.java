package com.example.dell.github_fetcher.network;

import android.content.Context;
import android.util.Log;
import android.view.textclassifier.TextLinks;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class VolleyRqParser {

    final String contentType = "application/json; charset=utf-8";
    Context context;
    RequestQueue requestQueue;
    String jsonresponse;

    private Map<String, String> header;

    public VolleyRqParser(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
        header = new HashMap<>();
        header.put("Authorization","Basic 123:123");

    }

    public void addHeader(String key, String value) {
        header.put(key, value);
    }

    public void executeRequest(int method,String destUrl, final VolleyCallback callback) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, destUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonresponse = response;
             //   Log.e("RES", " res::" + jsonresponse);
                callback.getResponse(jsonresponse);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return header;
            }
        };
        requestQueue.add(stringRequest);

    }

    public interface VolleyCallback {
        public void getResponse(String response);
    }

}