package com.example.fang.walmartproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.data.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter{
    List<Product> productList;
    onProductClickedListener mListener;
    static private String TAG =ProductAdapter.class.getSimpleName();

    public ProductAdapter(List<Product> productList, onProductClickedListener mListener) {
        this.productList = productList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product,viewGroup,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ProductViewHolder productViewHolder = (ProductViewHolder) viewHolder;
        Product product = productList.get(i);
        productViewHolder.setListener(product,mListener);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        productViewHolder.pName.setText(product.getPname());
        productViewHolder.pPrise.setText(product.getPrize());
        productViewHolder.pQuantity.setText(product.getQuantity());
        productViewHolder.pImage.setImageUrl(product.getImage(),imageLoader);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public interface onProductClickedListener{
        void onProductClicked(Product product);
    }





    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView pName, pPrise, pQuantity;
        NetworkImageView pImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            pName = itemView.findViewById(R.id.tv_product_name);
            pPrise = itemView.findViewById(R.id.tv_product_prise);
            pQuantity = itemView.findViewById(R.id.tv_product_quality);
            pImage = itemView.findViewById(R.id.iv_product_image);
        }

        public void setListener(final Product product, final onProductClickedListener mListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onProductClicked(product);
                }
            });
        }
    }
}
