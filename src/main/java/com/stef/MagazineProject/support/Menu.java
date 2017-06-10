package com.stef.MagazineProject.support;

import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.*;
import com.stef.MagazineProject.mysql.MySQLDaoFactory;

import java.util.ArrayList;
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
            temp = Integer.parseInt(in.next());
        } while (temp < 0 || temp >= 7);

        return temp;
    }

    private static void sortMenu() throws DaoException {
        Scanner in = new Scanner(System.in);
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao employeeDao = factory.getDao(factory.getConnection(), Employee.class);
        GenericDao clientDao = factory.getDao(factory.getConnection(), Client.class);
        ArrayList<Employee> employees;
        ArrayList<Client> clients;
        int temp = -1;
        do {
            System.out.println("You can: \n 1- Sort product by name\n" +
                    " 2- Sort product by price\n 3- Sort employee by full name\n");
            if (Session.getHuman() instanceof Employee) {
                System.out.println("4- Sort employee by salary\n 5- Sort client by full name");
            }
            System.out.println("0- Exit");

            System.out.println("Enter you choice: ");
            temp = Integer.parseInt(in.next());
        } while (temp != 0);

        switch (temp) {
            case 1:
                SelectionSort sortProductByName = new SelectionSort(Stock.getGoods());
                Stock.setGoods(sortProductByName.selectionSort("name"));
                System.out.println(Stock.getInformationAboutProducts());
                break;
            case 2:SelectionSort sortProductByPrice=new SelectionSort(Stock.getGoods());
                Stock.setGoods(sortProductByPrice.selectionSort("price"));
                System.out.println(Stock.getInformationAboutProducts());
                break;
            case 3:


                SelectionSort sortEmployeeByName = new SelectionSort(employeeDao.readAll());
                employees = sortEmployeeByName.selectionSort("name");
                for (Employee person: employees){
                    System.out.println(person.toString());
                }
                break;
            case 4:
                if (Session.getHuman() instanceof Employee) {
                    SelectionSort sortEmployeeBySalary = new SelectionSort(employeeDao.readAll());
                    employees = sortEmployeeBySalary.selectionSort("salary");
                    for (Employee person: employees) {
                        System.out.println(person.toString());
                    }
                }
                break;
            case 5:
                if (Session.getHuman() instanceof Employee) {
                    SelectionSort sortClientBySalary = new SelectionSort(clientDao.readAll());
                    employees = sortClientBySalary.selectionSort("fullname");
                    for (Employee person: employees) {
                        System.out.println(person.toString());
                    }
                }
                break;
            case 0: System.exit(0);
                break;

        }
    }

    private static void doIt(int x) throws DaoException{
        switch (x) {
            case 1:
                Session.setGoods(Stock.findProduct());
                break;
            case 2:
                System.out.println(Stock.getInformationAboutProducts());
                break;
            case 3:
                sortMenu();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 0:System.exit(0);
                break;
            default:
        }
    }

}
