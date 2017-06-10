package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.Identifacator;

public class OrderLine implements Identifacator<Integer>{
    private int orderId;
    private int orderLineId;
    private Goods goods;
    private int count;
    private double price;

    public OrderLine() {

    }

    public OrderLine(Goods goods, int count,int orderId) {
        this.goods = goods;
        this.count = count;
        this.orderId=orderId;
        price=setPrice();
    }

    public double setPrice() {
        return count * goods.getPrice();
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getId() {
        return orderLineId;
    }

    public void setId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
