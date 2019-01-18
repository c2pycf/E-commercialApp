package com.example.fang.walmartproject.data.source.remote;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.CategoryItem;
import com.example.fang.walmartproject.homePage.HomepageFragmentPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RemoteDataSource {
    static final String TAG = RemoteDataSource.class.getSimpleName();
    AppController volley;
    //HomepageFragmentPresenter mPresenter;

    public RemoteDataSource() {
        this.volley = AppController.getInstance();
    }

    public void getListRemote(String url, final HomepageFragmentPresenter mPresenter){
        final List<CategoryItem> itemList= new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject categoryObject = new JSONObject(response);
                    Log.d(TAG,categoryObject.toString());
                    JSONArray category = categoryObject.getJSONArray("category");
                    for(int i=0;i<category.length();i++){
                        JSONObject item = category.getJSONObject(i);
                        String cid = item.getString("cid");
                        String cname = item.getString("cname");
                        String cdis = item.getString("cdiscription");
                        String cimageurl = item.getString("cimagerl");
                        CategoryItem categoryItem = new CategoryItem(cid,cname,cdis,cimageurl);
                        itemList.add(categoryItem);


                    }
                    mPresenter.setCetagoryList(itemList);
                    //mView.showList(itemList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.getMessage());
            }
        });

        volley.addToRequestQueue(stringRequest,"Category_list");

        Log.d(TAG,"itemList:" + itemList.size());


    }
}
