package com.example.fang.walmartproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.data.Product;

import java.text.NumberFormat;
import java.util.List;

public class ProductDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Product product;
    static private String TAG =ProductDetailAdapter.class.getSimpleName();
    static private int PRODUCT_DETIAL_CARD = 0;
    static private int PRODUCT_DESCRIPTION_CARD = 1;
    static private int PRODUCT_REVIEW_CARD = 2;
    View.OnClickListener mListener;

    public ProductDetailAdapter(Product product, View.OnClickListener listener) {
        this.product = product;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        Log.d(TAG, "view type "+ i);
        switch (i){
            case 0:

                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_detail,viewGroup,false);


                return new ProductDetailViewHolder(view);
            case 1:
                Log.d(TAG, "product desc viewholder");
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_disc,viewGroup,false);


                return new ProductDescViewHolder(view);

            case 2:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_review,viewGroup,false);


                return new ProductReviewViewHolder(view);

            default:
                    return null;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = viewHolder.getItemViewType();
        switch (viewType){
            case 0:
                ProductDetailViewHolder productDetailViewHolder = (ProductDetailViewHolder) viewHolder;
                ImageLoader imageLoader = AppController.getInstance().getImageLoader();
                productDetailViewHolder.pName.setText(product.getPname());
                productDetailViewHolder.pImage.setImageUrl(product.getImage(),imageLoader);
                productDetailViewHolder.pQuantity.setText(product.getQuantity());
                int count = Integer.parseInt(product.getQuantity());
                NumberFormat format = NumberFormat.getCurrencyInstance();
                int prise = Integer.parseInt(product.getPrize());
                productDetailViewHolder.pPrise.setText(format.format(prise));
                productDetailViewHolder.addCartButton.setOnClickListener(mListener);
                Integer[] items = new Integer[count];
                for(int index = 0; index < count;index++){
                    items[index] = index;
                }
                //ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,items);
                //productDetailViewHolder.itemCount.setAdapter(adapter);
                break;

            case 1:
                ProductDescViewHolder productDescViewHolder = (ProductDescViewHolder) viewHolder;
                productDescViewHolder.description.setText(product.getDiscription());
                break;

            case 2:
                ProductReviewViewHolder productReviewViewHolder = (ProductReviewViewHolder) viewHolder;

                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ProductDetailViewHolder extends RecyclerView.ViewHolder{
        TextView pName, pPrise, pQuantity;
        NetworkImageView pImage;
        Spinner itemCount;
        Button addCartButton;

        public ProductDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            pName = itemView.findViewById(R.id.tv_product__title_detail);
            pPrise = itemView.findViewById(R.id.tv_product_prise_detail);
            pQuantity = itemView.findViewById(R.id.tv_product_quality_detail);
            pImage = itemView.findViewById(R.id.nv_product_image_detail);
            itemCount = itemView.findViewById(R.id.sp_product_count);
            addCartButton = itemView.findViewById(R.id.bt_add_cart);
        }

    }

    public class ProductDescViewHolder extends RecyclerView.ViewHolder{
        TextView description;

        public ProductDescViewHolder(@NonNull View itemView) {
            super(itemView);
           description = itemView.findViewById(R.id.tv_product_disc);
        }

    }

    public class ProductReviewViewHolder extends RecyclerView.ViewHolder{
       CardView review;

        public ProductReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            review = itemView.findViewById(R.id.card_review);
        }

    }

}
