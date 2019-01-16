package com.example.fang.walmartproject.data;

import java.util.List;

public class Order {
    String orderId;

    String orderStatus;

    String name;

    String bill;

    String address;

    String mobile;

    String email;

    Float paid;

    String date;

    Cart productList;

    public Order(String orderId, String orderStatus, String name, String bill, String address, String mobile, String email, float paid, String date, Cart cart) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.name = name;
        this.bill = bill;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.paid = paid;
        this.date = date;
        this.productList = cart;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Cart getProductList() {
        return productList;
    }

    public void setProductList(Cart productList) {
        this.productList = productList;
    }
}
