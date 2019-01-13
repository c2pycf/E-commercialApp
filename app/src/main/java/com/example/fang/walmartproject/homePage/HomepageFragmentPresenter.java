package com.example.fang.walmartproject.homePage;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.CategoryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomepageFragmentPresenter implements HomePageContract.ShopPresenter {
    HomePageContract.ShopView mView;
    AppController volley;
    static private final String TAG = HomepageFragmentPresenter.class.getSimpleName();

    public HomepageFragmentPresenter(HomePageFragment context) {
        this.mView = context;
        volley = AppController.getInstance();
    }

    @Override
    public void getCategoryList() {
        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=362c84de326feb551cf0614d0d78aa74&user_id=1525";
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
                    mView.showList(itemList);
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
    }

    @Override
    public void onItemClickHandled(String cid) {
        mView.showSubCategory(cid);
    }
}
