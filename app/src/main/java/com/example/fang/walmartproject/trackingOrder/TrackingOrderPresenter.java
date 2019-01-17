package com.example.fang.walmartproject.trackingOrder;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrackingOrderPresenter implements TrackingOrderContract.TrackingOrderPresenter {
    private TrackingOrderContract.TrackingOrderView mView;
    private AppController volley;
    static private final String TAG = TrackingOrderPresenter.class.getSimpleName();

    public TrackingOrderPresenter(TrackingOrderFragment fragment) {
        this.mView = fragment;
        volley = AppController.getInstance();

    }

    @Override
    public void startTrack(String api, String uid, String orderId) {
        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/shipment_track.php?api_key="+
                api+"&user_id="+uid+"&order_id="+orderId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject trackJson = new JSONObject(response);
                    JSONArray trackArray = trackJson.getJSONArray("Shipment track");
                    for(int i=0;i<trackArray.length();i++){
                        JSONObject shipment = trackArray.getJSONObject(i);
                        String shipId = shipment.getString("shipmentid");
                        String status = shipment.getString( "shipmentstatus");
                        mView.setText(shipId,status);
                    }
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
        volley.addToRequestQueue(stringRequest,"Tracking");

    }
}
