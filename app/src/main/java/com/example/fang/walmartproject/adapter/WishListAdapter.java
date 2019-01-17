package com.example.fang.walmartproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.WishList;
import com.example.fang.walmartproject.wishList.WishListPresenter;

import java.text.NumberFormat;

public class WishListAdapter extends RecyclerView.Adapter{
    WishList wishList;
    AppController volley;
    Context context;
    static final String TAG = WishListAdapter.class.getSimpleName();
    static NumberFormat format = NumberFormat.getCurrencyInstance();
    WishListPresenter mPresenter;


    public WishListAdapter(WishList wishList, Fragment fragment,WishListPresenter presenter) {
        this.wishList = wishList;
        volley = AppController.getInstance();

        this.context=fragment.getContext();

        mPresenter = presenter;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wish_list,viewGroup,false);

        return new WishItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final Product product = wishList.getProduct(i);
        final WishItemViewHolder itemViewHolder = (WishItemViewHolder) viewHolder;
        itemViewHolder.titleTextView.setText(product.getPname());
        String prise = format.format(Integer.parseInt(product.getPrize()));
        itemViewHolder.priseTextView.setText(prise);
        int count = Integer.parseInt(product.getQuantity());
        ImageLoader imageLoader = volley.getImageLoader();
        itemViewHolder.imageView.setImageUrl(product.getImage(), imageLoader);
        itemViewHolder.totalTextView.setText(product.getQuantity());
        Integer[] items = new Integer[count];
        for(int index = 0; index < count;index++){
            items[index] = index+1;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(context,android.R.layout.simple_spinner_dropdown_item,items);
        itemViewHolder.amount.setAdapter(adapter);
        itemViewHolder.amount.setSelection(product.getUserAmount()-1);
        itemViewHolder.removeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteProduct(product.getId());
            }
        });
        itemViewHolder.editTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setUserAmount(itemViewHolder.amount.getSelectedItemPosition()+1);
                mPresenter.updateProduct(product);
            }
        });
        itemViewHolder.saveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addToCart(product);
            }
        });

    }

    @Override
    public int getItemCount() {
        return wishList.getCartSize();
    }

    public class WishItemViewHolder extends RecyclerView.ViewHolder{
        NetworkImageView imageView;
        TextView titleTextView,priseTextView,removeTextView,saveTextView, totalTextView, editTextView;
        Spinner amount;

        public WishItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.nv_product_image_wish);
            titleTextView = itemView.findViewById(R.id.tv_product_title_wish);
            priseTextView = itemView.findViewById(R.id.tv_product_prise_wish);
            removeTextView = itemView.findViewById(R.id.item_remove_wish);
            saveTextView = itemView.findViewById(R.id.item_save_wish);
            editTextView = itemView.findViewById(R.id.item_edit_wish);
            amount = itemView.findViewById(R.id.sp_product_count_wish);
            totalTextView = itemView.findViewById(R.id.tv_product_quality_wish);
        }
    }
}
