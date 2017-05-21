package com.stef.MagazineProject.domain;


public class SalesLineItem {

    private int quantity;
    Product product;


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public SalesLineItem(int quantity,String name,String description, double price, String vendor, int warranty) {
        this.quantity = quantity;
        //product = new Product(name,description, price,vendor, warranty);
    }


    public SalesLineItem() {
        quantity = 1;
        product = new Product();
    }

    public double sumLine() {
        return quantity * product.getPrice();
    }


    @Override
    public String toString() {
        return "SalesLineItem{" +
                "Quantity: " + quantity +
                ", Product: " + product.toString() +
                '}';
    }
}