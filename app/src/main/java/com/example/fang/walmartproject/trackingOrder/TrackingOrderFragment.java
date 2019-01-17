package com.example.fang.walmartproject.trackingOrder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fang.walmartproject.R;

public class TrackingOrderFragment extends Fragment implements TrackingOrderContract.TrackingOrderView {

    private TextView shipmentId,shipmnentStatus;
    private TrackingOrderPresenter mPresenter;
    private ImageView trackingImage;

    public TrackingOrderFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle b= getArguments();
        if(b!=null) {
            String api = b.getString("api");
            String userId = b.getString("uid");
            String orderId = b.getString("order_id");
            mPresenter.startTrack(api, userId, orderId);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking,container,false);
        shipmentId = view.findViewById(R.id.tv_tracking_id);
        shipmnentStatus = view.findViewById(R.id.tv_tracking_status);
        mPresenter = new TrackingOrderPresenter(this);
        trackingImage = view.findViewById(R.id.tracking_image);
        getActivity().setTitle("Tracking Orders");
        return view;
    }

    @Override
    public void setText(String shipId, String status) {
        shipmentId.setText(shipId);
        String msg="";
        switch (status){
            case("1"):
                msg = "Your order is confirmed!";
                trackingImage.setImageResource(R.drawable.baseline_mood_black_48dp);
                shipmnentStatus.setText(msg);
                break;
            case("2"):
                msg = "Your order is dispatched!";
                trackingImage.setImageResource(R.drawable.baseline_move_to_inbox_black);
                shipmnentStatus.setText(msg);
                break;
            case("3"):
                msg = "Your order is shipping now!";
                trackingImage.setImageResource(R.drawable.baseline_local_shipping);
                shipmnentStatus.setText(msg);
                break;
            case("4"):
                msg = "Your order is delivered";
                trackingImage.setImageResource(R.drawable.baseline_check_box);
                shipmnentStatus.setText(msg);
                break;

        }
    }
}
