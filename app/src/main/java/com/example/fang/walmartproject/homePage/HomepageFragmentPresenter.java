package com.example.fang.walmartproject.homePage;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.CategoryItem;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.source.TopSeller;
import com.example.fang.walmartproject.data.source.UserDataSource;
import com.example.fang.walmartproject.data.source.UserRepository;
import com.example.fang.walmartproject.data.source.remote.RemoteDataSource;

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
    UserDataSource reprository;
    RemoteDataSource remoteDataSource;

    public HomepageFragmentPresenter(HomePageFragment context) {
        this.mView = context;
        volley = AppController.getInstance();
        reprository = new UserRepository(context.getContext());
        remoteDataSource = new RemoteDataSource();
    }

    @Override
    public void getCategoryList() {
        String api = "fbdba5e879a51ae7436280d18647e9fe";
        String id = "1525";
        if(reprository.getUser()!=null) {
            api = reprository.getUser().getUserAppApiKey();
            id = reprository.getUser().getUserId();
            Log.d(TAG,"api= " + api + " id=" + id);


        }
        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=" + api + "&user_id="+id;
        List<CategoryItem> itemList;
        remoteDataSource.getListRemote(url,this);
//        mView.showList(itemList);
//        final List<CategoryItem> itemList= new ArrayList<>();
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject categoryObject = new JSONObject(response);
//                    Log.d(TAG,categoryObject.toString());
//                    JSONArray category = categoryObject.getJSONArray("category");
//                    for(int i=0;i<category.length();i++){
//                        JSONObject item = category.getJSONObject(i);
//                        String cid = item.getString("cid");
//                        String cname = item.getString("cname");
//                        String cdis = item.getString("cdiscription");
//                        String cimageurl = item.getString("cimagerl");
//                        CategoryItem categoryItem = new CategoryItem(cid,cname,cdis,cimageurl);
//                        itemList.add(categoryItem);
//                    }
//                    mView.showList(itemList);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG,error.getMessage());
//            }
//        });
//
//        volley.addToRequestQueue(stringRequest,"Category_list");
    }

    @Override
    public void onItemClickHandled(String cid) {
        mView.showSubCategory(cid);
    }

    @Override
    public void getTopSells() {
        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_top_sellers.php?";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject sellers = new JSONObject(response);
                    List<TopSeller> sellerList = new ArrayList<>();
                    JSONArray sellerArray = sellers.getJSONArray( "Top sellers");
                    for(int i=0;i<sellerArray.length();i++){
                        JSONObject seller = sellerArray.getJSONObject(i);
                        String id = seller.getString("id");
                        String name = seller.getString("sellername");
                        String deal = seller.getString("sellerdeal");
                        String rating = seller.getString("sellerrating");
                        String url = seller.getString("sellerlogo");
                        TopSeller newSeller = new TopSeller(id,name,deal,rating,url);
                        sellerList.add(newSeller);

                    }
                    mView.setTopSells(sellerList);
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

        volley.addToRequestQueue(stringRequest,"Topsellers");

    }

    @Override
    public void setCetagoryList(List<CategoryItem> itemList) {
        mView.showList(itemList);
    }


}
