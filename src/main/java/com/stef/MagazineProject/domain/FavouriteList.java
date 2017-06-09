package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.Identifacator;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Scanner;

public class FavouriteList implements Identifacator<Integer>{
    private static final Logger log = Logger.getLogger(FavouriteList.class);
    private int id;
    private int clientId;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    private ArrayList<Product> item;

    public FavouriteList() {
        item = new ArrayList<Product>();
    }

    public void addProduct() {
        Scanner in = new Scanner(System.in);
        int repeat = -1;
        Product pr = null;
        do {

            try {
                pr = Stock.findProduct();
                item.add(pr);
                log.info("The product was add to favourite list\n" + pr.toString());
                System.out.println("Add other product?");
                System.out.println("1-YES / 0-NO :");
                repeat = Integer.parseInt(in.next());
            } catch (Exception e) {
                log.error("Error" + e.getMessage());
            }
        } while (repeat != 0);
    }

    public void deleteProduct(){
        Scanner in = new Scanner(System.in);
        int repeat = -1;
        String temp;
        do {
            try {
                for(Product products: item){
                    System.out.println(products.toString());
                }
                System.out.println("Enter name of product which you want delete");
                temp=in.nextLine();
                for(int i=0;i<item.size();i++){
                    if(temp==item.get(i).getName()){
                        item.remove(i);
                    }
                }
                System.out.println("Delete other product?");
                System.out.println("1-YES / 0-NO :");
                repeat = Integer.parseInt(in.next());
            } catch (Exception e) {
                log.error("Error" + e.getMessage());
            }
        } while (repeat!= 0);
    }

    @Override
    public String toString() {
        String temp="";
        for(Product products:item){
            temp+=products.toString();
        }
        return "FavouriteList{" +
                "Item: " + temp +
                '}';
    }
}