package com.example.fang.walmartproject.productCategory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.adapter.ProductAdapter;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.productCategory.productDetail.ProductDetailFragment;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.List;

public class ProductCategoryFragment extends Fragment implements ProductCategoryContract.ProductListView {
    ProductCategoryPresenter mPresenter;
    RecyclerView recyclerView;
    String cid, scid;
    SliderLayout sliderLayout;

    static private final String TAG = ProductCategoryFragment.class.getSimpleName();
    public ProductCategoryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop,container,false);
        getActivity().setTitle("Product List");
        recyclerView = view.findViewById(R.id.home_recycle_view);
        sliderLayout = view.findViewById(R.id.imageSlider);
        sliderLayout.setVisibility(View.GONE);
        LinearLayoutManager manager = new LinearLayoutManager(container.getContext());
        mPresenter = new ProductCategoryPresenter(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        cid = bundle.getString("cid");
        scid = bundle.getString("scid");
        Log.d(TAG,"cid: " + cid + " scid: " + scid);
        mPresenter.getProducts(cid, scid);

    }

    @Override
    public void showProductList(List<Product> itemList) {
        ProductAdapter mAdapter = new ProductAdapter(itemList, new ProductAdapter.onProductClickedListener() {
            @Override
            public void onProductClicked(Product product) {
                String id = product.getId();
                mPresenter.onProductClicked(product);
            }
        });
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void showProductDetail(Product product) {
        //show new fragment
        FragmentManager manager = getActivity().getSupportFragmentManager();
        ProductDetailFragment productCategoryFragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",product.getId());
        bundle.putString("pname",product.getPname());
        bundle.putString("quantity",product.getQuantity());
        bundle.putString("prize",product.getPrize());
        bundle.putString("discription",product.getDiscription());
        bundle.putString("pImage",product.getImage());
        productCategoryFragment.setArguments(bundle);
        manager.beginTransaction().replace(R.id.home_page_content,productCategoryFragment).addToBackStack(null).commit();
    }
}
