package com.example.fang.walmartproject.checkOut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.adapter.CheckOutAdapter;
import com.example.fang.walmartproject.adapter.OrderAdapter;
import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.OrderList;
import com.example.fang.walmartproject.homePage.HomePageActivity;
import com.stripe.android.Stripe;

public class CheckOutActivity extends AppCompatActivity implements CheckOutContract.CheckOutView{

    static final String TAG = CheckOutActivity.class.getSimpleName();
    static final String SIMPLIFY_API = "pk_test_Bb1D97K4vdVgMi3wmDkH3C8h";
    CheckOutPresenter mPresenter;
    RecyclerView recyclerView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        recyclerView = findViewById(R.id.checkout_recycle_view);
        toolbar = findViewById(R.id.toolbar_checkout);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Check out");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPresenter = new CheckOutPresenter(this);
        mPresenter.getCart();
    }

    @Override
    public void showRecycleView(Cart cart) {
        CheckOutAdapter adapter = new CheckOutAdapter(mPresenter,cart);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showOrderComfirmation(OrderList orderList) {
        OrderAdapter adapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void onCompleteClicked(View view){
        Intent intent = new Intent(CheckOutActivity.this,HomePageActivity.class);
        startActivity(intent);
    }
}
