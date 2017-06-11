package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.DaoCreator;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.DAO.Identifacator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class FavouriteList implements Identifacator<Integer> {
    private static final Logger log = Logger.getLogger(FavouriteList.class);
    private int id;
    private int clientId;
    private ArrayList<FavouriteListLine> item;

    public FavouriteList() {
        item = new ArrayList<FavouriteListLine>();
    }

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

    public void addProduct() {
        Scanner in = new Scanner(System.in);
        int repeat = -1;
        Goods pr = null;
        do {

            try {
                GenericDao dao = DaoCreator.createMySqlDao("favorite line");
                pr = Stock.findProduct();
                item.add((FavouriteListLine) dao.createInDB(new FavouriteListLine(pr, id)));
                log.info("The product was add to favourite list\n" + pr.toString());
                System.out.println("Add other product?");
                System.out.println("1-YES / 0-NO :");
                repeat = Integer.parseInt(in.next());
            } catch (Exception e) {
                log.error("Error" + e.getMessage());
            }
        } while (repeat != 0);
    }

    public void addProduct(Goods goods) {
        try {
            GenericDao dao = DaoCreator.createMySqlDao("favorite line");
            item.add((FavouriteListLine) dao.createInDB(new FavouriteListLine(goods, id)));
        } catch (Exception e) {
            log.error("Error" + e.getMessage());
        }
    }

    public void deleteProduct() {
        Scanner in = new Scanner(System.in);
        int repeat = -1;
        String temp;
        do {
            try {
                for (FavouriteListLine line : item) {
                    System.out.println(line.toString());
                }
                System.out.println("Enter name of product which you want delete");
                temp = in.nextLine();
                for (int i = 0; i < item.size(); i++) {
                    if (temp == item.get(i).getGoods().getName()) {
                        item.remove(i);
                    }
                }
                System.out.println("Delete other product?");
                System.out.println("1-YES / 0-NO :");
                repeat = Integer.parseInt(in.next());
            } catch (Exception e) {
                log.error("Error" + e.getMessage());
            }
        } while (repeat != 0);
    }

    @Override
    public String toString() {
        String temp = "";
        for (FavouriteListLine line : item) {
            temp += line.toString();
        }
        return "FavouriteList: " +
                "\n |\tItem: \t\t\t|\t" + temp;
    }
}