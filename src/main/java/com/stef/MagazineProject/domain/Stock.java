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
            goods.add((Goods) dao.create());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public static Goods findProduct() {
        Goods item = null;
        try {
            GenericDao dao = DaoCreator.createMySqlDao("goods");
            goods = dao.readAll();

            for (Goods good : goods) {
                System.out.println(good.toString());
            }
            System.out.print("Chose item by name: ");
            Scanner in = new Scanner(System.in);
            String inputName = in.nextLine();

            for (int i = 0; i < goods.size(); i++) {
                if (goods.get(i).getName().toLowerCase().equalsIgnoreCase(inputName.toLowerCase())) {
                    item = goods.get(i);
                }
            }
        } catch (DaoException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            return item;
        }
    }


    public static StringBuilder getInformationAboutProducts() throws DaoException {
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