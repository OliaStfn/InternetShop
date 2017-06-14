package com.stef.MagazineProject.support;

import com.stef.MagazineProject.DAO.DaoCreator;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.Client;
import com.stef.MagazineProject.domain.Employee;
import com.stef.MagazineProject.domain.Stock;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class Menu {

    private static final Logger log = Logger.getLogger(Menu.class);

    public static void main(String[] args) throws DaoException {
        menu();
    }

    public static void menu() throws DaoException {
        int x = -1;
        String tabulation = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(tabulation + "\nWelcome to the Internet Shop\n" + tabulation);
        Session.choice();
        do {
            printMenu();
            x = choice();
            doIt(x);
        } while (x != 0);
    }

    private static void printMenu() {
        System.out.println("\n1- Find product");
        System.out.println("2- Display information about product");
        System.out.println("3- Sort menu");

        if (Session.getHuman() instanceof Client) {
            System.out.println("4- Buy products");
            System.out.println("5- Add product to favourite list");
            System.out.println("6- Display information about product from favourite list");
            System.out.println("7-Add employee evaluate");

        } else if (Session.getHuman() instanceof Employee) {
            System.out.println("4- Add product");
            System.out.println("5- Add new employee");
        }
        System.out.println("0- Log out");
    }

    private static int choice() {
        Scanner in = new Scanner(System.in);
        int temp = -1;
        do {
            System.out.print("\nEnter your choice: ");
            temp = Integer.parseInt(in.next());
        } while (temp < 0 || temp >= 8);

        return temp;
    }

    private static void doIt(int x) throws DaoException {
        switch (x) {
            case 1:
                try {
                    Session.setGoods(Stock.findProduct());
                } catch (Exception e) {
                    log.error("Error with find item " + e.getMessage());
                }
                break;
            case 2:
                try {
                    GenericDao dao = DaoCreator.createMySqlDao("goods");
                    Stock.setGoods(dao.readAll());
                    System.out.println(Stock.getInformationAboutProducts());
                } catch (DaoException e) {
                    log.error("Error with output information about item " + e.getMessage());
                }
                break;
            case 3:
                try {
                    sortMenu();
                } catch (DaoException e) {
                    log.error("Error with sorting menu " + e.getMessage());
                }
                break;
            case 4:
                Scanner in = new Scanner(System.in);
                int temp = -1;
                if (Session.getHuman() instanceof Client) {
                    try {
                        ((Client) Session.getHuman()).addOrder();
                        do {
                            ((Client) Session.getHuman()).addGoodsToOrder();
                            System.out.println("Do you want to add another item? 1-Yes, 0-No");
                            temp = Integer.parseInt(in.next());
                        } while (temp != 0);
                    } catch (DaoException e) {
                        log.error("Error with add orders or add item to order" + e.getMessage());
                    }
                } else if (Session.getHuman() instanceof Employee) {
                    try {
                        Stock.addProduct();
                    } catch (Exception e) {
                        log.error("Error with add item to DB" + e.getMessage());
                    }
                }
                break;
            case 5:
                if (Session.getHuman() instanceof Client) {
                    try {
                        ((Client) Session.getHuman()).addProductToFavouriteList();
                    } catch (Exception e) {
                        log.error("Error with add item to favourite list " + e.getMessage());
                    }
                } else if (Session.getHuman() instanceof Employee) {
                    try {
                        GenericDao dao = DaoCreator.createMySqlDao("employee");
                        dao.create();
                    } catch (Exception e) {
                        log.error("Error with add item to DB" + e.getMessage());
                    }
                }
                break;
            case 6:
                if (Session.getHuman() instanceof Client) {
                    try {
                        ((Client) Session.getHuman()).showInformation();
                    } catch (Exception e) {
                        log.error("Error with display item from favourite list " + e.getMessage());
                    }
                }
                break;
            case 7:
                if (Session.getHuman() instanceof Client) {
                    try {
                        GenericDao dao = DaoCreator.createMySqlDao("employee");
                        in = new Scanner(System.in);
                        ArrayList<Employee> employees = dao.readAll();

                        for (Employee employee : employees) {
                            System.out.println(employee.toString() + "\n");
                        }
                        System.out.print("Enter name and surname employee which you want to evaluate: ");
                        String name = in.next();
                        String surname = in.next();
                        for (Employee employee : employees) {
                            if (employee.getName().toLowerCase().equals(name.toLowerCase())
                                    && employee.getSurname().toLowerCase().equals(surname.toLowerCase())){
                                employee.addMark();
                                break;
                            }
                        }
                    } catch (Exception e) {
                        log.error("Error with Evaluate employee " + e.getMessage());
                    }
                }
                break;
            case 0:
                Session.logOut();
                break;
            default:
                break;
        }
    }

    public static void sortMenu() throws DaoException {
        Scanner in = new Scanner(System.in);
        GenericDao dao = DaoCreator.createMySqlDao("employee");
        ArrayList<Employee> employees = dao.readAll();
        dao = DaoCreator.createMySqlDao("client");
        ArrayList<Client> clients = dao.readAll();
        dao = DaoCreator.createMySqlDao("goods");
        Stock.setGoods(dao.readAll());
        int temp = -1;
        String tabulation = "\n________________________________________________";
        do {
            System.out.println("\nYou can: " + tabulation);
            System.out.println("1- Sort product by name");
            System.out.println("2- Sort product by price");
            System.out.println("3- Sort employee by full name");

            if (Session.getHuman() instanceof Employee) {
                System.out.println("4- Sort employee by salary");
                System.out.println("5- Sort client by full name");
            }
            System.out.println("0- Exit" + tabulation);

            System.out.println("Enter you choice: ");
            temp = Integer.parseInt(in.next());
        } while (temp < 0 || temp > 5);

        switch (temp) {
            case 1:
                SelectionSort sortProductByName = new SelectionSort(Stock.getGoods());
                Stock.setGoods(sortProductByName.selectionSort("name"));
                System.out.println(Stock.getInformationAboutProducts());
                break;
            case 2:
                SelectionSort sortProductByPrice = new SelectionSort(Stock.getGoods());
                Stock.setGoods(sortProductByPrice.selectionSort("price"));
                System.out.println(Stock.getInformationAboutProducts());
                break;
            case 3:
                SelectionSort sortEmployeeByName = new SelectionSort(employees);
                employees = sortEmployeeByName.selectionSort("name");
                for (Employee person : employees) {
                    System.out.println(person.toString());
                }
                break;
            case 4:
                if (Session.getHuman() instanceof Employee) {
                    SelectionSort sortEmployeeBySalary = new SelectionSort(employees);
                    employees = sortEmployeeBySalary.selectionSort("salary");
                    for (Employee person : employees) {
                        System.out.println(person.toString());
                    }
                }
                break;
            case 5:
                if (Session.getHuman() instanceof Employee) {
                    SelectionSort sortClientByName = new SelectionSort(clients);
                    clients = sortClientByName.selectionSort("name");
                    for (Client person : clients) {
                        System.out.println(person.toString());
                    }
                }
                break;
            case 0:
                System.exit(0);
                break;

        }
    }
}