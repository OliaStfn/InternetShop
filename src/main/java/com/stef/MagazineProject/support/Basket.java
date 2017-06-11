package com.stef.MagazineProject.support;

import com.stef.MagazineProject.domain.Goods;

import java.util.ArrayList;
import java.util.Scanner;

public class Basket {
    private ArrayList<Goods> basket = new ArrayList<>();

    public void addToBasket(Goods goods) {
        basket.add(goods);
    }

    public void deleteFromBasket() {
        Scanner in = new Scanner(System.in);
        String name;
        int repeat = -1;
        do {
            for (int i = 0; i < basket.size(); i++) {
                System.out.println(basket.get(i).toString());
            }
            System.out.println("Enter the name of the product that you want to delete: ");
            name = in.nextLine();

            for (int i = 0; i < basket.size(); i++) {
                if (basket.get(i).getName().equals(name)) {
                    basket.remove(i);
                    break;
                }
            }
            System.out.println("Do you want to delete another product? 1-Yes, 0- No");
            repeat = Integer.parseInt(in.next());

        } while (repeat != 0);
    }
}