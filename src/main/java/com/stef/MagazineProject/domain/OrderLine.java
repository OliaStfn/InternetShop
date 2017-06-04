package com.stef.MagazineProject.domain;

import java.util.GregorianCalendar;

public class OrderLine {
    private Product product;
    private int count;

    public OrderLine(String name, double price, String vendor, GregorianCalendar productionDate, GregorianCalendar expDate, int count) {
        product = new Product(name,price,vendor,productionDate,expDate);
        this.count = count;
    }

    public OrderLine(Product product,int count) {
        this.product = product;
        this.count = count;
    }

    public double getPrice(){
        return count*product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
