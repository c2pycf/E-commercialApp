package com.example.fang.walmartproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;

public class OrderDetailAdapter extends RecyclerView.Adapter{
    Cart cart;

    public OrderDetailAdapter(Cart cart) {
        this.cart = cart;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_detail,viewGroup,false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Product product = cart.getProduct(i);
        OrderDetailViewHolder viewHolder1 = (OrderDetailViewHolder) viewHolder;
        viewHolder1.pid.setText(product.getId());
        viewHolder1.pname.setText(product.getPname());
        //viewHolder1.amount.setText(product.getUserAmount());
        //viewHolder1.prise.setText(product.getPrize());
    }

    @Override
    public int getItemCount() {
        return cart.getCartSize();
    }


    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        TextView pid,pname,prise,amount;
        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            pid = itemView.findViewById(R.id.tv_order_detail_pid);
            pname= itemView.findViewById(R.id.tv_order_detail_pname);
            prise = itemView.findViewById(R.id.tv_order_detail_prise);
            amount= itemView.findViewById(R.id.tv_order_detail_amount);
        }
    }
}
