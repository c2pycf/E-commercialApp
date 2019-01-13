package com.example.fang.walmartproject.subcategory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;

import com.example.fang.walmartproject.adapter.SubCategoryAdapter;

import com.example.fang.walmartproject.data.SubCategoryItem;
import com.example.fang.walmartproject.productCategory.ProductCategoryFragment;

import java.util.List;

public class SubCategoryFragment extends Fragment implements SubCategoryContract.ShopView{
    RecyclerView categoryView;
    AppController volley;
    SubCategoryPresenter mShopPresenter;
    String cid;

    static private String TAG = SubCategoryFragment.class.getSimpleName();
    public SubCategoryFragment() {
        volley = AppController.getInstance();
        mShopPresenter = new SubCategoryPresenter(this,cid);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        this.cid = bundle.getString("cid");
        Log.d(TAG,"cid: "+ cid);
        View view =  inflater.inflate(R.layout.fragment_shop,container,false);
        categoryView = view.findViewById(R.id.home_recycle_view);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(),2);
        categoryView.setLayoutManager(manager);
        categoryView.setItemAnimator(new DefaultItemAnimator());
        getActivity().setTitle("SubCategory");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mShopPresenter.getCategoryList();

    }

    @Override
    public void showList(List<SubCategoryItem> itemList) {
        SubCategoryAdapter adapter = new SubCategoryAdapter(itemList, new SubCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SubCategoryItem item) {
                String scid = item.getScid();
                mShopPresenter.onSubCategoryItemClicked(cid, scid);
            }
        });
        categoryView.setAdapter(adapter);

    }

    @Override
    public void showProductList(String cid, String scid) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        ProductCategoryFragment productCategoryFragment = new ProductCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cid",cid);
        bundle.putString("scid",scid);
        productCategoryFragment.setArguments(bundle);
        manager.beginTransaction().replace(R.id.home_page_content,productCategoryFragment).addToBackStack(null).commit();

    }
}
