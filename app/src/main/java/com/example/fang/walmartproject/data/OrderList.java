package com.example.fang.walmartproject.data;

import java.util.ArrayList;
import java.util.List;

public class OrderList {
    List<Order> orders;

    public OrderList() {
        orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrder(int i){
        return orders.get(i);
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public int getOrderListSize(){
        return orders.size();
    }



}
