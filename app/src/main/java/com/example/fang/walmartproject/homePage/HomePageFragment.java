package com.example.fang.walmartproject.homePage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.adapter.CategoryAdapter;
import com.example.fang.walmartproject.data.CategoryItem;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.source.TopSeller;
import com.example.fang.walmartproject.subcategory.SubCategoryFragment;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class HomePageFragment extends Fragment implements HomePageContract.ShopView{
    RecyclerView categoryView;
    AppController volley;
    HomepageFragmentPresenter mShopPresenter;
    SliderLayout sliderLayout;
    public HomePageFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_shop,container,false);
        categoryView = view.findViewById(R.id.home_recycle_view);
        sliderLayout = view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(),2);
        categoryView.setLayoutManager(manager);
        categoryView.setItemAnimator(new DefaultItemAnimator());
        getActivity().setTitle("Category");
        volley = AppController.getInstance();
        mShopPresenter = new HomepageFragmentPresenter(this);
        mShopPresenter.getTopSells();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mShopPresenter.getCategoryList();

    }

    @Override
    public void showList(List<CategoryItem> itemList) {
        CategoryAdapter adapter = new CategoryAdapter(itemList, new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CategoryItem item) {
                String cid = item.getCid();
                mShopPresenter.onItemClickHandled(cid);
            }
        });
        categoryView.setAdapter(adapter);

    }

    @Override
    public void showSubCategory(String cid) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        SubCategoryFragment subCategoryFragment = new SubCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cid",cid);
        subCategoryFragment.setArguments(bundle);
        manager.beginTransaction().replace(R.id.home_page_content,subCategoryFragment).addToBackStack(null).commit();
    }

    @Override
    public void setTopSells(List<TopSeller> topSells) {
        for (int i = 0; i <topSells.size(); i++) {

            SliderView sliderView = new SliderView(this.getContext());

            TopSeller topSeller = topSells.get(i);
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
