package com.stef.MagazineProject.domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Orders {
    private int clientId;
    private int orderId;
    private double allPrice;
    private ArrayList<OrderLine> lines;

    public Orders(int clientId) {
        this.clientId = clientId;
        allPrice = 0;
        lines = new ArrayList<>();
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getAllPrice() {
        return allPrice;
    }

    public double allPrice() {
        double temp=0;
        for (OrderLine line: lines){
            temp+=line.getPrice();
        }
        return temp;
    }

    public void addNewLine(int count,Product product){
        lines.add(new OrderLine(product,count));
        allPrice=allPrice();
    }

    public void addNewLine(String name, double price, String vendor, GregorianCalendar productionDate, GregorianCalendar expDate, int count){
        lines.add(new OrderLine(name,price,vendor,productionDate,expDate,count));
        allPrice=allPrice();
    }
}
