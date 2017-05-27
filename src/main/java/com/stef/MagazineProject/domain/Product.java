package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.Identifacator;
import com.stef.MagazineProject.support.ISort;

import java.util.GregorianCalendar;

public class Product implements Identifacator<Integer>,ISort {
    private Integer id;
    private String name;
    private double price;
    private String vendor;
    private GregorianCalendar productionDate;
    private GregorianCalendar expDate;

    public Product() {
    }

    public Product(String name, double price, String vendor, GregorianCalendar productionDate, GregorianCalendar expDate) {
        this.name = name;
        this.price = price;
        this.vendor = vendor;
        this.productionDate = productionDate;
        this.expDate = expDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void showExpDate(){
        System.out.println("Expiration date is :" + expDate.getTime());
    }

    public void setProductionDate(GregorianCalendar productionDate) {
        this.productionDate = productionDate;
    }

    public void setExpDate(GregorianCalendar expDate) {
        this.expDate = expDate;
    }

    public GregorianCalendar getProductionDate() {
        return productionDate;
    }

    public GregorianCalendar getExpDate() {
        return expDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Name: " + name +
                ", Price: " + price +
                ", vendor: " + vendor +
                ", Production Date: " +  getProductionDate().get(GregorianCalendar.DATE) +
                "." + getProductionDate().get(GregorianCalendar.MONTH) +
                "." + getProductionDate().get(GregorianCalendar.YEAR) +
                ", Expiration Date: " + getExpDate().get(GregorianCalendar.DATE) +
                "." + getExpDate().get(GregorianCalendar.MONTH) +
                "." + getExpDate().get(GregorianCalendar.YEAR) +
                '}';
    }

    @Override
    public String getWordForSort() {
        return getName();
    }

    @Override
    public double getPriceForSort() {
        return getPrice();
    }
}