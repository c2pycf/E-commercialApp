package com.example.fang.walmartproject.cart;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.adapter.CartAdapter;
import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.source.CartDataSource;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements ShoppingCartContracter.CartView{

    private ShoppingCartPresenter mPresenter;
    private RecyclerView recyclerView;
    static final private String TAG = ShoppingCartActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        mPresenter = new ShoppingCartPresenter(this);

        recyclerView = findViewById(R.id.cart_recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPresenter.getCartData();
        getWindow().setTitle("Cart");
    }

    @Override
    public void showRecyvleView(Cart cart, int totalPrise) {
        CartAdapter adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);

    }
}
