package com.example.fang.walmartproject.productCategory.productDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.adapter.ProductDetailAdapter;
import com.example.fang.walmartproject.data.Product;

public class ProductDetailFragment extends Fragment implements ProductDetailContract.DetailView{
    Product mProduct;
    RecyclerView recyclerView;
    ProductDetailPresenter mPresenter;
    static private final String TAG = ProductDetailFragment.class.getSimpleName();

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
        final ProductDetailAdapter adapter = new ProductDetailAdapter(mProduct, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mProduct.setUserAmount(1);
                mPresenter.onAddCartHandled(mProduct);
            }
        }, getContext(), new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"User select amount is " + (position+1));
                mProduct.setUserAmount(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        },mPresenter);
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
        getActivity().setTitle("Product Details");

        return view;
    }

    @Override
    public void addCartResult(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void share(Product product) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Product ID: " + product.getId()+"Product: "+product.getPname()+"\n"+"Prise: " + product.getPrize());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
