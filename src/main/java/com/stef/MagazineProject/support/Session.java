package com.stef.MagazineProject.support;

import com.stef.MagazineProject.DAO.DaoCreator;
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
    private static Goods goods = null;

    public static void login() throws DaoException {
        Scanner in = new Scanner(System.in);
        String login;
        String password;
        ArrayList<Client> clients = DaoCreator.readClientFromDB();
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
        System.out.println("Account not found. Do you want try again? 1-Yes,0-No");
        int choise = Integer.parseInt(in.next());
        if (choise == 1) login();
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
        int choice;
        MySQLDaoFactory factory = new MySQLDaoFactory();
        GenericDao dao = factory.getDao(factory.getConnection(), Employee.class);
        ArrayList<Employee> employees = dao.readAll();

        do {
            System.out.print("\nEnter your login: ");
            login = in.nextLine();
            System.out.print("Enter your password: ");
            password = in.nextLine();
            for (Employee employee : employees) {
                if (employee.getLogin().equals(login)) {
                    if (employee.getPassword().equals(password)) {
                        human = employee;
                        break;
                    } else System.out.println("Incorrect password");
                }
            }
            if(human == null){
                System.out.print("\nAccount not found. Do you want try again? 1-Yes,0-No");
                choice = Integer.parseInt(in.next());
            }else{
                choice = 0;
            }

        }while(choice!=0);
        System.out.println("Sing in successful");
    }

    public static void choice() throws DaoException {
        Scanner in = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.println("\nWhat do you want?");
            System.out.println("1-Register user`s account");
            System.out.println("2-Login as administrator");
            System.out.println("3-Login as user");
            System.out.print("\nEnter your choice:");
            choice = Integer.parseInt(in.next());
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    loginAdmin();
                    break;
                case 3:
                    login();
                    break;
            }
        } while (human == null);
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