package com.stef.MagazineProject.support;

import java.util.Scanner;

public class Menu {
    public static void menu() {
        int x = -1;
        System.out.println("Welcome to the internet shop");
        do {
            printMenu();
        } while (x != 0);
    }

    private static void printMenu() {

        System.out.println("You can choose one of the point to continue");
        System.out.println("1- Add product");
        System.out.println("2- Find product");
        System.out.println("3- Display information about product");
        System.out.println("4- Buy product");
        System.out.println("5-Sort products");
        System.out.println("6-Add product to the basket");
        System.out.println("7- Add to list favorites");
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

    private static void doIt(int x) {
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
            case 7:
                break;
            case 0:
                break;
            default:

        }
    }


}
