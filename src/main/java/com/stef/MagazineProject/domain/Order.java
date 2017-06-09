package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.Identifacator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Order implements Identifacator<Integer>{
    private int clientId;
    private int orderId;
    private double allPrice;
    private String status;
    private GregorianCalendar orderDate;
    private ArrayList<OrderLine> lines;

    public Order() {
    }

    public Order(int clientId) {
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

    public int getId() {
        return orderId;
    }

    public void setId(int orderId) {
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

    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderDate(GregorianCalendar orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }
}
