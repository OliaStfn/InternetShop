package com.stef.MagazineProject.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Sale implements Serializable {
    private int id;
    private GregorianCalendar date;
    ArrayList<SalesLineItem> lines;


    public Sale() {
        date = new GregorianCalendar();
        lines = new ArrayList<SalesLineItem>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GregorianCalendar getDate() {
        return date;
    }


    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void addLine(int quantity,String name,String description, double price, String vendor, int warranty) {
        lines.add(new SalesLineItem(quantity,name,description,price,vendor,warranty));
    }

    public double allPrice() {
        double temp = 0;
        for (int i = 0; i < lines.size(); i++) {
            temp += lines.get(i).sumLine();
        }
        return temp;
    }

    @Override
    public String toString() {
        return "Sale{" +
                " Lines: " + lines.toString() +
                '}';
    }
}