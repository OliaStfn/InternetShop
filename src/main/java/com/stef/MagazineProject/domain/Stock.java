package com.stef.MagazineProject.domain;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class Stock {

    private static final Logger log = Logger.getLogger(Stock.class);
    private static ArrayList<Product> goods = new ArrayList<Product>();


    public static void addProduct(String name, double price, String vendor, GregorianCalendar productionDate, GregorianCalendar expDate) {
        try {
            if (price <= 0) {
                throw new Exception();
            }
            goods.add(new Product(name, price, vendor, productionDate, expDate));
        } catch (Exception e) {
            System.out.println("Price less than zero");
            log.info("Price less than zero" + e.getMessage());
        }
    }

    public static void addProduct() {
        Scanner in = new Scanner(System.in);
        String name = "";
        String vendor = "";
        double price = 0;
        int day;
        int month;
        int year;
        GregorianCalendar productionDate;
        GregorianCalendar expDate;
        int repeat = -1;
        do {
            System.out.print("Enter product name: ");
            name = in.next();
            System.out.print("Enter price of product: ");
            price = Double.parseDouble(in.next());
            System.out.print("Enter product vendor: ");
            vendor = in.next();
            System.out.println("Enter production day of product: ");
            day = in.nextInt();
            System.out.println("Enter production month of product: ");
            month = in.nextInt();
            System.out.println("Enter production year of product: ");
            year = in.nextInt();
            productionDate = new GregorianCalendar(year, month, day);
            System.out.println("Enter expiration day of product: ");
            day = in.nextInt();
            System.out.println("Enter expiration month of product: ");
            month = in.nextInt();
            System.out.println("Enter expiration year of product: ");
            year = in.nextInt();
            expDate = new GregorianCalendar(year, month, day);

            System.out.println("Add another product?");
            System.out.print("1-YES / 0-NO : ");
            repeat = Integer.parseInt(in.next());
        } while (repeat != 0);
    }


    public static Product findProduct() {

        for (Product product : goods) {
            System.out.println(product.toString());
        }

        System.out.print("Chose your product by ID: ");
        Scanner in = new Scanner(System.in);
        int inputID = Integer.parseInt(in.next());
        for (int i = 0; i < goods.size(); i++) {
            if (goods.get(i).getId() == inputID) {
                inputID = i;
                break;
            }
        }
        return goods.get(inputID);
    }


    public static StringBuilder getInformationAboutProducts() {
        StringBuilder temp = new StringBuilder("");
        for (Product product : goods) {
            temp.append(product.toString());
            temp.append("\n");
        }
        return temp;
    }

    public static ArrayList<Product> getGoods() {
        return goods;
    }

    public static void setGoods(ArrayList<Product> goods) {
        Stock.goods = goods;
    }
}
