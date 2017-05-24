package com.stef.MagazineProject.support;

import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.domain.Client;
import com.stef.MagazineProject.domain.Employee;

import java.util.Scanner;

public class Menu {
    public static void menu() throws DaoException {
        int x = -1;
        System.out.println("Welcome to the Internet Shop");
        Session.choice();

        if (Session.getHuman() instanceof Client) {
            do {
                printMenuForUser();
                x = choice();
            } while (x != 0);

        } else if (Session.getHuman() instanceof Employee) {
            do {
                printMenuForAdmin();
                x = choice();
            } while (x != 0);
        }


    }

    private static void printMenuForUser() {
        System.out.println("You can choose one of the point to continue");
        System.out.println("1- Find product");
        System.out.println("2- Display information about product");
        System.out.println("3- Buy product");
        System.out.println("4- Sort products");
        System.out.println("5- Add product to the basket");
        System.out.println("6- Add to list favorites");
        System.out.println("0- Exit");
    }

    private static void printMenuForAdmin() {
        System.out.println("You can choose one of the point to continue");
        System.out.println("1- Add product");
        System.out.println("2- Find product");
        System.out.println("3- Display information about product");
        System.out.println("4- Sort products");
        System.out.println("0- Exit");
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

    private static void doItForUser(int x) {
        switch (x) {
            case 1:
                break;
            case 2:
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
    private static void doItForAdmin(int x) {
        switch (x) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 0:
                break;
            default:
        }
    }

}
