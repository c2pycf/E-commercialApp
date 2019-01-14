package com.example.fang.walmartproject.productCategory.productDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.adapter.ProductDetailAdapter;
import com.example.fang.walmartproject.data.Product;

public class ProductDetailFragment extends Fragment implements ProductDetailContract.DetailView{
    Product mProduct;
    RecyclerView recyclerView;
    ProductDetailContract.DetailPresenter mPresenter;

    public ProductDetailFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        String pname = bundle.getString("pname");
        String quantity = bundle.getString("quantity");
        String prize = bundle.getString("prize");
        String discription = bundle.getString("discription");
        String image = bundle.getString("pImage");
        mProduct = new Product(id,pname,quantity,prize,discription,image);
        ProductDetailAdapter adapter = new ProductDetailAdapter(mProduct, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onAddCartHandled(mProduct);
            }
        });
        recyclerView.setAdapter(adapter);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail,container,false);
        recyclerView = view.findViewById(R.id.recycle_view_product_detail);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPresenter = new ProductDetailPresenter(this);

        return view;
    }

    @Override
    public void addCartResult(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}