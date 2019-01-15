package com.example.fang.walmartproject.subcategory;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.CategoryItem;
import com.example.fang.walmartproject.data.SubCategoryItem;
import com.example.fang.walmartproject.data.source.UserDataSource;
import com.example.fang.walmartproject.data.source.UserRepository;
import com.example.fang.walmartproject.homePage.HomepageFragmentPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubCategoryPresenter implements SubCategoryContract.ShopPresenter{
    SubCategoryContract.ShopView mView;
    AppController volley;
    String cid;
    UserDataSource reporsitory;
    static private final String TAG = SubCategoryPresenter.class.getSimpleName();

    public SubCategoryPresenter(SubCategoryFragment context, String cid) {
        this.mView = context;
        volley = AppController.getInstance();
        this.cid = cid;
        reporsitory = new UserRepository(context.getContext());
    }

    @Override
    public void getCategoryList() {
        String api = "fbdba5e879a51ae7436280d18647e9fe";
        String id = "1525";
        if(reporsitory.getUser()!=null) {

            api = reporsitory.getUser().getUserAppApiKey();
            id = reporsitory.getUser().getUserId();
            Log.d(TAG,"api= " + api + " id=" + id);
        }
        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id="+cid+"&api_key="+api+"&user_id="+id;
        final List<SubCategoryItem> itemList= new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject categoryObject = new JSONObject(response);
                    Log.d(TAG,categoryObject.toString());
                    JSONArray category = categoryObject.getJSONArray("subcategory");
                    for(int i=0;i<category.length();i++){
                        JSONObject item = category.getJSONObject(i);
                        String cid = item.getString("scid");
                        String cname = item.getString("scname");
                        String cdis = item.getString("scdiscription");
                        String cimageurl = item.getString("scimageurl");
                        SubCategoryItem categoryItem = new SubCategoryItem(cid,cname,cdis,cimageurl);
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
        })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("Id","107");
//                params.put("api_key","362c84de326feb551cf0614d0d78aa74");
//                params.put("user_id","1525");
//                return params;
//            }
//        }

                ;

        volley.addToRequestQueue(stringRequest,"SubCategory_list");
    }

    @Override
    public void onSubCategoryItemClicked(String cid, String scid) {
        mView.showProductList(cid,scid);
    }
}
