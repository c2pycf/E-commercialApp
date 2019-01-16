package com.example.fang.walmartproject.wishList;

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

import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.adapter.WishListAdapter;
import com.example.fang.walmartproject.data.WishList;

public class WishListFragment extends Fragment implements WishListContract.WishListView {
    RecyclerView recyclerView;
    WishListPresenter mPresenter;

    public WishListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish_list,container,false);
        recyclerView = view.findViewById(R.id.wish_recycle_view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Save for later");
        mPresenter = new WishListPresenter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPresenter.getWishList();


    }

    @Override
    public void showWishList(WishList wishList) {
        WishListAdapter adapter =  new WishListAdapter(wishList, this);
        recyclerView.setAdapter(adapter);

    }
}
