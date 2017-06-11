package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.DaoCreator;
import com.stef.MagazineProject.DAO.GenericDao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;


public class ClientTest {
    private ArrayList<Order> orders = new ArrayList<>();

    @Test
    public void addOrder() throws Exception {
        //GenericDao dao = DaoCreator.createMySqlDao("order");
        //orders.add((Order) dao.createInDB(new Order(1)));
    }

    @Test
    public void addGoodsToOrder() throws Exception {
        /*Goods goods = Stock.findProduct();
        Scanner in= new Scanner(System.in);
        int quantity;
        System.out.println("How much product you want to buy?");
        quantity=Integer.parseInt(in.next());
        orders.get(orders.size()-1).addNewLine(quantity,goods);
        */
    }

}