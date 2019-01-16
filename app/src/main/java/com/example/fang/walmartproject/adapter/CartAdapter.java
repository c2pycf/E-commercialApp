package com.example.fang.walmartproject.adapter;

import android.app.Activity;
import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.example.fang.walmartproject.cart.ShoppingCartActivity;
import com.example.fang.walmartproject.cart.ShoppingCartContracter;
import com.example.fang.walmartproject.cart.ShoppingCartPresenter;
import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;
import java.text.NumberFormat;

public class CartAdapter extends RecyclerView.Adapter{
    Cart cart;
    AppController volley;
    static final String TAG = CartAdapter.class.getSimpleName();
    static NumberFormat format = NumberFormat.getCurrencyInstance();
    ShoppingCartContracter.CartPresenter mPresenter;
    Activity activity;



    public CartAdapter(Cart cart, ShoppingCartActivity activity, ShoppingCartContracter.CartPresenter presenter) {
        this.cart = cart;
        volley = AppController.getInstance();
        mPresenter = presenter;
        this.activity =activity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == cart.getCartSize()){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_total_prise_not_empty, viewGroup, false);
            return new CartCheckOutViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cart, viewGroup, false);
            return new CartItemViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = viewHolder.getItemViewType();
        Log.d(TAG,"type" + type);
        if(type == (cart.getCartSize())){
            CartCheckOutViewHolder priseViewHolder1 = (CartCheckOutViewHolder) viewHolder;
            float totalPrise = cart.getTotalPrize();
            float tax = (float) (totalPrise*0.08);
            float estTotalPrise = totalPrise + tax;
                priseViewHolder1.subTotalTextView.setText(format.format(totalPrise));
                priseViewHolder1.shippingTextView.setText(format.format(0));
                priseViewHolder1.taxTextView.setText(format.format(tax));
                priseViewHolder1.estTotalTextView.setText(format.format(estTotalPrise));
            if(totalPrise==0){
                priseViewHolder1.proceedButton.setVisibility(View.GONE);
                priseViewHolder1.emptyMsgTextView.setVisibility(View.VISIBLE);
            }
            else {
                priseViewHolder1.proceedButton.setVisibility(View.VISIBLE);
                priseViewHolder1.emptyMsgTextView.setVisibility(View.GONE);
                priseViewHolder1.proceedButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.processToCheckOut();
                    }
                });
            }
        }
        else {
            final CartItemViewHolder itemViewHolder = (CartItemViewHolder) viewHolder;
            final Product product = cart.getProduct(i);
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
            ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(activity,android.R.layout.simple_spinner_dropdown_item,items);
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
                    mPresenter.saveLater(product);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return cart.getCartSize()+1;
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder{
        NetworkImageView imageView;
        TextView titleTextView,priseTextView,removeTextView,saveTextView, totalTextView, editTextView;
        Spinner amount;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.nv_product_image_cart);
            titleTextView = itemView.findViewById(R.id.tv_product_title_cart);
            priseTextView = itemView.findViewById(R.id.tv_product_prise_cart);
            removeTextView = itemView.findViewById(R.id.item_remove_cart);
            saveTextView = itemView.findViewById(R.id.item_save_cart);
            editTextView = itemView.findViewById(R.id.item_edit_cart);
            amount = itemView.findViewById(R.id.sp_product_count_cart);
            totalTextView = itemView.findViewById(R.id.tv_product_quality_cart);
        }
    }

    public class CartCheckOutViewHolder extends RecyclerView.ViewHolder{
        TextView subTotalTextView,shippingTextView, taxTextView,estTotalTextView,emptyMsgTextView;
        Button proceedButton;

        public CartCheckOutViewHolder(@NonNull View itemView) {
            super(itemView);
            subTotalTextView = itemView.findViewById(R.id.tv_sub_total_cart);
            shippingTextView = itemView.findViewById(R.id.tv_shipping_cart);
            taxTextView = itemView.findViewById(R.id.tv_tax_cart);
            estTotalTextView = itemView.findViewById(R.id.tv_est_total);
            proceedButton = itemView.findViewById(R.id.bt_process);
            emptyMsgTextView = itemView.findViewById(R.id.tv_empty_cart);

        }
    }


}
