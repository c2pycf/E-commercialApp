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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;

import com.example.fang.walmartproject.adapter.SubCategoryAdapter;

import com.example.fang.walmartproject.data.SubCategoryItem;
import com.example.fang.walmartproject.data.source.TopSeller;
import com.example.fang.walmartproject.productCategory.ProductCategoryFragment;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class SubCategoryFragment extends Fragment implements SubCategoryContract.ShopView{
    RecyclerView categoryView;
    AppController volley;
    SubCategoryPresenter mShopPresenter;
    String cid;
    SliderLayout sliderLayout;

    static private String TAG = SubCategoryFragment.class.getSimpleName();
    public SubCategoryFragment() {
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
        volley = AppController.getInstance();
        mShopPresenter = new SubCategoryPresenter(this,cid);
        sliderLayout = view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1);
        mShopPresenter.getTopSells();

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

    @Override
    public void setTopSells(List<TopSeller> sellerList) {
        for (int i = 0; i <sellerList.size(); i++) {

            SliderView sliderView = new SliderView(this.getContext());

            TopSeller topSeller = sellerList.get(i);
            sliderView.setImageUrl(topSeller.getLogo());
            sliderView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);
            sliderView.setDescription(topSeller.getName()+"\n"+topSeller.getDeal()+"\n"+"Rating: "+topSeller.getRating());
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(getContext(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }
}
