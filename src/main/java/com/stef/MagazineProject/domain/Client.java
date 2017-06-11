package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.DaoCreator;
import com.stef.MagazineProject.DAO.DaoException;
import com.stef.MagazineProject.DAO.GenericDao;
import com.stef.MagazineProject.DAO.Identifacator;
import com.stef.MagazineProject.support.ISort;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Client extends Human implements Identifacator<Integer>, ISort {
    private FavouriteList list;
    private int id;
    private String phoneNumber;
    private String address;
    private String login;
    private String password;
    private ArrayList<Order> orders;

    public Client() {
        list =null;
        setName("name");
        setSurname("surname");
        setBornDate(new GregorianCalendar(1990, 01, 01));
        phoneNumber = "none";
        address = "none";
    }

    public Client(String name, String surname,
                  int year, int month, int day,
                  String phoneNumber, String address) {
        list =null;
        setName(name);
        setSurname(surname);
        setBornDate(new GregorianCalendar(year, month, day));
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addProductToFavouriteList() throws DaoException {
        if (list == null){
            GenericDao dao = DaoCreator.createMySqlDao("favorite list");
            list = (FavouriteList) dao.createInDB(new FavouriteList());
        }
        list.addProduct();
    }

    public void deleteProductFromFavouriteList() {
        list.deleteProduct();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addOrder() throws DaoException {
        GenericDao dao = DaoCreator.createMySqlDao("order");
        orders.add((Order) dao.createInDB(new Order(id)));
    }

    public void addGoodsToOrder(){
        Goods goods = Stock.findProduct();
        Scanner in= new Scanner(System.in);
        int quantity;
        System.out.println("How much product you want to buy?");
        quantity=Integer.parseInt(in.next());
        orders.get(orders.size()-1).addNewLine(quantity,goods);
    }

    public void deleteOrder() throws DaoException {
        GenericDao dao = DaoCreator.createMySqlDao("order");
        Scanner in = new Scanner(System.in);
        int temp;
        orders = dao.readAll();
        for (Order order : orders) {
            System.out.println(order.toString());
        }
        System.out.println("Enter order id which you want to delete");
        temp = Integer.parseInt(in.next());
        for (Order order : orders) {
            if (order.getId() == temp) {
                dao.delete(order);
                orders.remove(order);
            }
        }
    }

    @Override
    public String toString() {
        String tabulation = "\n________________________________________________";
        return "Client:" + tabulation +
                "\n |\tName: \t\t\t|\t" + getName() +
                "\n |\tSurname: \t\t|\t" + getSurname() +
                "\n |\tLogin: \t\t\t|\t" + getLogin() +
                "\n |\tBorn Date: \t\t|\t" + getBornDate().get(GregorianCalendar.DATE) +
                "." + getBornDate().get(GregorianCalendar.MONTH) +
                "." + getBornDate().get(GregorianCalendar.YEAR) +
                "\n |\tPhoneNumber: \t|\t" + phoneNumber +
                "\n |\tAddress: \t\t|\t" + address + tabulation;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getWordForSort() {
        return getSurname() + getName();
    }
}