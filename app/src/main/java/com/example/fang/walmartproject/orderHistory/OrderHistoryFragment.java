package com.example.fang.walmartproject.orderHistory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.adapter.OrderHistoryAdapter;
import com.example.fang.walmartproject.data.OrderList;
import com.example.fang.walmartproject.login.LoginActivity;
import com.example.fang.walmartproject.trackingOrder.TrackingOrderFragment;

public class OrderHistoryFragment extends Fragment implements OrderHistoryContract.OrderHistoryView {
    RecyclerView recyclerView;
    OrderHistoryPresenter mPresenter;
    AppController volley;
    int signInFlg;
    LinearLayout signInContent;
    Button signInButton;

    public OrderHistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history,container,false);
        recyclerView =view.findViewById(R.id.order_history_recycle);
        signInContent = view.findViewById(R.id.order_history_sign_in);
        signInButton = view.findViewById(R.id.bt_order_history_sign_in);

        getActivity().setTitle("Order History");
        volley = AppController.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new OrderHistoryPresenter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        signInFlg = volley.getSignFlag();
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onSignInClicked();
            }
        });
        if (signInFlg==0){
            signInContent.setVisibility(View.VISIBLE);
        }
        else{
            signInContent.setVisibility(View.GONE);
            mPresenter.getOrderHistory();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (volley.getSignFlag()==0){
            signInContent.setVisibility(View.VISIBLE);
        }
        else{
            signInContent.setVisibility(View.GONE);
            mPresenter.getOrderHistory();
        }
    }

    @Override
    public void showList(OrderList orderList) {
        OrderHistoryAdapter adapter = new OrderHistoryAdapter(orderList,mPresenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSignInPage() {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void showTrack(Bundle b) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        TrackingOrderFragment fragment = new TrackingOrderFragment();
        fragment.setArguments(b);
        manager.beginTransaction().replace(R.id.home_page_content,fragment).addToBackStack(null).commit();
    }

}
