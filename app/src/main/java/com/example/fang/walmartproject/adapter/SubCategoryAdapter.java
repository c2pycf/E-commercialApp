package com.example.fang.walmartproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;

import com.example.fang.walmartproject.data.SubCategoryItem;


import java.util.List;

public class SubCategoryAdapter extends  RecyclerView.Adapter{
    private List<SubCategoryItem> mCategoryItemLists;

    static private final String TAG = SubCategoryAdapter.class.getSimpleName();

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(SubCategoryItem item);
    }

    public SubCategoryAdapter(List<SubCategoryItem> mCategoryItemLists,OnItemClickListener listener) {
        this.mCategoryItemLists = mCategoryItemLists;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category,viewGroup,false);

        return new SubCategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SubCategoryAdapter.CategoryViewHolder categoryViewHolder = (SubCategoryAdapter.CategoryViewHolder) viewHolder;
        SubCategoryItem item = mCategoryItemLists.get(i);
        categoryViewHolder.setItemListener(item,mListener);
        categoryViewHolder.categoryTextView.setText(item.getScname());
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        categoryViewHolder.categoryImage.setImageUrl(item.getScimageurl(),imageLoader);
        categoryViewHolder.categoryImage.setContentDescription(item.getScdiscription());



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

        void setItemListener(final SubCategoryItem item, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG,"setItemListener");
                    listener.onItemClick(item);
                }
            });
        }
    }
}
