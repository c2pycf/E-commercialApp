package com.example.fang.walmartproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;

public class CartAdapter extends RecyclerView.Adapter{
    Cart cart;
    AppController volley;


    public CartAdapter(Cart cart) {
        this.cart = cart;
        volley = AppController.getInstance();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cart,viewGroup,false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CartItemViewHolder itemViewHolder = (CartItemViewHolder) viewHolder;
        Product product = cart.getProduct(i);
        itemViewHolder.titleTextView.setText(product.getPname());
        itemViewHolder.priseTextView.setText(product.getPrize());
        ImageLoader imageLoader = volley.getImageLoader();
        itemViewHolder.imageView.setImageUrl(product.getImage(),imageLoader);
        itemViewHolder.totalTextView.setText(product.getQuantity());
        itemViewHolder.amount.setSelection(product.getUserAmount()-1);

    }

    @Override
    public int getItemCount() {
        return cart.getCartSize();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder{
        NetworkImageView imageView;
        TextView titleTextView,priseTextView,removeTextView,saveTextView, totalTextView;
        Spinner amount;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.nv_product_image_cart);
            titleTextView = itemView.findViewById(R.id.tv_product_title_cart);
            priseTextView = itemView.findViewById(R.id.tv_product_prise_cart);
            removeTextView = itemView.findViewById(R.id.item_remove_cart);
            saveTextView = itemView.findViewById(R.id.item_save_cart);
            amount = itemView.findViewById(R.id.sp_product_count_cart);
            totalTextView = itemView.findViewById(R.id.tv_product_quality_cart);
        }
    }

    public class CartCheckOutViewHolder extends RecyclerView.ViewHolder{

        public CartCheckOutViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
