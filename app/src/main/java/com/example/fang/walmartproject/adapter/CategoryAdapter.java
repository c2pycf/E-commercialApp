package com.example.fang.walmartproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.data.CategoryItem;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter{
    private List<CategoryItem> mCategoryItemLists;

    public CategoryAdapter(List<CategoryItem> mCategoryItemLists) {
        this.mCategoryItemLists = mCategoryItemLists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category,viewGroup,false);

       return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CategoryViewHolder categoryViewHolder = (CategoryViewHolder) viewHolder;
        CategoryItem item = mCategoryItemLists.get(i);
        categoryViewHolder.categoryTextView.setText(item.getCname());
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        categoryViewHolder.categoryImage.setImageUrl(item.getCimageurl(),imageLoader);
        categoryViewHolder.categoryImage.setContentDescription(item.getCdiscription());



    }

    @Override
    public int getItemCount() {
        return mCategoryItemLists.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        NetworkImageView categoryImage;
        TextView categoryTextView;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.iv_cimage);
            categoryTextView = itemView.findViewById(R.id.tv_cname);

        }
    }
}
