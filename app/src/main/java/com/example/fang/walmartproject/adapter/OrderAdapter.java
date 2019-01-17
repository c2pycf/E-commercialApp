package com.example.fang.walmartproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.data.Order;
import com.example.fang.walmartproject.data.OrderList;

import java.text.NumberFormat;

public class OrderAdapter extends RecyclerView.Adapter{
    OrderList orderList;
    Context context;
    static NumberFormat format = NumberFormat.getCurrencyInstance();
    public OrderAdapter(OrderList orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_comfirmed,viewGroup,false);
        context = viewGroup.getContext();
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final Order order = orderList.getOrder(i);
        final OrderViewHolder viewHolder1 = (OrderViewHolder) viewHolder;
        if(i==0){
            viewHolder1.title.setVisibility(View.VISIBLE);
        }
        viewHolder1.orderId.setText(order.getOrderId());
        viewHolder1.orderStatus.setText(order.getOrderStatus());
        viewHolder1.name.setText(order.getName());
        viewHolder1.bill.setText(order.getBill());
        viewHolder1.address.setText(order.getAddress());
        viewHolder1.paidPrise.setText(format.format(order.getPaid()));
        viewHolder1.date.setText(order.getDate());
        viewHolder1.totalPrise.setText(format.format(order.getProductList().getTotalPrize()));
        LinearLayoutManager manager = new LinearLayoutManager(context);
        viewHolder1.recyclerView.setLayoutManager(manager);
        viewHolder1.recyclerView.setItemAnimator(new DefaultItemAnimator());
        viewHolder1.showDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder1.recyclerView.getVisibility()==View.GONE) {
                    viewHolder1.recyclerView.setVisibility(View.VISIBLE);
                    OrderDetailAdapter adapter = new OrderDetailAdapter(order.getProductList());
                    viewHolder1.recyclerView.setAdapter(adapter);
                }
                else {
                    viewHolder1.recyclerView.setVisibility(View.GONE);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return orderList.getOrderListSize();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView title, orderId, orderStatus,name,bill,address,totalPrise,paidPrise,date;
        Button showDetailButton;
        RecyclerView recyclerView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_order_id);
            orderStatus = itemView.findViewById(R.id.order_order_status);
            name = itemView.findViewById(R.id.order_order_name);
            bill= itemView.findViewById(R.id.order_order_bill);
            address= itemView.findViewById(R.id.order_order_address);
            paidPrise= itemView.findViewById(R.id.order_order_paid);
            date= itemView.findViewById(R.id.order_order_date);
            title = itemView.findViewById(R.id.order_title);
            totalPrise = itemView.findViewById(R.id.order_order_total_prise);
            showDetailButton = itemView.findViewById(R.id.bt_show_order_detail);
            recyclerView =itemView.findViewById(R.id.order_item_recycle);


        }
    }
}
