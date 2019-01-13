package com.example.fang.walmartproject.productCategory;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.data.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryPresenter implements ProductCategoryContract.ProductListPresenter {
    ProductCategoryContract.ProductListView mView;
    AppController volley;

    static final String TAG = ProductCategoryPresenter.class.getSimpleName();

    public ProductCategoryPresenter(ProductCategoryFragment context) {
        this.mView = context;
        this.volley = AppController.getInstance();
    }

    @Override
    public void getProducts(String cid, String scid) {
        String url ="http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=107&scid=205&api_key=362c84de326feb551cf0614d0d78aa74&user_id=1525";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    List<Product> itemList = new ArrayList<>();
                    JSONObject products = new JSONObject(response);
                    Log.d(TAG,products.toString());
                    JSONArray productList = products.getJSONArray("products");
                    for(int i=0;i<productList.length();i++){
                        JSONObject productJson = productList.getJSONObject(i);
                        String pid = productJson.getString("id");
                        String pname = productJson.getString("pname");
                        String quantity = productJson.getString("quantity");
                        String prize = productJson.getString( "prize");
                        String disc = productJson.getString("discription");
                        String image = productJson.getString("image");

                        Product newProduct = new Product(pid,pname,quantity,prize,disc,image);
                        itemList.add(newProduct);
                    }
                    mView.showProductList(itemList);
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
        volley.addToRequestQueue(stringRequest,"ProductList");
    }

    @Override
    public void onProductClicked(Product product) {
        mView.showProductDetail(product);
    }
}
