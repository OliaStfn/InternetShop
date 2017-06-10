package com.stef.MagazineProject.support;

import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.domain.Client;
import com.stef.MagazineProject.domain.Employee;
import com.stef.MagazineProject.domain.Human;
import com.stef.MagazineProject.domain.Goods;
import com.stef.MagazineProject.mysql.MySQLDaoFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Session {
    private static Human human = null;
    private static Goods goods =null;

    public static void login() throws DaoException {
        Scanner in = new Scanner(System.in);
        String login;
        String password;
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Client.class);
        ArrayList<Client> clients = dao.readAll();
        System.out.println("Enter your login: ");
        login = in.nextLine();
        System.out.println("Enter your password: ");
        password = in.nextLine();
        for (Client client : clients) {
            if (client.getLogin().equals(login)) {
                if (client.getPassword().equals(password)) {
                    human = client;
                    return;
                } else System.out.println("Incorrect password");
            }
        }
        System.out.println("Account not found");
    }

    public static void register() throws DaoException {
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Client.class);
        human = (Client) dao.create();
    }

    public static void loginAdmin() throws DaoException {
        Scanner in = new Scanner(System.in);
        String login;
        String password;
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Employee.class);
        ArrayList<Employee> employees = dao.readAll();
        System.out.println("Enter your login: ");
        login = in.nextLine();
        System.out.println("Enter your password: ");
        password = in.nextLine();
        for (Employee employee : employees) {
            if (employee.getLogin().equals(login)) {
                if (employee.getPassword().equals(password)) {
                    human = employee;
                    return;
                } else System.out.println("Incorrect password");
            }
        }
        System.out.println("Account not found");
    }

    public static void choice() throws DaoException {
        Scanner in = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.println("What do you want? Enter your choice: ");
            System.out.println("1-Register user`s account\n 2-Login as administrator\n " +
                    "3-Login as user\n 0-Exit");
            choice = Integer.parseInt(in.next());
            if (choice == 1) {
                register();
                break;
            } else if (choice == 2) {
                loginAdmin();
                break;
            } else if (choice == 3) {
                login();
                break;
            }
        } while (choice != 0);
    }

    public static void logOut() throws DaoException {
        human = null;
    }

    public static Human getHuman() {
        return human;
    }

    public static Goods getGoods() {
        return goods;
    }

    public static void setGoods(Goods goods) {
        Session.goods = goods;
    }
}
