package com.example.fang.walmartproject.cart;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.adapter.CartAdapter;
import com.example.fang.walmartproject.checkOut.CheckOutActivity;
import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.source.CartDataSource;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements ShoppingCartContracter.CartView {

    private ShoppingCartPresenter mPresenter;
    private RecyclerView recyclerView;
    static final private String TAG = ShoppingCartActivity.class.getSimpleName();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        mPresenter = new ShoppingCartPresenter(this);
        toolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shopping Cart");

        recyclerView = findViewById(R.id.cart_recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPresenter.getCartData();
        getWindow().setTitle("Cart");
    }

    @Override
    public void showRecyvleView(Cart cart, float totalPrise) {
        CartAdapter adapter = new CartAdapter(cart, this, mPresenter);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showCheckOut() {
        Intent intent = new Intent(ShoppingCartActivity.this, CheckOutActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
