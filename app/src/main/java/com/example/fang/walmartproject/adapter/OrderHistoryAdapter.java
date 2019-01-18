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
import com.example.fang.walmartproject.orderHistory.OrderHistoryPresenter;

public class OrderHistoryAdapter extends RecyclerView.Adapter{
    OrderList orderList;
    Context context;
    OrderHistoryPresenter mPresenter;


    public OrderHistoryAdapter(OrderList orderList, OrderHistoryPresenter mPresenter) {
        this.orderList = orderList;
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_history,viewGroup,false);
        context = viewGroup.getContext();
        return new OrderHistoryAdapter.OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int postion = viewHolder.getItemViewType();
        final Order order = orderList.getOrder(postion);
        final OrderHistoryViewHolder viewHolder1 = (OrderHistoryViewHolder) viewHolder;
        if(orderList.getOrderListSize()==0){
            viewHolder1.title.setVisibility(View.VISIBLE);
        }
        viewHolder1.orderId.setText(order.getOrderId());
        viewHolder1.orderStatus.setText(order.getOrderStatus());
        viewHolder1.name.setText(order.getName());
        viewHolder1.bill.setText(order.getBill());
        viewHolder1.address.setText(order.getAddress());
        viewHolder1.date.setText(order.getDate());
        viewHolder1.showDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.track(order);
            }
        });


    }

    @Override
    public int getItemViewType(int position) {
        return orderList.getOrderListSize()-1-position;
    }

    @Override
    public int getItemCount() {
        return orderList.getOrderListSize();
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder{
        TextView title,orderId, orderStatus,name,bill,address,date;
        Button showDetailButton;

        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_history_id);
            orderStatus = itemView.findViewById(R.id.order_history_status);
            name = itemView.findViewById(R.id.order_history_name);
            bill= itemView.findViewById(R.id.order_history_bill);
            address= itemView.findViewById(R.id.order_history_address);
            date= itemView.findViewById(R.id.order_history_date);
            showDetailButton = itemView.findViewById(R.id.bt_tracking);
            title = itemView.findViewById(R.id.order_history_title);



        }
    }
}
