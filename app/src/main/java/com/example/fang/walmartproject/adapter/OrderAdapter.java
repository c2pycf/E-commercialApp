package com.example.fang.walmartproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.data.Order;
import com.example.fang.walmartproject.data.OrderList;

public class OrderAdapter extends RecyclerView.Adapter{
    OrderList orderList;

    public OrderAdapter(OrderList orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_comfirmed,viewGroup,false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Order order = orderList.getOrder(i);
        OrderViewHolder viewHolder1 = (OrderViewHolder) viewHolder;
        if(i==0){
            ((OrderViewHolder) viewHolder).title.setVisibility(View.VISIBLE);
        }
        viewHolder1.orderId.setText(order.getOrderId());
        viewHolder1.orderStatus.setText(order.getOrderStatus());
        viewHolder1.name.setText(order.getName());
        viewHolder1.bill.setText(order.getBill());
        viewHolder1.address.setText(order.getAddress());
        viewHolder1.itemId.setText(order.getItemId());
        viewHolder1.itemQuantity.setText(order.getQuantity());
        viewHolder1.totalPrise.setText(order.getTotalPrice());
        viewHolder1.paidPrise.setText(order.getPaid());
        viewHolder1.date.setText(order.getDate());


    }

    @Override
    public int getItemCount() {
        return orderList.getOrderListSize();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView title, orderId, orderStatus,name,bill,address,itemId,
                itemName,itemQuantity,totalPrise,paidPrise,date;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_order_id);
            orderStatus = itemView.findViewById(R.id.order_order_status);
            name = itemView.findViewById(R.id.order_order_name);
            bill= itemView.findViewById(R.id.order_order_bill);
            address= itemView.findViewById(R.id.order_order_address);
            itemId= itemView.findViewById(R.id.order_order_pid);
            itemName= itemView.findViewById(R.id.order_order_pname);
            itemQuantity= itemView.findViewById(R.id.order_order_quantity);
            totalPrise= itemView.findViewById(R.id.order_order_total_prise);
            paidPrise= itemView.findViewById(R.id.order_order_paid);
            date= itemView.findViewById(R.id.order_order_date);
            title = itemView.findViewById(R.id.order_title);


        }
    }
}
