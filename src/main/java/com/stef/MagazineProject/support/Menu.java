package com.stef.MagazineProject.support;

import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.Client;
import com.stef.MagazineProject.domain.Employee;
import com.stef.MagazineProject.domain.FavouriteList;
import com.stef.MagazineProject.domain.Stock;

import java.util.Scanner;

public class Menu {
    public static void menu() throws DaoException {
        int x = -1;
        System.out.println("Welcome to the Internet Shop");
        Session.choice();
        do {
            printMenu();
            x = choice();
            doIt(x);
        } while (x != 0);
    }


    private static void printMenu() {
        System.out.println("You can choose one of the point to continue");
        System.out.println("1- Find product");
        System.out.println("2- Display information about products");
        System.out.println("3- Sort products");

        if (Session.getHuman() instanceof Client) {
            System.out.println("4- Buy product");
            System.out.println("5- Add product to the basket");
            System.out.println("6- Add to list favorites");

        } else if (Session.getHuman() instanceof Employee) {
            System.out.println("4- Add product");
        }
        System.out.println("0- Log out");
    }

    private static int choice() {
        Scanner in = new Scanner(System.in);
        int temp = -1;
        do {
            System.out.println("Enter your choice: ");
            temp = in.nextInt();
        } while (temp < 0 || temp >= 7);

        return temp;
    }

    private static void doIt(int x) {
        switch (x) {
            case 1:
                Session.setProduct(Stock.findProduct());
                break;
            case 2:
                System.out.println(Stock.getInformationAboutProducts());
                break;
            case 3:

                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 0:
                break;
            default:
        }
    }

}
