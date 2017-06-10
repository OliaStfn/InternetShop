package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.DAO.DaoCreator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;


public class Stock {

    private static final Logger log = Logger.getLogger(Stock.class);
    private static ArrayList<Goods> goods = new ArrayList<Goods>();


    public static void addProduct() {
        try {
            GenericDao dao = DaoCreator.createMySqlDao("goods");
            dao.create();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public static Goods findProduct() {
        try {
            GenericDao dao = DaoCreator.createMySqlDao("goods");
            goods = dao.readAll();

            for (Goods goods : goods) {
                System.out.println(goods.toString());
            }
            System.out.print("Chose product by ID: ");
            Scanner in = new Scanner(System.in);
            int inputID = Integer.parseInt(in.next());

            for (Goods goods : goods) {
                if(goods.getId()==inputID) return goods;
            }
            return null;
        } catch (NumberFormatException e) {
            log.info(e.getMessage());
            return null;
        } catch (DaoException e) {
            log.info(e.getMessage());
            return null;
        }
    }


    public static StringBuilder getInformationAboutProducts() {
        StringBuilder temp = new StringBuilder("");
        for (Goods goods : Stock.goods) {
            temp.append(goods.toString());
            temp.append("\n");
        }
        return temp;
    }

    public static ArrayList<Goods> getGoods() {
        return goods;
    }

    public static void setGoods(ArrayList<Goods> goods) {
        Stock.goods = goods;
    }
}
